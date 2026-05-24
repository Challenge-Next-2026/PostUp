usuarios = []
postagens = []
avaliacoes = []
pontuacoes = []


# =========================
# USUÁRIOS
# =========================
# Função responsável por cadastrar um novo usuário
# Aqui o sistema pede os dados, valida email repetido
# e salva o usuário dentro da lista principal

def cadastrar_usuario():
    print("\n=== CADASTRO DE USUÁRIO ===")

    nome = input("Nome de usuário: ")
    email = input("Email: ")

    # Verifica se email já existe
    for usuario in usuarios:
        if usuario["email"] == email:
            print("Email já cadastrado.")
            return

    senha = input("Senha: ")
    confirmar = input("Confirmar senha: ")

    if senha != confirmar:
        print("As senhas não coincidem.")
        return

    usuario = {
        "nome": nome,
        "email": email,
        "senha": senha
    }

    usuarios.append(usuario)

    print("Usuário cadastrado com sucesso!")


# =========================
# LOGIN
# =========================
# Função responsável pelo login
# O sistema compara email e senha digitados
# com os usuários já cadastrados na lista

def login():
    print("\n=== LOGIN ===")

    email = input("Email: ")
    senha = input("Senha: ")

    for usuario in usuarios:
        if usuario["email"] == email and usuario["senha"] == senha:
            print(f"Bem-vindo, {usuario['nome']}!")
            menu_usuario(usuario)
            return

    print("Email ou senha incorretos.")


# =========================
# MENU DO USUÁRIO
# =========================
# Menu do usuário após login
# Aqui ficam todas as funcionalidades principais
# relacionadas à conta do usuário

def menu_usuario(usuario):
    while True:
        print("\n=== MENU DO USUÁRIO ===")
        print("1 - Criar postagem")
        print("2 - Ver minhas postagens")
        print("3 - Ver pontuação")
        print("4 - Alterar email")
        print("5 - Alterar senha")
        print("6 - Logout")

        try:
            opcao = int(input("Escolha uma opção: "))

            match opcao:
                case 1:
                    criar_postagem(usuario)

                case 2:
                    ver_postagens(usuario)

                case 3:
                    ver_pontuacao(usuario)

                case 4:
                    alterar_email(usuario)

                case 5:
                    alterar_senha(usuario)

                case 6:
                    print("Logout realizado.")
                    break

                case _:
                    print("Opção inválida.")

        except ValueError:
            print("Digite apenas números.")


# =========================
# POSTAGEM
# =========================
# Função para criar uma postagem
# A postagem fica vinculada ao usuário logado
# e é salva dentro da lista de postagens

def criar_postagem(usuario):
    print("\n=== NOVA POSTAGEM ===")

    titulo = input("Título da ação sustentável: ")
    descricao = input("Descrição: ")
    arquivo = input("Nome do arquivo/imagem: ")

    postagem = {
        "usuario": usuario["nome"],
        "titulo": titulo,
        "descricao": descricao,
        "arquivo": arquivo
    }

    postagens.append(postagem)

    print("Postagem criada com sucesso!")

    avaliar_postagem(postagem)


# =========================
# VER POSTAGENS
# =========================
# Função para visualizar postagens
# Mostra apenas as postagens do usuário logado

def ver_postagens(usuario):
    print("\n=== MINHAS POSTAGENS ===")

    encontrou = False

    for postagem in postagens:
        if postagem["usuario"] == usuario["nome"]:
            encontrou = True

            print("---------------------")
            print(f"Título: {postagem['titulo']}")
            print(f"Descrição: {postagem['descricao']}")
            print(f"Arquivo: {postagem['arquivo']}")

    if encontrou == False:
        print("Nenhuma postagem encontrada.")


# =========================
# ALTERAR EMAIL
# =========================
# Função para alterar email
# O sistema verifica se o novo email já existe
# Caso não exista, o email é atualizado

def alterar_email(usuario):
    print("=== ALTERAR EMAIL ===")

    novo_email = input("Novo email: ")

    email_existente = False

    for u in usuarios:
        if u["email"] == novo_email:
            email_existente = True

    if email_existente == True:
        print("Esse email já está em uso.")

    else:
        usuario["email"] = novo_email
        print("Email alterado com sucesso!")


# =========================
# ALTERAR SENHA
# =========================
# Função para alterar senha
# Primeiro confirma a senha atual
# Depois pede confirmação da nova senha

def alterar_senha(usuario):
    print("=== ALTERAR SENHA ===")

    senha_atual = input("Digite sua senha atual: ")

    if senha_atual == usuario["senha"]:
        nova_senha = input("Nova senha: ")
        confirmar = input("Confirme a nova senha: ")

        if nova_senha == confirmar:
            usuario["senha"] = nova_senha
            print("Senha alterada com sucesso!")

        else:
            print("As senhas não coincidem.")

    else:
        print("Senha atual incorreta.")


# =========================
# VER PONTUAÇÃO
# =========================
# Função para mostrar a pontuação total
# Soma todos os pontos que pertencem ao usuário

def ver_pontuacao(usuario):
    print("=== PONTUAÇÃO TOTAL ===")

    total = 0

    for pontuacao in pontuacoes:
        if pontuacao["usuario"] == usuario["nome"]:
            total = total + pontuacao["pontos"]

    print(f"Pontuação total ECOA: {total}")


# =========================
# AVALIAÇÃO
# =========================
# Função de avaliação da postagem
# Aqui o sistema analisa o tipo da ação sustentável
# e gera notas de impacto, dificuldade e confiabilidade

def avaliar_postagem(postagem):
    print("\n=== AVALIAÇÃO ===")

    titulo = postagem["titulo"].lower()

    impacto = 0
    dificuldade = 0
    confiabilidade = 100
    frequencia = 50

    if titulo == "plantar":
        impacto = 100
        dificuldade = 50

    elif titulo == "reciclagem":
        impacto = 90
        dificuldade = 70

    elif titulo == "bicicleta":
        impacto = 80
        dificuldade = 80

    else:
        impacto = 60
        dificuldade = 40

    avaliacao = {
        "usuario": postagem["usuario"],
        "titulo": postagem["titulo"],
        "impacto": impacto,
        "dificuldade": dificuldade,
        "confiabilidade": confiabilidade,
        "frequencia": frequencia
    }

    avaliacoes.append(avaliacao)

    print("Postagem avaliada com sucesso!")

    calcular_pontuacao(avaliacao)


# =========================
# PONTUAÇÃO
# =========================
def calcular_pontuacao(avaliacao):
    print("\n=== PONTUAÇÃO ===")

    pontos = (
        avaliacao["impacto"] +
        avaliacao["dificuldade"] +
        avaliacao["confiabilidade"] +
        avaliacao["frequencia"]
    ) / 4

    pontuacao = {
        "usuario": avaliacao["usuario"],
        "titulo": avaliacao["titulo"],
        "pontos": pontos
    }

    pontuacoes.append(pontuacao)

    print(f"Pontuação ECOA: {pontos}")


# =========================
# MENU PRINCIPAL
# =========================
while True:
    print("\n=== POSTUP ===")
    print("1 - Cadastrar usuário")
    print("2 - Login")
    print("3 - Sair")

    try:
        opcao = int(input("Escolha uma opção: "))

        match opcao:
            case 1:
                cadastrar_usuario()

            case 2:
                login()

            case 3:
                print("Programa encerrado.")
                break

            case _:
                print("Opção inválida.")

    except ValueError:
        print("Digite apenas números.")
