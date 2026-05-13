package br.com.fiap.main;

import br.com.fiap.bean.*;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        // Variáveis, Listas e objetos de apoio
        int escolha, id, idPostagem, contador = 1, contador2 = 1, contador3 = 1;
        String auxiliar, opcao = "sim";
        List <Usuario> usuarios = new ArrayList<>();
        List <Postagem> postagens = new ArrayList<>();
        List <Arquivo> arquivos = new ArrayList<>();
        List <Avaliacao> avaliacoes = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // laço de controle de execução
        while (opcao.equalsIgnoreCase("sim")){
            // Tratamento de errps
            try {
                // menu principal
                auxiliar = JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação");
                escolha = Integer.parseInt(auxiliar);
                // estrutura condicional avaliando a escolha do usuario
                switch (escolha){
                    // USUÁRIO
                    case 1:
                        // Novo elemento na lista usuarios
                        usuarios.add(new Usuario());
                        // Menu secindário
                        auxiliar = JOptionPane.showInputDialog("1 - Cadastrar\n2 - Editar email\n3 - Editar senha");
                        escolha = Integer.parseInt(auxiliar);
                        // estrutura condicional avaliando a escolha do usuario
                        switch (escolha){
                            // CADASTRAR
                            case 1:
                                // Definição de id com base no número de repetições
                                usuarios.get(contador - 1).setIdUsuario(contador);
                                // Chamado de metodo cadastrarusuario()
                                usuarios.get(contador - 1).cadastrarUsuario();
                                // Exibição de Usuário
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(contador - 1).getIdUsuario(), usuarios.get(contador - 1).getNome(), usuarios.get(contador - 1).getEmail(), usuarios.get(contador - 1).getSenha(), usuarios.get(contador - 1).getDataCadastro().format(dtf)));
                                contador++;
                                break;
                            // EDITAR EMAIL
                            case 2:
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                id = Integer.parseInt(auxiliar);
                                // Chamada de metodo alterarEmail no objeto usuario de id informado
                                usuarios.get(id - 1).alterarEmail(usuarios, id);
                                // Exibição de Usuário
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), usuarios.get(id - 1).getEmail(), usuarios.get(id - 1).getSenha(), usuarios.get(id - 1).getDataCadastro().format(dtf)));
                                break;
                            // EDITAR EMAIL
                            case 3:
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                id = Integer.parseInt(auxiliar);
                                // Chamada de metodo alterarSenha no objeto usuario de id informado
                                usuarios.get(id - 1).alterarSenha(usuarios, id);
                                // Exibição de Usuário
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), usuarios.get(id - 1).getEmail(), usuarios.get(id - 1).getSenha(), usuarios.get(id - 1).getDataCadastro().format(dtf)));
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    // POSTAGEM
                    case 2:
                        // Novo elemento na lista postagens
                        postagens.add(new Postagem());
                        // Menu segundário
                        auxiliar = JOptionPane.showInputDialog("1 - Criar Postagem\n2 - Editar Postagem\n3 - Remover Postagem");
                        escolha = Integer.parseInt(auxiliar);
                        // estrutura condicional avaliando a escolha do usuario
                        switch (escolha){
                            // CRIAR POSTAGEM
                            case 1:
                                // Definição de id com base no número de repetições
                                postagens.get(contador2 - 1).setIdPostagem(contador2);
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que deseja fazer uma postagem:");
                                id = Integer.parseInt(auxiliar);
                                // Chamada de metodo criarPostagem no objeto postagens de id informado(Só roda se o id do usuario for existente e válido)
                                postagens.get(contador2 - 1).criarPostagem(usuarios, id, postagens, contador2);
                                // Exibição de Postagem
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(contador2 - 1).getIdPostagem(), postagens.get(contador2 - 1).getTitulo(), postagens.get(contador2 - 1).getDescricao(), postagens.get(contador2 - 1).getDataPostagem().format(dtf), postagens.get(contador2 - 1).getStatus()));
                                contador2++;
                                break;
                            // EDITAR POSTAGEM
                            case 2:
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                id = Integer.parseInt(auxiliar);
                                // Solicitação de id postagem
                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser alterada:");
                                idPostagem = Integer.parseInt(auxiliar);
                                // Chamada de metodo editarPostagem no objeto postagens de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                postagens.get(idPostagem - 1).editarPostagem(usuarios, id, postagens, idPostagem);
                                // Exibição de Postagem
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), postagens.get(idPostagem - 1).getDescricao(), postagens.get(idPostagem - 1).getDataPostagem().format(dtf), postagens.get(idPostagem - 1).getStatus()));
                                break;
                            // REMOVER POSTAGEM
                            case 3:
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                id = Integer.parseInt(auxiliar);
                                // Solicitação de id postagem
                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser removida:");
                                idPostagem = Integer.parseInt(auxiliar);
                                // Chamada de metodo removerPostagem no objeto postagens de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                                postagens.get(idPostagem - 1).removerPostagem(usuarios, id, postagens, idPostagem);
                                // Exibição de Postagem
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), postagens.get(idPostagem - 1).getDescricao(), postagens.get(idPostagem - 1).getDataPostagem().format(dtf), postagens.get(idPostagem - 1).getStatus()));
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    // ARQUIVO
                    case 3:
                        // Novo elemento na lista arquivos
                        arquivos.add(new Arquivo());
                        // Menu secundário
                        auxiliar = JOptionPane.showInputDialog("1 - Importar Arquivo\n2 - Acessar Câmera");
                        escolha = Integer.parseInt(auxiliar);
                        // estrutura condicional avaliando a escolha do usuario
                        switch (escolha){
                            // IMPORTAR ARQUIVO
                            case 1:
                                // Definição de id com base no número de repetições
                                arquivos.get(contador3 - 1).setIdArquivo(contador3);
                                // Solicitação de id usuário
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem");
                                id = Integer.parseInt(auxiliar);
                                // Solicitação de id postagem
                                auxiliar = JOptionPane.showInputDialog("Informe o ID da postagem que deseja importar o arquivo:");
                                idPostagem = Integer.parseInt(auxiliar);
                                // Chamada de metodo importarArquivo no objeto arquivos de id informado(Só roda se o id do usuario, o id postagem e o id arquivo forem existentes e válidos)
                                arquivos.get(contador3 - 1).importarArquivo(usuarios, id, postagens, idPostagem, arquivos, contador3);
                                // Exibição de Arquivo
                                JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n----------------\nID Post: %d\nTítulo Post: %s\n-----------------\nID Arquivo: %d\nNome: %s\nTipo: %s\nTamanho: %f\nURL: %s\nData Upload: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), arquivos.get(contador3 - 1).getIdArquivo(), arquivos.get(contador3 - 1).getNomeArquivo(), arquivos.get(contador3 - 1).getTipo(), arquivos.get(contador3 - 1).getTamanho(), arquivos.get(contador3 - 1).getUrl(), arquivos.get(contador3 - 1).getDataUpload().format(dtf)));
                                contador3++;
                                break;
                            // ACESSAR CÂMERA
                            case 2:
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
                                JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n----------------\nID Post: %d\nTítulo Post: %s\n-----------------\nID Arquivo: %d\nNome: %s\nTipo: %s\nTamanho: %f\nURL: %s\nData Upload: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), arquivos.get(contador3 - 1).getIdArquivo(), arquivos.get(contador3 - 1).getNomeArquivo(), arquivos.get(contador3 - 1).getTipo(), arquivos.get(contador3 - 1).getTamanho(), arquivos.get(contador3 - 1).getUrl(), arquivos.get(contador3 - 1).getDataUpload().format(dtf)));
                                contador3++;
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    // AVALIACAO
                    case 4:
                        // Novo elemento na lista avaliacoes
                        avaliacoes.add(new Avaliacao());
                        // Solicitação de id usuário
                        auxiliar = JOptionPane.showInputDialog("ID do usuário: ");
                        id = Integer.parseInt(auxiliar);
                        // Solicitação de id postagem
                        auxiliar = JOptionPane.showInputDialog("ID da postagem a ser avaliada: ");
                        idPostagem = Integer.parseInt(auxiliar);
                        // Chamada de metodo avaliarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                        avaliacoes.getFirst().avaliarPostagem(avaliacoes, usuarios, id, postagens, idPostagem);
                        // Chamada de metodo mostrarPostagem() no objeto avaliacoes de id informado(Só roda se o id do usuario e o id postagem forem existentes e válidos)
                        avaliacoes.getFirst().mostarAvaliacao(avaliacoes, usuarios, id, postagens, idPostagem);
                        break;
                    case 5:
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
                        pontuacao.exibirPontuacao(pontuacao, usuarios, id, postagens, idPostagem);
                        break;
                    default:
                        throw new Exception("Escolha inválida");
                }
                // Condição para finalização do código
                opcao = JOptionPane.showInputDialog("Deseja continuar?");
                if (opcao.equalsIgnoreCase("sim")) {
                    continue;
                } else {
                    break;
                }
            } catch (Exception e) {
                // Mensagem de erro
                JOptionPane.showMessageDialog(null, e.getMessage());
                break;
            }
        }
        // Fim do programa
        JOptionPane.showMessageDialog(null, "Programa encerrado.");
    }
}
