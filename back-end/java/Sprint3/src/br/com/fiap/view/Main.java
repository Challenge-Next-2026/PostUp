package br.com.fiap.view;

import br.com.fiap.model.dto.*;
import br.com.fiap.model.dao.*;

import javax.swing.*;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                        usuario.setIdUsuario(usuarioDAO.obterProximoId());
                        // Cadastro de usuário
                        usuario.cadastrarUsuario();
                        // Inserção no banco
                        JOptionPane.showMessageDialog(null, usuarioDAO.inserir(usuario), "Alerta",JOptionPane.WARNING_MESSAGE);
                        // Exibição de objeto
                        JOptionPane.showMessageDialog(null, usuarioDAO.exibir(usuario), "Exibição", JOptionPane.INFORMATION_MESSAGE);
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
                                    escolha = Integer.parseInt(JOptionPane.showInputDialog("USUÀRIO\n1 - Editar\n2 - Exibir\n3 - Excluir"));
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // EDITAR
                                        case 1:
                                            // Chamada de metodo alterarSenha
                                            usuarioLogado.alterar();
                                            JOptionPane.showMessageDialog(null, usuarioDAO1.alterar(usuarioLogado), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            break;
                                        // EXIBIR
                                        case 2:
                                            // Exibição de Usuário
                                            JOptionPane.showMessageDialog(null, usuarioDAO1.exibir(usuarioLogado), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EXCLUIR
                                        case 3:
                                            Usuario usuarioExcluido = new Usuario();
                                            resultado = usuarioDAO1.listarCadastrados();
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
                                            usuarioExcluido.setIdUsuario(Integer.parseInt(JOptionPane.showInputDialog("Informe o id do Usuário que deseja remover:")));
                                            JOptionPane.showMessageDialog(null, usuarioDAO1.excluir(usuarioExcluido), "Excluir", JOptionPane.WARNING_MESSAGE);
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
                                            // Armazenamento de próximo id dentro do idUsuario
                                            postagem.setIdPostagem(postagemDAO.obterProximoId());
                                            // Cadastro de usuário
                                            postagem.criarPostagem();
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, postagemDAO.inserir(postagem), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Exibição de Postagem
                                            JOptionPane.showMessageDialog(null, postagemDAO.exibir(postagem), "Exibição", JOptionPane.INFORMATION_MESSAGE);
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
                                            postagem2.setIdPostagem(Integer.parseInt(JOptionPane.showInputDialog("informe o ID da postagem a ser alterada:")));
                                            // Alteração de Postagem direto no banco
                                            postagem2.editarPostagem();
                                            JOptionPane.showMessageDialog(null, postagemDAO2.alterar(postagem2), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            // Exibição de Postagem2
                                            JOptionPane.showMessageDialog(null, postagemDAO2.exibir(postagem2), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // REMOVER POSTAGEM
                                        case 3:
                                            // Exibição de lista de posts Cadastrados
                                            // Visualização de BDD
                                            PostagemDAO postagemDAO3 = new PostagemDAO(con);
                                            Postagem postagem3 = new Postagem();
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
                                            postagem3.setIdPostagem(Integer.parseInt(JOptionPane.showInputDialog("informe o ID da postagem a ser removida:")));
                                            // Excluindo direto no banco
                                            JOptionPane.showMessageDialog(null, postagemDAO3.excluir(postagem3), "Excluir", JOptionPane.WARNING_MESSAGE);
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
                                    escolha = Integer.parseInt(JOptionPane.showInputDialog("ARQUIVO\n1 - Importar Arquivo\n2 - Acessar Câmera\n3 - Alterar\n4 - Excluir"));
                                    // estrutura condicional avaliando a escolha do usuario
                                    switch (escolha){
                                        // IMPORTAR ARQUIVO
                                        case 1:
                                            // Objetos de operações
                                            ArquivoDAO arquivoDAO = new ArquivoDAO(con);
                                            Arquivo arquivo = new Arquivo();
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
                                            arquivo.setIdArquivo(arquivoDAO.obterProximoId());
                                            // Cadastro de arquivo
                                            arquivo.importarArquivo();
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, arquivoDAO.inserir(arquivo), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Alteração de FK no banco
                                            postagemDAO4.alterarFKArquivo(idPostagem4, arquivo.getIdArquivo());
                                            // Exibição de Postagem
                                            JOptionPane.showMessageDialog(null, arquivoDAO.exibir(arquivo), "Exibição", JOptionPane.INFORMATION_MESSAGE);
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
                                            arquivo2.setIdArquivo(arquivoDAO2.obterProximoId());
                                            // Chamada de metodo acessarCamera() no objeto arquivos de id informado(Só roda se o id do usuario, o id postagem e o id arquivo forem existentes e válidos)
                                            arquivo2.acessarCamera(arquivo2.getIdArquivo());
                                            // Inserção no banco
                                            JOptionPane.showMessageDialog(null, arquivoDAO2.inserir(arquivo2), "Alerta",JOptionPane.WARNING_MESSAGE);
                                            // Alteração de FK no banco
                                            postagemDAO5.alterarFKArquivo(idPostagem5, arquivo2.getIdArquivo());
                                            // Exibição de Postagem
                                            JOptionPane.showMessageDialog(null, arquivoDAO2.exibir(arquivo2), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // ALTERAR
                                        case 3:
                                            Arquivo arquivoAlterado = new Arquivo();
                                            ArquivoDAO arquivoDAO1 = new ArquivoDAO(con);
                                            ArrayList<Arquivo> lista = arquivoDAO1.listarCadastrados();
                                            if (lista != null) {
                                                String listagem = "";
                                                for (Arquivo loop : lista) {
                                                    listagem += "ID: " + loop.getIdArquivo() + "\nNome: " + loop.getNomeArquivo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há arquivos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            arquivoAlterado.setIdArquivo(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do arquivo que deseja alterar:")));
                                            arquivoAlterado.alterarArquivo();
                                            JOptionPane.showMessageDialog(null, arquivoDAO1.alterar(arquivoAlterado), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            break;
                                        // EXCLUIR
                                        case 4:
                                            Arquivo arquivoExcluido = new Arquivo();
                                            arquivoDAO1 = new ArquivoDAO(con);
                                            lista = arquivoDAO1.listarCadastrados();
                                            if (lista != null) {
                                                String listagem = "";
                                                for (Arquivo loop : lista) {
                                                    listagem += "ID: " + loop.getIdArquivo() + "\nNome: " + loop.getNomeArquivo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há arquivos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            arquivoExcluido.setIdArquivo(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do arquivo que deseja excluir:")));
                                            JOptionPane.showMessageDialog(null, arquivoDAO1.excluir(arquivoExcluido), "Excluir", JOptionPane.WARNING_MESSAGE);
                                            break;
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // AVALIACAO
                                case 4:
                                    // Objetos de teste
                                    Postagem postagemAvaliada = new Postagem();
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
                                    postagemAvaliada.setIdPostagem(Integer.parseInt(JOptionPane.showInputDialog("ID da postagem a ser avaliada: ")));
                                    // Criação de objetos a serem avaliados
                                    JOptionPane.showMessageDialog(null, postagemDAO6.exibir(postagemAvaliada), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                    arquivo3.setIdArquivo((postagemDAO6.exibirFKArquivo(postagemAvaliada.getIdPostagem())));
                                    JOptionPane.showMessageDialog(null, arquivoDAO3.exibir(arquivo3), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                    int qtdPostagens = postagemDAO6.contarPostagens();
                                    // Obter próximo id
                                    int idAvaliacao = avaliacaoDAO.obterProximoId();
                                    // Chamada de metodo avaliarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                    avaliacao.avaliarPostagem(postagemAvaliada, arquivo3, qtdPostagens, idAvaliacao);
                                    // Inserção no banco
                                    JOptionPane.showMessageDialog(null, avaliacaoDAO.inserir(avaliacao), "Alerta",JOptionPane.WARNING_MESSAGE);
                                    // Alteração de FK no banco
                                    postagemDAO6.alterarFKAvaliacao(postagemAvaliada.getIdPostagem(), idAvaliacao);
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
                                    break;
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
