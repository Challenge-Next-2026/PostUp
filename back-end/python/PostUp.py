
import json
from datetime import datetime
from getpass import getpass
from pathlib import Path

import oracledb
import requests
from conexao import conectar

API_JAVA = 'https://postup-api.onrender.com/postup'
EXPORTACOES = Path(__file__).resolve().parent / 'exportacoes'


def pedir(rotulo, limite=None):
    valor = input(rotulo).strip()
    if not valor:
        print('Campo obrigatório.')
        return None
    if limite is not None and len(valor) > limite:
        print(f'O limite é de {limite} caracteres.')
        return None
    return valor


def confirmar():
    return input('Digite SIM para confirmar: ').strip().upper() == 'SIM'


def id_novo(cursor, tabela, coluna):
    # Identificadores fixos e internos: nunca recebem texto do usuário.
    permitidos = {
        ('USUARIO', 'ID_USUARIO'), ('POSTAGEM', 'ID_POSTAGEM'),
        ('AVALIACAO', 'ID_AVALIACAO'), ('PONTUACAO', 'ID_PONTUACAO')
    }
    if (tabela, coluna) not in permitidos:
        raise ValueError('Tabela/coluna não autorizada.')
    cursor.execute(f'SELECT NVL(MAX({coluna}), 0) + 1 FROM {tabela}')
    return int(cursor.fetchone()[0])


def exportar_json(dados, prefixo):
    try:
        EXPORTACOES.mkdir(parents=True, exist_ok=True)
        nome = f'{prefixo}_{datetime.now():%Y%m%d_%H%M%S_%f}.json'
        destino = EXPORTACOES / nome
        with destino.open('w', encoding='utf-8') as arq:
            json.dump(dados, arq, ensure_ascii=False, indent=2, default=str)
        print(f'Arquivo JSON salvo em: {destino}')
    except OSError as erro:
        print(f'Falha na exportação: {erro}')


def oferecer_exportacao(dados, prefixo):
    if input('Exportar resultado para JSON? (s/n): ').strip().lower() == 's':
        exportar_json(dados, prefixo)


def consultar_api(recurso):
    if recurso not in ('usuario', 'postagem'):
        raise ValueError('Recurso não permitido.')
    try:
        resposta = requests.get(f'{API_JAVA}/{recurso}', timeout=60)
        resposta.raise_for_status()
        return resposta.json()
    except (requests.RequestException, ValueError) as erro:
        print(f'Erro ao consultar a API Java: {erro}')
        return None


def listar_api(recurso):
    dados = consultar_api(recurso)
    if dados is None:
        return
    print(f'\n=== {recurso.upper()} (API JAVA) ===')
    if isinstance(dados, list):
        if not dados:
            print('Nenhum registro retornado.')
        for item in dados:
            print(json.dumps(item, ensure_ascii=False, default=str))
    else:
        print(json.dumps(dados, ensure_ascii=False, indent=2, default=str))
    print('Observação: campos nulos podem decorrer do DTO da API Java.')
    oferecer_exportacao(dados, recurso + '_api')


def avaliar_postagem(titulo):
    chave = titulo.strip().lower()
    if chave == 'plantar':
        impacto, dificuldade = 100, 50
    elif chave == 'reciclagem':
        impacto, dificuldade = 90, 70
    elif chave == 'bicicleta':
        impacto, dificuldade = 80, 80
    else:
        impacto, dificuldade = 60, 40
    return {'impacto': impacto, 'dificuldade': dificuldade,
            'confiabilidade': 100, 'frequencia': 50}


def calcular_pontuacao(avaliacao):
    return sum(avaliacao.values()) / 4


def criar_avaliacao_e_pontuacao(cursor, titulo):
    """Grava os dois registros; chamador decide quando dar commit."""
    avaliacao = avaliar_postagem(titulo)
    pontos = calcular_pontuacao(avaliacao)
    id_avaliacao = id_novo(cursor, 'AVALIACAO', 'ID_AVALIACAO')
    cursor.execute('''
        INSERT INTO AVALIACAO
        (ID_AVALIACAO, DS_CRITERIOAVALIACAO, VL_NOTAIMPACTO,
         VL_NOTADIFICULDADE, VL_NOTACONFIABILIDADE, VL_NOTAFREQUENCIA, DT_AVALIACAO)
        VALUES (:id, :criterio, :impacto, :dificuldade, :confiabilidade,
                :frequencia, SYSDATE)
    ''', {'id': id_avaliacao, 'criterio': 'Avaliacao automatica PostUp', **avaliacao})
    id_pontuacao = id_novo(cursor, 'PONTUACAO', 'ID_PONTUACAO')
    cursor.execute('''
        INSERT INTO PONTUACAO
        (ID_PONTUACAO, VL_PONTUACAO, DS_ORIGEMPONTUACAO, DT_PONTUACAO)
        VALUES (:id, :pontos, :origem, SYSDATE)
    ''', {'id': id_pontuacao, 'pontos': pontos, 'origem': 'PostUp'})
    return id_avaliacao, id_pontuacao, avaliacao, pontos


def cadastrar_usuario():
    print('\n=== CADASTRAR USUÁRIO ===')
    nome = pedir('Nome: ', 100)
    email = pedir('E-mail: ', 50)
    if not nome or not email:
        return
    if '@' not in email or email.startswith('@') or email.endswith('@'):
        print('Informe um e-mail válido.')
        return
    senha = getpass('Senha: ')
    if not senha or len(senha) > 50 or senha != getpass('Confirmar senha: '):
        print('Senha inválida ou confirmação diferente.')
        return
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        usuario_id = id_novo(cursor, 'USUARIO', 'ID_USUARIO')
        cursor.execute('''
            INSERT INTO USUARIO (ID_USUARIO, NM_USUARIO, DS_EMAIL, DS_SENHA,
                                 DT_CADASTRO, ST_CONTA, NUM_POSICAORANKING)
            VALUES (:id, :nome, :email, :senha, SYSDATE, 'T', 1)
        ''', {'id': usuario_id, 'nome': nome, 'email': email, 'senha': senha})
        banco.commit()
        print(f'Usuário cadastrado no Oracle! ID: {usuario_id}')
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Erro ao cadastrar usuário: {erro}')
    finally:
        cursor.close()
        banco.close()


def login():
    print('\n=== LOGIN ===')
    email = pedir('E-mail: ', 50)
    if not email:
        return
    senha = getpass('Senha: ')
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        cursor.execute('''
            SELECT ID_USUARIO, NM_USUARIO, DS_EMAIL FROM USUARIO
            WHERE DS_EMAIL = :email AND DS_SENHA = :senha AND ST_CONTA = 'T'
        ''', {'email': email, 'senha': senha})
        linha = cursor.fetchone()
    except oracledb.Error as erro:
        print(f'Erro no login: {erro}')
        linha = None
    finally:
        cursor.close()
        banco.close()
    if linha:
        usuario = {'id': int(linha[0]), 'nome': linha[1], 'email': linha[2]}
        print(f'Bem-vindo(a), {usuario["nome"]}!')
        menu_usuario(usuario)
    else:
        print('E-mail ou senha incorretos.')


def atualizar_campo(usuario, coluna, valor):
    if coluna not in ('NM_USUARIO', 'DS_EMAIL'):
        raise ValueError('Campo não autorizado.')
    banco = conectar()
    if banco is None:
        return False
    cursor = banco.cursor()
    try:
        cursor.execute(f'''UPDATE USUARIO SET {coluna} = :valor
                           WHERE ID_USUARIO = :id AND DS_EMAIL = :email''',
                       {'valor': valor, 'id': usuario['id'], 'email': usuario['email']})
        if cursor.rowcount != 1:
            banco.rollback()
            print('Usuário não encontrado ou sessão desatualizada.')
            return False
        banco.commit()
        print('Alteração salva no Oracle!')
        return True
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Erro ao atualizar: {erro}')
        return False
    finally:
        cursor.close()
        banco.close()


def alterar_nome(usuario):
    nome = pedir('Novo nome: ', 100)
    if nome and atualizar_campo(usuario, 'NM_USUARIO', nome):
        usuario['nome'] = nome


def alterar_email(usuario):
    email = pedir('Novo e-mail: ', 50)
    if not email:
        return
    if '@' not in email or email.startswith('@') or email.endswith('@'):
        print('E-mail inválido.')
        return
    if atualizar_campo(usuario, 'DS_EMAIL', email):
        usuario['email'] = email


def alterar_senha(usuario):
    atual = getpass('Senha atual: ')
    nova = getpass('Nova senha: ')
    if not nova or len(nova) > 50 or nova != getpass('Confirmar nova senha: '):
        print('Nova senha inválida ou confirmação diferente.')
        return
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        cursor.execute('''UPDATE USUARIO SET DS_SENHA = :nova
                          WHERE ID_USUARIO = :id AND DS_SENHA = :atual
                          AND DS_EMAIL = :email''',
                       {'nova': nova, 'atual': atual, 'id': usuario['id'],
                        'email': usuario['email']})
        if cursor.rowcount == 1:
            banco.commit()
            print('Senha alterada no Oracle!')
        else:
            banco.rollback()
            print('Senha atual incorreta.')
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Erro ao alterar senha: {erro}')
    finally:
        cursor.close()
        banco.close()


def minhas_postagens(cursor, usuario_id):
    cursor.execute('''
        SELECT p.ID_POSTAGEM, p.DS_TITULOPOST, p.DS_POSTAGEM,
               po.VL_PONTUACAO, p.AVALIACAO_ID_AVALIACAO, p.PONTUACAO_ID_PONTUACAO
        FROM POSTAGEM p
        JOIN POSTAGEM_USUARIO pu ON pu.ID_POSTAGEM = p.ID_POSTAGEM
        LEFT JOIN PONTUACAO po ON po.ID_PONTUACAO = p.PONTUACAO_ID_PONTUACAO
        WHERE pu.ID_USUARIO = :id ORDER BY p.ID_POSTAGEM
    ''', {'id': usuario_id})
    return cursor.fetchall()


def ver_postagens(usuario):
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        linhas = minhas_postagens(cursor, usuario['id'])
        print('\n=== MINHAS POSTAGENS (ORACLE) ===')
        dados = []
        for ident, titulo, descricao, pontos, _, _ in linhas:
            print(f'#{ident} | {titulo}: {descricao} | Pontos: {pontos if pontos is not None else "não registrados"}')
            dados.append({'idPostagem': ident, 'titulo': titulo,
                          'descricao': descricao, 'pontuacao': pontos})
        if not dados:
            print('Nenhuma postagem encontrada.')
        oferecer_exportacao(dados, 'minhas_postagens')
    except oracledb.Error as erro:
        print(f'Erro ao consultar postagens: {erro}')
    finally:
        cursor.close()
        banco.close()


def criar_postagem(usuario):
    print('\n=== CRIAR POSTAGEM ===')
    titulo = pedir('Título (até 20 caracteres): ', 20)
    descricao = pedir('Descrição (até 200 caracteres): ', 200)
    if not titulo or not descricao:
        return
    print('Esta versão não envia arquivos/imagens ao servidor.')
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        # Tudo na mesma transação: falha em qualquer etapa => rollback completo.
        av_id, po_id, avaliacao, pontos = criar_avaliacao_e_pontuacao(cursor, titulo)
        post_id = id_novo(cursor, 'POSTAGEM', 'ID_POSTAGEM')
        cursor.execute('''
            INSERT INTO POSTAGEM
              (ID_POSTAGEM, DS_TITULOPOST, DS_POSTAGEM, DT_POSTAGEM,
               ST_POSTAGEM, GEOLOCALIZACAO_ID_GEOLOC, VALIDACAO_ID_VALIDACAO,
               AVALIACAO_ID_AVALIACAO, PONTUACAO_ID_PONTUACAO)
            VALUES (:id, :titulo, :descricao, SYSDATE, 'T', 1, 1, :av, :po)
        ''', {'id': post_id, 'titulo': titulo, 'descricao': descricao,
              'av': av_id, 'po': po_id})
        cursor.execute('''
            INSERT INTO POSTAGEM_USUARIO
              (ID_USUARIO, ID_POSTAGEM, QTD_PORCENTAGEMPARTICIPACAO)
            VALUES (:usuario, :postagem, 99)
        ''', {'usuario': usuario['id'], 'postagem': post_id})
        banco.commit()
        print(f'Postagem #{post_id} cadastrada no Oracle!')
        print(f'Avaliação registrada: {avaliacao}')
        print(f'Pontuação registrada: {pontos:.1f}')
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Erro ao criar postagem (transação revertida): {erro}')
    finally:
        cursor.close()
        banco.close()


def buscar_postagem_propria(cursor, usuario, post_id):
    cursor.execute('''
        SELECT p.DS_TITULOPOST, p.DS_POSTAGEM,
               p.AVALIACAO_ID_AVALIACAO, p.PONTUACAO_ID_PONTUACAO
        FROM POSTAGEM p
        JOIN POSTAGEM_USUARIO pu ON pu.ID_POSTAGEM = p.ID_POSTAGEM
        WHERE p.ID_POSTAGEM = :post AND pu.ID_USUARIO = :usuario
        FOR UPDATE OF p.DS_TITULOPOST
    ''', {'post': post_id, 'usuario': usuario['id']})
    return cursor.fetchone()


def pedir_id_postagem():
    texto = pedir('ID da postagem: ')
    if texto is None:
        return None
    if not texto.isdigit() or int(texto) <= 0:
        print('Informe um ID numérico positivo.')
        return None
    return int(texto)


def editar_postagem(usuario):
    print('\n=== EDITAR MINHA POSTAGEM ===')
    post_id = pedir_id_postagem()
    if post_id is None:
        return
    titulo = pedir('Novo título (até 20 caracteres): ', 20)
    descricao = pedir('Nova descrição (até 200 caracteres): ', 200)
    if not titulo or not descricao:
        return
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        atual = buscar_postagem_propria(cursor, usuario, post_id)
        if not atual:
            print('Postagem não encontrada para este usuário.')
            banco.rollback()
            return
        # Recalcula pontuação quando o título é alterado; inclui legado sem avaliação.
        avaliacao = avaliar_postagem(titulo)
        pontos = calcular_pontuacao(avaliacao)
        av_id, po_id = atual[2], atual[3]
        if av_id is None or po_id is None:
            novo_av, novo_po, _, _ = criar_avaliacao_e_pontuacao(cursor, titulo)
            if av_id is None:
                av_id = novo_av
            else:
                cursor.execute('DELETE FROM AVALIACAO WHERE ID_AVALIACAO = :id', {'id': novo_av})
            if po_id is None:
                po_id = novo_po
            else:
                cursor.execute('DELETE FROM PONTUACAO WHERE ID_PONTUACAO = :id', {'id': novo_po})
        cursor.execute('''
            UPDATE AVALIACAO SET VL_NOTAIMPACTO = :impacto,
              VL_NOTADIFICULDADE = :dificuldade,
              VL_NOTACONFIABILIDADE = :confiabilidade,
              VL_NOTAFREQUENCIA = :frequencia, DT_AVALIACAO = SYSDATE
            WHERE ID_AVALIACAO = :id
        ''', {'id': av_id, **avaliacao})
        cursor.execute('''UPDATE PONTUACAO SET VL_PONTUACAO = :pontos,
                          DT_PONTUACAO = SYSDATE WHERE ID_PONTUACAO = :id''',
                       {'pontos': pontos, 'id': po_id})
        cursor.execute('''UPDATE POSTAGEM SET DS_TITULOPOST = :titulo,
                          DS_POSTAGEM = :descricao,
                          AVALIACAO_ID_AVALIACAO = :av,
                          PONTUACAO_ID_PONTUACAO = :po
                          WHERE ID_POSTAGEM = :id''',
                       {'titulo': titulo, 'descricao': descricao, 'av': av_id,
                        'po': po_id, 'id': post_id})
        banco.commit()
        print(f'Postagem #{post_id} atualizada. Nova pontuação: {pontos:.1f}')
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Erro ao editar postagem (revertido): {erro}')
    finally:
        cursor.close()
        banco.close()


def excluir_postagem(usuario):
    print('\n=== EXCLUIR MINHA POSTAGEM ===')
    post_id = pedir_id_postagem()
    if post_id is None or not confirmar():
        print('Exclusão cancelada.')
        return
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        atual = buscar_postagem_propria(cursor, usuario, post_id)
        if not atual:
            banco.rollback()
            print('Postagem não encontrada para este usuário.')
            return
        cursor.execute('SELECT COUNT(*) FROM POSTAGEM_USUARIO WHERE ID_POSTAGEM = :id',
                       {'id': post_id})
        if int(cursor.fetchone()[0]) != 1:
            banco.rollback()
            print('Postagem compartilhada: exclusão bloqueada por segurança.')
            return
        cursor.execute('DELETE FROM POSTAGEM_USUARIO WHERE ID_POSTAGEM = :id',
                       {'id': post_id})
        cursor.execute('DELETE FROM POSTAGEM WHERE ID_POSTAGEM = :id', {'id': post_id})
        # Só remove registros auxiliares quando nenhuma outra postagem os utiliza.
        if atual[2] is not None:
            cursor.execute('''DELETE FROM AVALIACAO WHERE ID_AVALIACAO = :id
                              AND NOT EXISTS (SELECT 1 FROM POSTAGEM
                              WHERE AVALIACAO_ID_AVALIACAO = :id)''', {'id': atual[2]})
        if atual[3] is not None:
            cursor.execute('''DELETE FROM PONTUACAO WHERE ID_PONTUACAO = :id
                              AND NOT EXISTS (SELECT 1 FROM POSTAGEM
                              WHERE PONTUACAO_ID_PONTUACAO = :id)''', {'id': atual[3]})
        banco.commit()
        print(f'Postagem #{post_id} excluída.')
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Exclusão não realizada; vínculos podem impedir a operação: {erro}')
    finally:
        cursor.close()
        banco.close()


def ver_pontuacao(usuario):
    banco = conectar()
    if banco is None:
        return
    cursor = banco.cursor()
    try:
        cursor.execute('''
            SELECT NVL(SUM(po.VL_PONTUACAO), 0)
            FROM POSTAGEM_USUARIO pu
            JOIN POSTAGEM p ON p.ID_POSTAGEM = pu.ID_POSTAGEM
            JOIN PONTUACAO po ON po.ID_PONTUACAO = p.PONTUACAO_ID_PONTUACAO
            WHERE pu.ID_USUARIO = :id
        ''', {'id': usuario['id']})
        total = cursor.fetchone()[0]
        print(f'Pontuação total registrada no Oracle: {total}')
        print('Postagens antigas sem vínculo de pontuação não entram nessa soma.')
        oferecer_exportacao({'idUsuario': usuario['id'], 'pontuacaoTotal': total},
                            'minha_pontuacao')
    except oracledb.Error as erro:
        print(f'Erro ao consultar pontuação: {erro}')
    finally:
        cursor.close()
        banco.close()


def excluir_minha_conta(usuario):
    print('\nATENÇÃO: exclusão permanente. Postagens vinculadas impedem a exclusão.')
    if not confirmar():
        print('Exclusão cancelada.')
        return False
    banco = conectar()
    if banco is None:
        return False
    cursor = banco.cursor()
    try:
        cursor.execute('SELECT COUNT(*) FROM POSTAGEM_USUARIO WHERE ID_USUARIO = :id',
                       {'id': usuario['id']})
        if int(cursor.fetchone()[0]):
            print('Exclua suas postagens primeiro, se desejar excluir esta conta.')
            banco.rollback()
            return False
        cursor.execute('''DELETE FROM USUARIO WHERE ID_USUARIO = :id
                          AND DS_EMAIL = :email''',
                       {'id': usuario['id'], 'email': usuario['email']})
        if cursor.rowcount != 1:
            banco.rollback()
            print('Conta não encontrada.')
            return False
        banco.commit()
        print('Conta excluída do Oracle.')
        return True
    except oracledb.Error as erro:
        banco.rollback()
        print(f'Não foi possível excluir a conta: {erro}')
        return False
    finally:
        cursor.close()
        banco.close()


def consultar_cep():
    cep = pedir('CEP (8 números): ')
    if not cep:
        return
    cep = cep.replace('-', '')
    if len(cep) != 8 or not cep.isdigit():
        print('CEP inválido. Informe 8 dígitos.')
        return
    try:
        resposta = requests.get(f'https://brasilapi.com.br/api/cep/v1/{cep}', timeout=20)
        resposta.raise_for_status()
        dados = resposta.json()
        print(f'Cidade/UF: {dados.get("city")} / {dados.get("state")}')
        print(f'Bairro: {dados.get("neighborhood") or "Não informado"}')
        print('Integração externa: consulta de localização para ações sustentáveis.')
        oferecer_exportacao(dados, 'cep')
    except (requests.RequestException, ValueError) as erro:
        print(f'Erro na BrasilAPI: {erro}')


def menu_usuario(usuario):
    while True:
        print('\n=== MENU DO USUÁRIO ===')
        print('1 - Criar postagem')
        print('2 - Ver minhas postagens')
        print('3 - Ver pontuação')
        print('4 - Alterar e-mail')
        print('5 - Alterar senha')
        print('6 - Alterar nome')
        print('7 - Excluir minha conta')
        print('8 - Consultar todas as postagens (API Java)')
        print('9 - Consultar CEP (API pública)')
        print('10 - Editar minha postagem')
        print('11 - Excluir minha postagem')
        print('0 - Logout')
        opcao = input('Opção: ').strip()
        # Mantemos if/elif como no projeto original.
        if opcao == '1':
            criar_postagem(usuario)
        elif opcao == '2':
            ver_postagens(usuario)
        elif opcao == '3':
            ver_pontuacao(usuario)
        elif opcao == '4':
            alterar_email(usuario)
        elif opcao == '5':
            alterar_senha(usuario)
        elif opcao == '6':
            alterar_nome(usuario)
        elif opcao == '7':
            if excluir_minha_conta(usuario):
                break
        elif opcao == '8':
            listar_api('postagem')
        elif opcao == '9':
            consultar_cep()
        elif opcao == '10':
            editar_postagem(usuario)
        elif opcao == '11':
            excluir_postagem(usuario)
        elif opcao == '0':
            print('Logout realizado.')
            break
        else:
            print('Opção inválida.')


def menu_principal():
    while True:
        print('\n=== POSTUP | SPRINT 4 ===')
        print('1 - Cadastrar usuário (CREATE / Oracle)')
        print('2 - Login')
        print('3 - Consultar usuários (READ / API Java)')
        print('4 - Consultar postagens (READ / API Java)')
        print('5 - Consultar CEP (API pública externa)')
        print('0 - Sair')
        opcao = input('Escolha uma opção: ').strip()
        if opcao == '1':
            cadastrar_usuario()
        elif opcao == '2':
            login()
        elif opcao == '3':
            listar_api('usuario')
        elif opcao == '4':
            listar_api('postagem')
        elif opcao == '5':
            consultar_cep()
        elif opcao == '0':
            print('Programa encerrado.')
            break
        else:
            print('Opção inválida.')


if __name__ == '__main__':
    try:
        menu_principal()
    except (KeyboardInterrupt, EOFError):
        print('\nPrograma encerrado pelo usuário.')
