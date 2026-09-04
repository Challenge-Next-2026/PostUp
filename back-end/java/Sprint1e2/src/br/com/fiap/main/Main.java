package br.com.fiap.main;

import br.com.fiap.bean.*;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.PostagemDAO;
import br.com.fiap.dao.UsuarioDAO;

import javax.swing.*;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    static void main()
    {
        // Variáveis, Listas e objetos de apoio
        int escolha, id, idPostagem, idUsuario = 0, idUsuario2 = 1, contador2 = 1, contador3 = 1;
        String auxiliar, opcao = "sim";
        List <Usuario> usuarios = new ArrayList<>();
        List <Postagem> postagens = new ArrayList<>();
        List <Arquivo> arquivos = new ArrayList<>();
        List <Avaliacao> avaliacoes = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Connection con = ConnectionFactory.abrirConexao();
        List <UsuarioDAO> usuariosDAO = new ArrayList<>();
        List <PostagemDAO> postagensDAO = new ArrayList<>();
        // laço de controle de execução
        do{
            // Tratamento de errps
            try {
                escolha = Integer.parseInt(JOptionPane.showInputDialog("PostUp\n1 - Cadastro\n2 - Login\n3 - Sair"));
                switch (escolha){
                    // Cadastro
                    case 1:
                        // Objeto para realizar as operações
                        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
                        // Armazenamento de próximo id dentro do idUsuario
                        idUsuario = usuarioDAO.obterProximoId();
                        // Objeto para realizar operações
                        Usuario usuario = new Usuario();
                        // Cadastro de usuário
                        usuario.cadastrarUsuario(idUsuario);
                        // Inserção no banco
                        JOptionPane.showMessageDialog(null, usuarioDAO.inserir(usuario), "Alerta",JOptionPane.WARNING_MESSAGE);
                        // Atribuição de objetos de teste às listas
                        usuarios.add(usuario);
                        usuariosDAO.add(usuarioDAO);
                        // Exibição de objeto
                        Usuario exibicao = usuarioDAO.exibir(usuario, idUsuario);
                        JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro()), "Info", JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    // LOGIN
                    case 2:
                        // Visualização de BDD
                        UsuarioDAO usuarioDAO1 = new UsuarioDAO(con);
                        ArrayList<Usuario> resultado = usuarioDAO1.listarCadastrados();
                        if (resultado != null) {
                            String listagem = "";
                            for (Usuario usuario1 : resultado) {
                                listagem += "ID: " + usuario1.getIdUsuario() + " Nome: " + usuario1.getNome() + "\n\n";
                            }
                            JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        // Pedida e armazenamento de dados cadastrados
                        idUsuario2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário que deseja efetuar o LOGIN:"));
                        String email = JOptionPane.showInputDialog("Informe o email cadastrado: ");
                        String senha = JOptionPane.showInputDialog("Informe a senha cadastrado: ");
                        // Novos objetos de consulta e exibição
                        Usuario usuarioLogado = usuarioDAO1.buscarUsuario(idUsuario2, email, senha);
                        // Verificação de login
                        if (usuarioLogado == null) {
                            JOptionPane.showMessageDialog(null, "Email ou Senha Inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                            continue;
                        } else {
                            // Boas vindas
                            JOptionPane.showMessageDialog(null, "Seja Bem Vindo à PostUp\n" + usuarioLogado.getNome());
                        }
                        do {
                            // menu principal
                            auxiliar = JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação\n6 - Logout");
                            escolha = Integer.parseInt(auxiliar);
                            // estrutura condicional avaliando a escolha do usuario
                            switch (escolha){
                                // USUÁRIO
                                case 1:
                                    // Menu secundário
                                    auxiliar = JOptionPane.showInputDialog("USUÀRIO\n1 - Editar email\n2 - Editar senha");
                                    escolha = Integer.parseInt(auxiliar);
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // EDITAR EMAIL
                                        case 1:
                                            // Chamada de metodo alterarSenha
                                            usuarioLogado.alterarEmail();
                                            usuarioDAO1.alterarEmail(usuarioLogado);
                                            // Exibição de Usuário
                                            exibicao = usuarioDAO1.exibir(usuarioLogado, usuarioLogado.getIdUsuario());
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro()), "Info", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EDITAR SENHA
                                        case 2:
                                            // Chamada de metodo alterarEmail
                                            usuarioLogado.alterarSenha();
                                            usuarioDAO1.alterarSenha(usuarioLogado);
                                            // Exibição de Usuário
                                            exibicao = usuarioDAO1.exibir(usuarioLogado, usuarioLogado.getIdUsuario());
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro()), "Info", JOptionPane.INFORMATION_MESSAGE);
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // POSTAGEM
                                case 2:
                                    // Menu segundário
                                    auxiliar = JOptionPane.showInputDialog("POSTAGEM\n1 - Criar Postagem\n2 - Editar Postagem\n3 - Remover Postagem");
                                    escolha = Integer.parseInt(auxiliar);
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // CRIAR POSTAGEM
                                        case 1:
                                            // Objeto para realizar as operações
                                            PostagemDAO postagemDAO = new PostagemDAO(con);
                                            // Armazenamento de próximo id dentro do idUsuario
                                            idPostagem = postagemDAO.obterProximoId();
                                            // Objeto para realizar operações
                                            Postagem postagem = new Postagem();
                                            // Cadastro de usuário
                                            postagem.criarPostagem(idPostagem);
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, postagemDAO.inserir(postagem), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Atribuição de objetos de teste às listas
                                            postagens.add(postagem);
                                            postagensDAO.add(postagemDAO);
                                            // Exibição de Postagem
                                            // postagens.get(contador2 - 1).exibir(usuarios, 0, id, postagens, contador2, 0, null, 0, null, null);
                                            // contador2++;
                                            break;
                                        // EDITAR POSTAGEM
                                        case 2:
                                            // Validação
                                            if (usuarios.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else if (postagens.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else {
                                                // Solicitação de id usuário
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                                id = Integer.parseInt(auxiliar);
                                                // Solicitação de id postagem
                                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser alterada:");
                                                idPostagem = Integer.parseInt(auxiliar);
                                                // Chamada de metodo editarPostagem no objeto postagens de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                                postagens.get(idPostagem - 1).editarPostagem(postagens, idPostagem);
                                                // Exibição de Postagem
                                                postagens.get(contador2 - 1).exibir(usuarios, 0, id, postagens, contador2, 0, null, 0, null, null);
                                                break;
                                            }
                                        // REMOVER POSTAGEM
                                        case 3:
                                            // Validação
                                            if (usuarios.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else if (postagens.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else {
                                                // Solicitação de id usuário
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                                id = Integer.parseInt(auxiliar);
                                                // Solicitação de id postagem
                                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser removida:");
                                                idPostagem = Integer.parseInt(auxiliar);
                                                // Chamada de metodo removerPostagem no objeto postagens de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                                postagens.get(idPostagem - 1).removerPostagem(postagens, idPostagem);
                                                // Exibição de Postagem
                                                postagens.get(contador2 - 1).exibir(usuarios, 0, id, postagens, contador2, 0, null, 0, null, null);
                                                break;
                                            }
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // ARQUIVO
                                case 3:
                                    // Menu secundário
                                    auxiliar = JOptionPane.showInputDialog("ARQUIVO\n1 - Importar Arquivo\n2 - Acessar Câmera");
                                    escolha = Integer.parseInt(auxiliar);
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // IMPORTAR ARQUIVO
                                        case 1:
                                            // Validação
                                            if (usuarios.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else if (postagens.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else {
                                                // Novo elemento na lista arquivos
                                                arquivos.add(new Arquivo());
                                                // Definição de id com base no número de repetições
                                                arquivos.get(contador3 - 1).setIdArquivo(contador3);
                                                // Solicitação de id usuário
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem");
                                                id = Integer.parseInt(auxiliar);
                                                // Solicitação de id postagem
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID da postagem que deseja importar o arquivo:");
                                                idPostagem = Integer.parseInt(auxiliar);
                                                // Chamada de metodo importarArquivo no objeto arquivos de id informado(Só roda se o id do usuario, o id postagem e o id arquivo forem existentes e válidos)
                                                arquivos.get(contador3 - 1).importarArquivo(arquivos, contador3);
                                                // Exibição de Arquivo
                                                arquivos.get(contador3 - 1).exibir(usuarios, 0, id, postagens, 0, idPostagem, arquivos, contador3, null, null);
                                                contador3++;
                                                break;
                                            }
                                        // ACESSAR CÂMERA
                                        case 2:
                                            // Validação
                                            if (usuarios.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else if (postagens.isEmpty()) {
                                                JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                                continue;
                                            } else {
                                                // Novo elemento na lista arquivos
                                                arquivos.add(new Arquivo());
                                                // Definição de id com base no número de repetições
                                                arquivos.get(contador3 - 1).setIdArquivo(contador3);
                                                // Solicitação de id usuário
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem");
                                                id = Integer.parseInt(auxiliar);
                                                // Solicitação de id postagem
                                                auxiliar = JOptionPane.showInputDialog("Informe o ID da postagem que deseja acessar a cãmera:");
                                                idPostagem = Integer.parseInt(auxiliar);
                                                // Chamada de metodo acessarCamera() no objeto arquivos de id informado(Só roda se o id do usuario, o id postagem e o id arquivo forem existentes e válidos)
                                                arquivos.get(contador3 - 1).acessarCamera(usuarios, id, postagens, idPostagem, arquivos, contador3);
                                                // Exibição de Arquivo
                                                arquivos.get(contador3 - 1).exibir(usuarios, 0, id, postagens, 0, idPostagem, arquivos, contador3, null, null);
                                                contador3++;
                                                break;
                                            }
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // AVALIACAO
                                case 4:
                                    // Validação
                                    if (usuarios.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else if (postagens.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else if (arquivos.isEmpty()){
                                        JOptionPane.showMessageDialog(null, "Não há arquivos anexados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else {
                                        // Novo elemento na lista avaliacoes
                                        avaliacoes.add(new Avaliacao());
                                        // Solicitação de id postagem
                                        auxiliar = JOptionPane.showInputDialog("ID da postagem a ser avaliada: ");
                                        idPostagem = Integer.parseInt(auxiliar);
                                        // Chamada de metodo avaliarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                        avaliacoes.getFirst().avaliarPostagem(avaliacoes, arquivos, postagens, idPostagem);
                                        // Chamada de metodo mostrarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                        avaliacoes.getFirst().exibir(usuarios, 0,0, postagens, 0, idPostagem, arquivos, contador3, avaliacoes, null);
                                        break;
                                    }
                                // PONTUAÇÂO
                                case 5:
                                    if (usuarios.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else if (postagens.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Não há postagens criadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else if (avaliacoes.isEmpty()){
                                        JOptionPane.showMessageDialog(null, "Não há postagens avaliadas", "ERRO", JOptionPane.ERROR_MESSAGE);
                                        continue;
                                    } else {
                                        // criação e instanciação do objeto pontuação
                                        Pontuacao pontuacao = new Pontuacao();
                                        // Solicitação de id usuário
                                        auxiliar = JOptionPane.showInputDialog("ID do usuário: ");
                                        id = Integer.parseInt(auxiliar);
                                        // Solicitação de id postagem
                                        auxiliar = JOptionPane.showInputDialog("ID da postagem que deseja ver a pontuação");
                                        idPostagem = Integer.parseInt(auxiliar);
                                        // Chamada de metodo calcularPontuacao() no objeto pontuacao de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                        pontuacao.calcularPontuacao(pontuacao, avaliacoes, usuarios, id, postagens, idPostagem);
                                        // Chamada de metodo exibirPontuacao() no objeto pontuacao de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                        pontuacao.exibir(usuarios, 0, id, postagens, 0, idPostagem, null, 0, avaliacoes, pontuacao);
                                        break;
                                    }
                                // LOGOUT
                                case 6:
                                    JOptionPane.showMessageDialog(null, "Redirecionando...", "Alerta", JOptionPane.WARNING_MESSAGE);
                                    continue;
                                default:
                                    throw new Exception("Escolha inválida");
                            }
                        } while (escolha != 6);
                    // SAIR
                    case 3:
                        ConnectionFactory.fecharConexao(con);
                        JOptionPane.showMessageDialog(null, "Encerrando...", "Atenção", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        throw new Exception("Opção Inválida!");
                }
            } catch (Exception e) {
                // Mensagem de erro
                JOptionPane.showMessageDialog(null, e.getMessage());
                break;
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
        // Fechar conexão
        ConnectionFactory.fecharConexao(con);
        // Fim do programa
        JOptionPane.showMessageDialog(null, "Programa encerrado.");
    }
}
