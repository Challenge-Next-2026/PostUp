package br.com.fiap.main;

import br.com.fiap.bean.*;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    static void main()
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
        do{
            // Tratamento de errps
            try {
                // menu principal
                auxiliar = JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação");
                escolha = Integer.parseInt(auxiliar);
                // estrutura condicional avaliando a escolha do usuario
                switch (escolha){
                    // USUÁRIO
                    case 1:
                        // Menu secundário
                        auxiliar = JOptionPane.showInputDialog("1 - Cadastrar\n2 - Editar email\n3 - Editar senha");
                        escolha = Integer.parseInt(auxiliar);
                        // estrutura condicional avaliando a escolha do usuario
                        switch (escolha){
                            // CADASTRAR
                            case 1:
                                // Novo elemento na lista usuarios
                                usuarios.add(new Usuario());
                                // Definição de id com base no número de repetições
                                usuarios.get(contador - 1).setIdUsuario(contador);
                                // Chamado de metodo cadastrarusuario()
                                usuarios.get(contador - 1).cadastrarUsuario();
                                // Exibição de Usuário
                                usuarios.get(contador - 1).exibir(usuarios, contador, 0, null, 0, 0, null, 0, null, null);
                                contador++;
                                break;
                            // EDITAR EMAIL
                            case 2:
                                // Validação
                                if (usuarios.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                } else {
                                    // Solicitação de id usuário
                                    auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                    id = Integer.parseInt(auxiliar);
                                    // Chamada de metodo alterarEmail no objeto usuario de id informado
                                    usuarios.get(id - 1).alterarEmail();
                                    // Exibição de Usuário
                                    usuarios.get(contador - 1).exibir(usuarios, contador, 0, null, 0, 0, null, 0, null, null);
                                    break;
                                }

                            // EDITAR SENHA
                            case 3:
                                // Validação
                                if (usuarios.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                } else {
                                    // Solicitação de id usuário
                                    auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                    id = Integer.parseInt(auxiliar);
                                    // Chamada de metodo alterarSenha no objeto usuario de id informado
                                    usuarios.get(id - 1).alterarSenha();
                                    // Exibição de Usuário
                                    usuarios.get(contador - 1).exibir(usuarios, contador, 0, null, 0, 0, null, 0, null, null);
                                    break;
                                }
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    // POSTAGEM
                    case 2:
                        // Menu segundário
                        auxiliar = JOptionPane.showInputDialog("1 - Criar Postagem\n2 - Editar Postagem\n3 - Remover Postagem");
                        escolha = Integer.parseInt(auxiliar);
                        // estrutura condicional avaliando a escolha do usuario
                        switch (escolha){
                            // CRIAR POSTAGEM
                            case 1:
                                // Validação
                                if (usuarios.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Não há usuários cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                } else {
                                    // Novo elemento na lista postagens
                                    postagens.add(new Postagem());
                                    // Definição de id com base no número de repetições
                                    postagens.get(contador2 - 1).setIdPostagem(contador2);
                                    // Solicitação de id usuário
                                    auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que deseja fazer uma postagem:");
                                    id = Integer.parseInt(auxiliar);
                                    // Chamada de metodo criarPostagem no objeto postagens de id informado(Só roda se o id do usuario for existente e válido)
                                    postagens.get(contador2 - 1).criarPostagem(usuarios, id, postagens, contador2);
                                    // Exibição de Postagem
                                    postagens.get(contador2 - 1).exibir(usuarios, 0, id, postagens, contador2, 0, null, 0, null, null);
                                    contador2++;
                                    break;
                                }
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
                        auxiliar = JOptionPane.showInputDialog("1 - Importar Arquivo\n2 - Acessar Câmera");
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
                    default:
                        throw new Exception("Escolha inválida");
                }
            } catch (Exception e) {
                // Mensagem de erro
                JOptionPane.showMessageDialog(null, e.getMessage());
                break;
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
        // Fim do programa
        JOptionPane.showMessageDialog(null, "Programa encerrado.");
    }
}
