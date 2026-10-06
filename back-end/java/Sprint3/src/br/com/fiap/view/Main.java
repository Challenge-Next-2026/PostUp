package br.com.fiap.view;

import br.com.fiap.controller.PostUpController;
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
        PostUpController controller = new PostUpController(con);
        // laço de controle de execução
        do{
            // Tratamento de errps
            try {
                escolha = Integer.parseInt(JOptionPane.showInputDialog("PostUp\n1 - Cadastro\n2 - Login\n3 - Sair"));
                switch (escolha){
                    // Cadastro
                    case 1:
                        String[] resultado = controller.cadastrarUsuario();
                        // Resultado Cadastro
                        JOptionPane.showMessageDialog(null, resultado[0], "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        // Resultado INSERT
                        JOptionPane.showMessageDialog(null, resultado[1], "Inserção", JOptionPane.WARNING_MESSAGE);
                        // Resultado SELECT
                        JOptionPane.showMessageDialog(null, resultado[2], "Exibição", JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    // LOGIN
                    case 2:
                        // Objetos de operações
                        Usuario usuarioLogado;
                        ArrayList<Usuario> listarUsuarios = controller.listarUsuarios();
                        // Visualização de BDD
                        if (listarUsuarios != null) {
                            String listagem = "";
                            for (Usuario loop : listarUsuarios) {
                                listagem += "ID: " + loop.getIdUsuario() + "\nNome: " + loop.getNome() + "\n\n";
                            }
                            JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não há usuários cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        // Pedida e armazenamento de dados cadastrados
                        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário:"));
                        String email = JOptionPane.showInputDialog("Informe o email:");
                        String senha = JOptionPane.showInputDialog("Informe a senha:");
                        usuarioLogado = controller.realizarLogin(idUsuario, email, senha);
                        // Verificação
                        if (usuarioLogado == null) {
                            JOptionPane.showMessageDialog(null, "Email ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                            continue;
                        } else {
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
                                            // Chamada de metodo alterar
                                            JOptionPane.showMessageDialog(null, usuarioLogado.alterar(), "Alteração", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, controller.alterarUsuario(usuarioLogado), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            break;
                                        // EXIBIR
                                        case 2:
                                            // Exibição de Usuário
                                            JOptionPane.showMessageDialog(null, controller.exibirUsuario(usuarioLogado), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EXCLUIR
                                        case 3:
                                            Usuario usuarioExcluido = new Usuario();
                                            listarUsuarios = controller.listarUsuarios();
                                            // Visualização de BDD
                                            if (listarUsuarios != null) {
                                                String listagem = "";
                                                for (Usuario loop : listarUsuarios) {
                                                    listagem += "ID: " + loop.getIdUsuario() + "\nNome: " + loop.getNome() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há usuários cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            usuarioExcluido.setIdUsuario(Integer.parseInt(JOptionPane.showInputDialog("Informe o id do Usuário que deseja remover:")));
                                            JOptionPane.showMessageDialog(null, controller.excluirUsuario(usuarioExcluido), "Excluir", JOptionPane.WARNING_MESSAGE);
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
                                            // Armazenamento de resultado
                                            resultado = controller.criarPostagem();
                                            // Exibição
                                            // Cadastro na memória
                                            JOptionPane.showMessageDialog(null, resultado[0], "Postagem", JOptionPane.INFORMATION_MESSAGE);
                                            // Inserção
                                            JOptionPane.showMessageDialog(null, resultado[1], "Inserção", JOptionPane.WARNING_MESSAGE);
                                            // Exibição
                                            JOptionPane.showMessageDialog(null, resultado[2], "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EDITAR POSTAGEM
                                        case 2:
                                            // Exibição de lista de posts Cadastrados
                                            // Visualização de BDD
                                            // Objetos para operação
                                            Postagem postagem = new Postagem();
                                            ArrayList<Postagem> listaPostagem = controller.listarPostagens();
                                            if (listaPostagem != null) {
                                                String listagem = "";
                                                for (Postagem loop : listaPostagem) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Solicitação de id postagem
                                            postagem.setIdPostagem(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID da postagem a ser alterada:")));
                                            // Alteração de Postagem na memória
                                            JOptionPane.showMessageDialog(null, postagem.editarPostagem(), "Edição", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, controller.alterarPostagem(postagem), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            // Exibição de Postagem2
                                            JOptionPane.showMessageDialog(null, controller.exibirPostagem(postagem), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // REMOVER POSTAGEM
                                        case 3:
                                            // Inicialização de objeto
                                            postagem = new Postagem();
                                            // Exibição de lista de posts Cadastrados
                                            // Visualização de BDD
                                            listaPostagem = controller.listarPostagens();
                                            if (listaPostagem != null) {
                                                String listagem = "";
                                                for (Postagem loop : listaPostagem) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Solicitação de id postagem
                                            postagem.setIdPostagem(Integer.parseInt(JOptionPane.showInputDialog("informe o ID da postagem a ser removida:")));
                                            // Excluindo direto no banco
                                            JOptionPane.showMessageDialog(null, controller.excluirPostagem(postagem), "Excluir", JOptionPane.WARNING_MESSAGE);
                                            // Lista Atualizada
                                            listaPostagem = controller.listarPostagens();
                                            if (listaPostagem != null) {
                                                String listagem = "";
                                                for (Postagem loop : listaPostagem) {
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
                                            // Lista de Postagens
                                            ArrayList<Postagem> listaPostagem = controller.listarPostagens();
                                            if (listaPostagem != null) {
                                                String listagem = "";
                                                for (Postagem loop : listaPostagem) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Pedida de id para adicionar arquivos
                                            idPostagem = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID da postagem que deseja adicionar um arquivo?"));
                                            // Armazenamento de resultado
                                            resultado = controller.inserirArquivo(idPostagem);
                                            // Exibições
                                            JOptionPane.showMessageDialog(null, resultado[0], "Importar", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[1], "Inserção", JOptionPane.WARNING_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[2], "Alteração FK", JOptionPane.WARNING_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[3], "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // ACESSAR CÂMERA
                                        case 2:
                                            // Lista de Postagens
                                            listaPostagem = controller.listarPostagens();
                                            if (listaPostagem != null) {
                                                String listagem = "";
                                                for (Postagem loop : listaPostagem) {
                                                    listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            // Pedida de id para adicionar arquivos
                                            idPostagem = Integer.parseInt(JOptionPane.showInputDialog("Qual o ID da postagem que deseja adicionar um arquivo?"));
                                            // Armazenamento de resultado
                                            resultado = controller.cameraArquivo(idPostagem);
                                            // Exibições
                                            JOptionPane.showMessageDialog(null, resultado[0], "Câmera", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[1], "Inserção", JOptionPane.WARNING_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[2], "Alteração FK", JOptionPane.WARNING_MESSAGE);
                                            JOptionPane.showMessageDialog(null, resultado[3], "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // ALTERAR
                                        case 3:
                                            Arquivo arquivoAlterado = new Arquivo();
                                            // Lista de Arquivos
                                            ArrayList<Arquivo> listaArquivo = controller.listarArquivos();
                                            if (listaArquivo != null) {
                                                String listagem = "";
                                                for (Arquivo loop : listaArquivo) {
                                                    listagem += "ID: " + loop.getIdArquivo() + "\nNome: " + loop.getNomeArquivo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há arquivos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            arquivoAlterado.setIdArquivo(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do arquivo que deseja alterar:")));
                                            // Exibição
                                            JOptionPane.showMessageDialog(null, arquivoAlterado.alterarArquivo(), "Edição", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, controller.alterarArquivo(arquivoAlterado), "Alteração", JOptionPane.WARNING_MESSAGE);
                                            JOptionPane.showMessageDialog(null, controller.exibirArquivo(arquivoAlterado), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        // EXCLUIR
                                        case 4:
                                            Arquivo arquivoExcluido = new Arquivo();
                                            // Lista de Arquivos
                                            listaArquivo = controller.listarArquivos();
                                            if (listaArquivo != null) {
                                                String listagem = "";
                                                for (Arquivo loop : listaArquivo) {
                                                    listagem += "ID: " + loop.getIdArquivo() + "\nNome: " + loop.getNomeArquivo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há arquivos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            arquivoExcluido.setIdArquivo(Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do arquivo que deseja excluir:")));
                                            JOptionPane.showMessageDialog(null, controller.excluirArquivo(arquivoExcluido), "Excluir", JOptionPane.WARNING_MESSAGE);
                                            // Lista de Arquivos atualizada
                                            listaArquivo = controller.listarArquivos();
                                            if (listaArquivo != null) {
                                                String listagem = "";
                                                for (Arquivo loop : listaArquivo) {
                                                    listagem += "ID: " + loop.getIdArquivo() + "\nNome: " + loop.getNomeArquivo() + "\n\n";
                                                }
                                                JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Não há arquivos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                            break;
                                        default:
                                            throw new Exception("Escolha inválida");
                                    }
                                    break;
                                // AVALIACAO
                                case 4:
                                    // Lista de Postagens
                                    ArrayList<Postagem> listaPostagem = controller.listarPostagens();
                                    if (listaPostagem != null) {
                                        String listagem = "";
                                        for (Postagem loop : listaPostagem) {
                                            listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                        }
                                        JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                    // Solicitação de id postagem
                                    idPostagem = Integer.parseInt(JOptionPane.showInputDialog("ID da postagem a ser avaliada: "));
                                    // Atribuição
                                    Avaliacao avaliacao = controller.avaliarPostagem(idPostagem);
                                    // Exibição
                                    Avaliacao exibicao = controller.exibirAvaliacao(avaliacao);
                                    JOptionPane.showMessageDialog(null, String.format("ID: %d\nCritério: %s\nImpacto: %d\nDificuldade: %d\nConfiabilidade: %d\nFrequência: %d\nData: %s", exibicao.getIdAvaliacao(), exibicao.getCriterio(), exibicao.getNotaImpacto(), exibicao.getNotaDificuldade(), exibicao.getNotaConfiabilidade(), exibicao.getNotaFrequencia(), exibicao.getDataAvaliacao().format(dtf)), "Exibição", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                // PONTUAÇÂO
                                case 5:
                                    // Lista de Postagens
                                    listaPostagem = controller.listarPostagens();
                                    if (listaPostagem != null) {
                                        String listagem = "";
                                        for (Postagem loop : listaPostagem) {
                                            listagem += "ID: " + loop.getIdPostagem() + "\nTítulo: " + loop.getTitulo() + "\n\n";
                                        }
                                        JOptionPane.showMessageDialog(null, listagem, "Lista", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não há postagens cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                    // Solicitação de id postagem
                                    idPostagem = Integer.parseInt(JOptionPane.showInputDialog("ID da postagem que deseja ver a pontuação: "));
                                    // Atribuição
                                    Pontuacao pontuacao = controller.calcularPontuacao(idPostagem);
                                    // Exibição
                                    Pontuacao exibicaoP = controller.exibirPontuacao(pontuacao);
                                    JOptionPane.showMessageDialog(null, String.format("ID: %d\nValor: %f\nData: %s", exibicaoP.getIdPontuacao(), exibicaoP.getValor(), exibicaoP.getDataPontuacao().format(dtf)), "Exibição", JOptionPane.INFORMATION_MESSAGE);
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
