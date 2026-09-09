package br.com.fiap.main;

import br.com.fiap.bean.*;
import br.com.fiap.dao.*;

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
        int escolha, idPostagem, idUsuario;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Connection con = ConnectionFactory.abrirConexao();
        // laço de controle de execução
        do{
            // Tratamento de errps
            try {
                escolha = Integer.parseInt(JOptionPane.showInputDialog("PostUp\n1 - Cadastro\n2 - Login\n3 - Sair"));
                switch (escolha){
                    // Cadastro
                    case 1:
                        // Objetos para realizar as operações
                        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
                        Usuario usuario = new Usuario();
                        Usuario exibicao;
                        // Armazenamento de próximo id dentro do idUsuario
                        idUsuario = usuarioDAO.obterProximoId();
                        // Cadastro de usuário
                        usuario.cadastrarUsuario(idUsuario);
                        // Inserção no banco
                        JOptionPane.showMessageDialog(null, usuarioDAO.inserir(usuario), "Alerta",JOptionPane.WARNING_MESSAGE);
                        // Exibição de objeto
                        exibicao = usuarioDAO.exibir(usuario, idUsuario);
                        JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro().format(dtf)), "Info", JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    // LOGIN
                    case 2:
                        // Objetos de operações
                        UsuarioDAO usuarioDAO1 = new UsuarioDAO(con);
                        Usuario usuarioLogado;
                        ArrayList<Usuario> resultado = usuarioDAO1.listarCadastrados();
                        // Visualização de BDD
                        if (resultado != null) {
                            String listagem = "";
                            for (Usuario loop : resultado) {
                                listagem += "ID: " + loop.getIdUsuario() + "\nNome: " + loop.getNome() + "\n\n";
                            }
                            JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        // Pedida e armazenamento de dados cadastrados
                        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário que deseja efetuar o LOGIN:"));
                        String email = JOptionPane.showInputDialog("Informe o email cadastrado: ");
                        String senha = JOptionPane.showInputDialog("Informe a senha cadastrado: ");
                        // Novos objetos de consulta e exibição
                        usuarioLogado = usuarioDAO1.buscarUsuario(idUsuario, email, senha);
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
                            escolha = Integer.parseInt(JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação\n6 - Logout"));
                            // estrutura condicional avaliando a escolha do usuario
                            switch (escolha){
                                // USUÁRIO
                                case 1:
                                    // Menu secundário
                                    escolha = Integer.parseInt(JOptionPane.showInputDialog("USUÀRIO\n1 - Editar email\n2 - Editar senha"));
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // EDITAR EMAIL
                                        case 1:
                                            // Chamada de metodo alterarSenha
                                            usuarioLogado.alterarEmail();
                                            usuarioDAO1.alterarEmail(usuarioLogado);
                                            // Exibição de Usuário
                                            exibicao = usuarioDAO1.exibir(usuarioLogado, usuarioLogado.getIdUsuario());
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro().format(dtf)), "Info", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EDITAR SENHA
                                        case 2:
                                            // Chamada de metodo alterarEmail
                                            usuarioLogado.alterarSenha();
                                            usuarioDAO1.alterarSenha(usuarioLogado);
                                            // Exibição de Usuário
                                            exibicao = usuarioDAO1.exibir(usuarioLogado, usuarioLogado.getIdUsuario());
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nEmail: %s\nData de Cadastro: %s", exibicao.getIdUsuario(), exibicao.getNome(), exibicao.getEmail(), exibicao.getDataCadastro().format(dtf)), "Info", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // POSTAGEM
                                case 2:
                                    // Menu segundário
                                    escolha = Integer.parseInt(JOptionPane.showInputDialog("POSTAGEM\n1 - Criar Postagem\n2 - Editar Postagem\n3 - Remover Postagem"));
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // CRIAR POSTAGEM
                                        case 1:
                                            // Objeto para realizar as operações
                                            PostagemDAO postagemDAO = new PostagemDAO(con);
                                            Postagem postagem = new Postagem();
                                            Postagem exibicaoPost;
                                            // Armazenamento de próximo id dentro do idUsuario
                                            idPostagem = postagemDAO.obterProximoId();
                                            // Cadastro de usuário
                                            postagem.criarPostagem(idPostagem);
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, postagemDAO.inserir(postagem), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Exibição de Postagem
                                            exibicaoPost = postagemDAO.exibir(postagem, idPostagem);
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nTítulo: %s\nDescrição: %s\nData: %s", exibicaoPost.getIdPostagem(), exibicaoPost.getTitulo(), exibicaoPost.getDescricao(), exibicaoPost.getDataPostagem().format(dtf)), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EDITAR POSTAGEM
                                        case 2:
                                            // Exibição de lista de posts Cadastrados
                                            // Visualização de BDD
                                            // Objetos para operação
                                            Postagem postagem2 = new Postagem();
                                            PostagemDAO postagemDAO2 = new PostagemDAO(con);
                                            ArrayList<Postagem> resultadoPost = postagemDAO2.listarCadastrados();
                                            if (resultadoPost != null) {
                                                String listagem = "";
                                                for (Postagem loop : resultadoPost) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Solicitação de id postagem
                                            int idPostagem2 = Integer.parseInt(JOptionPane.showInputDialog("informe o ID da postagem a ser alterada:"));
                                            // Alteração de Postagem direto no banco
                                            postagem2.editarPostagem();
                                            postagemDAO2.alterar(postagem2, idPostagem2);
                                            // Exibição de Postagem2
                                            break;
                                        // REMOVER POSTAGEM
                                        case 3:
                                            // Exibição de lista de posts Cadastrados
                                            // Visualização de BDD
                                            PostagemDAO postagemDAO3 = new PostagemDAO(con);
                                            ArrayList<Postagem> resultadoPost2 = postagemDAO3.listarCadastrados();
                                            if (resultadoPost2 != null) {
                                                String listagem = "";
                                                for (Postagem loop : resultadoPost2) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Solicitação de id postagem
                                            int idPostagem3 = Integer.parseInt(JOptionPane.showInputDialog("informe o ID da postagem a ser removida:"));
                                            // Excluindo direto no banco
                                            postagemDAO3.excluir(idPostagem3);
                                            // Lista Atualizada
                                            resultadoPost2 = postagemDAO3.listarCadastrados();
                                            if (resultadoPost2 != null) {
                                                String listagem = "";
                                                for (Postagem loop : resultadoPost2) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            break;
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // ARQUIVO
                                case 3:
                                    // Menu secundário
                                    escolha = Integer.parseInt(JOptionPane.showInputDialog("ARQUIVO\n1 - Importar Arquivo\n2 - Acessar Câmera"));
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // IMPORTAR ARQUIVO
                                        case 1:
                                            // Objetos de operações
                                            ArquivoDAO arquivoDAO = new ArquivoDAO(con);
                                            Arquivo arquivo = new Arquivo();
                                            Arquivo exibicaoArquivo;
                                            PostagemDAO postagemDAO4 = new PostagemDAO(con);
                                            ArrayList<Postagem> resultadoPost3 = postagemDAO4.listarCadastrados();
                                            if (resultadoPost3 != null) {
                                                String listagem = "";
                                                for (Postagem loop : resultadoPost3) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Pedida de id para adicionar arquivos
                                            int idPostagem4 = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID da postagem que deseja adicionar um arquivo?"));
                                            // Armazenamento de próximo id dentro do idUsuario
                                            int idArquivo = arquivoDAO.obterProximoId();
                                            // Cadastro de arquivo
                                            arquivo.importarArquivo(idArquivo);
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, arquivoDAO.inserir(arquivo), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Alteração de FK no banco
                                            postagemDAO4.alterarFKArquivo(idPostagem4, idArquivo);
                                            // Exibição de Postagem
                                            exibicaoArquivo = arquivoDAO.exibir(arquivo, idArquivo);
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nTipo: %s\nTamanho: %s\nURL: %s\nData Upload: %s", exibicaoArquivo.getIdArquivo(), exibicaoArquivo.getNomeArquivo(), exibicaoArquivo.getTipo(), exibicaoArquivo.getTamanho(), exibicaoArquivo.getUrl(), exibicaoArquivo.getDataUpload().format(dtf)), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // ACESSAR CÂMERA
                                        case 2:
                                            // Objetos de operações
                                            ArquivoDAO arquivoDAO2 = new ArquivoDAO(con);
                                            Arquivo arquivo2 = new Arquivo();
                                            Arquivo exibicaoArquivo2;
                                            PostagemDAO postagemDAO5 = new PostagemDAO(con);
                                            ArrayList<Postagem> resultadoPost4 = postagemDAO5.listarCadastrados();
                                            if (resultadoPost4 != null) {
                                                String listagem = "";
                                                for (Postagem loop : resultadoPost4) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Pedida de id para adicionar arquivos
                                            int idPostagem5 = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID da postagem que deseja adicionar um arquivo?"));
                                            // Armazenamento de próximo id dentro do idUsuario
                                            int idArquivo2 = arquivoDAO2.obterProximoId();
                                            // Chamada de metodo acessarCamera() no objeto arquivos de id informado(Só roda se o id do usuario, o id postagem e o id arquivo forem existentes e válidos)
                                            arquivo2.acessarCamera(idArquivo2);
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, arquivoDAO2.inserir(arquivo2), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Alteração de FK no banco
                                            postagemDAO5.alterarFKArquivo(idPostagem5, idArquivo2);
                                            // Exibição de Postagem
                                            exibicaoArquivo2 = arquivoDAO2.exibir(arquivo2, idArquivo2);
                                            JOptionPane.showMessageDialog(null, String.format("ID: %d\nNome: %s\nTipo: %s\nTamanho: %s\nURL: %s\nData Upload: %s", exibicaoArquivo2.getIdArquivo(), exibicaoArquivo2.getNomeArquivo(), exibicaoArquivo2.getTipo(), exibicaoArquivo2.getTamanho(), exibicaoArquivo2.getUrl(), exibicaoArquivo2.getDataUpload().format(dtf)), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // AVALIACAO
                                case 4:
                                    // Objetos de teste
                                    Postagem postagemAvaliada;
                                    Arquivo arquivoAvaliado;
                                    Arquivo arquivo3 = new Arquivo();
                                    ArquivoDAO arquivoDAO3 = new ArquivoDAO(con);
                                    Avaliacao avaliacao = new Avaliacao();
                                    AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(con);
                                    Avaliacao exibirAvaliacao;
                                    // Exibição de Lista de postagens disponíveis
                                    PostagemDAO postagemDAO6 = new PostagemDAO(con);
                                    ArrayList<Postagem> resultadoPost5 = postagemDAO6.listarCadastrados();
                                    if (resultadoPost5 != null) {
                                        String listagem = "";
                                        for (Postagem loop : resultadoPost5) {
                                            listagem += "ID: " + loop.getIdPostagem() + " Título: " + loop.getTitulo() + "\n\n";
                                        }
                                        JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                    // Solicitação de id postagem
                                    int idPostagem6 = Integer.parseInt(JOptionPane.showInputDialog("ID da postagem a ser avaliada: "));
                                    // Criação de objetos a serem avaliados
                                    postagemAvaliada = postagemDAO6.exibir(new Postagem(), idPostagem6);
                                    int idArquivo3 = (postagemDAO6.exibirFKArquivo(idPostagem6));
                                    arquivoAvaliado = arquivoDAO3.exibir(arquivo3, idArquivo3);
                                    int qtdPostagens = postagemDAO6.contarPostagens();
                                    // Obter próximo id
                                    int idAvaliacao = avaliacaoDAO.obterProximoId();
                                    // Chamada de metodo avaliarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                    avaliacao.avaliarPostagem(postagemAvaliada, arquivoAvaliado, qtdPostagens, idAvaliacao);
                                    // Inserção no banco
                                    JOptionPane.showMessageDialog(null, avaliacaoDAO.inserir(avaliacao), "Alerta",JOptionPane.WARNING_MESSAGE);
                                    // Alteração de FK no banco
                                    postagemDAO6.alterarFKAvaliacao(idPostagem6, idAvaliacao);
                                    // Exibição de avaliacao
                                    exibirAvaliacao = avaliacaoDAO.exibir(avaliacao, idAvaliacao);
                                    JOptionPane.showMessageDialog(null, String.format("ID: %d\nCritério: %s\nNota Impacto: %d\nNota Dificuldade: %d\nNota Confiabilidade: %d\nNota Frequência: %d\nData: %s", exibirAvaliacao.getIdAvaliacao(), exibirAvaliacao.getCriterio(), exibirAvaliacao.getNotaImpacto(), exibirAvaliacao.getNotaDificuldade(), exibirAvaliacao.getNotaConfiabilidade(), exibirAvaliacao.getNotaFrequencia(), exibirAvaliacao.getDataAvaliacao().format(dtf)), "Info", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                // PONTUAÇÂO
                                case 5:
                                    // Objetos de teste
                                    Avaliacao avaliacao2;
                                    AvaliacaoDAO avaliacaoDAO2 = new AvaliacaoDAO(con);
                                    Pontuacao pontuacao = new Pontuacao();
                                    PontuacaoDAO pontuacaoDAO = new PontuacaoDAO(con);
                                    Pontuacao exibirPontuacao;
                                    // Exibição de Lista de postagens disponíveis
                                    PostagemDAO postagemDAO7 = new PostagemDAO(con);
                                    ArrayList<Postagem> resultadoPost6 = postagemDAO7.listarCadastrados();
                                    if (resultadoPost6 != null) {
                                        String listagem = "";
                                        for (Postagem loop : resultadoPost6) {
                                            listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                        }
                                        JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                    // Solicitação de id postagem
                                    int idPostagem7 = Integer.parseInt(JOptionPane.showInputDialog("ID da postagem que deseja ver a pontuação: "));
                                    // Criação de objetos para visualização de pontuação
                                    int idAvaliacao2 = postagemDAO7.exibirFKAvaliacao(idPostagem7);
                                    avaliacao2 = avaliacaoDAO2.exibir(new Avaliacao(), idAvaliacao2);
                                    int idPontuacao = pontuacaoDAO.obterProximoId();
                                    // Chamada de metodo calcularPontuacao() no objeto pontuacao de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                    pontuacao.calcularPontuacao(avaliacao2, idPontuacao);
                                    // Inserção no banco
                                    JOptionPane.showMessageDialog(null, pontuacaoDAO.inserir(pontuacao), "Info", JOptionPane.INFORMATION_MESSAGE);
                                    // Alteração de FK no banco
                                    postagemDAO7.alterarFKPontuacao(idPostagem7, idPontuacao);
                                    // Exibição de pontuação
                                    exibirPontuacao = pontuacaoDAO.exibir(new Pontuacao(), idPontuacao);
                                    JOptionPane.showMessageDialog(null, String.format("ID: %d\nValor: %.2f\nData: %s", exibirPontuacao.getIdPontuacao(), exibirPontuacao.getValor(), exibirPontuacao.getDataPontuacao().format(dtf)), "Pontuação", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                // LOGOUT
                                case 6:
                                    JOptionPane.showMessageDialog(null, "Redirecionando...", "Alerta", JOptionPane.WARNING_MESSAGE);
                                    escolha = 0;
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
