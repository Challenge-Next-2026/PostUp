package br.com.fiap.main;

import br.com.fiap.bean.Arquivo;
import br.com.fiap.bean.Postagem;
import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int escolha, id, idPostagem, contador = 1, contador2 = 1, contador3 = 1;
        String auxiliar, opcao = "sim";
        List <Usuario> usuarios = new ArrayList<>();
        List <Postagem> postagens = new ArrayList<>();
        List <Arquivo> arquivos = new ArrayList<>();
        while (opcao.equalsIgnoreCase("sim")){
            try {
                auxiliar = JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação");
                escolha = Integer.parseInt(auxiliar);
                switch (escolha){
                    case 1:
                        usuarios.add(new Usuario());
                        auxiliar = JOptionPane.showInputDialog("1 - Cadastrar\n2 - Editar email\n3 - Editar senha");
                        escolha = Integer.parseInt(auxiliar);
                        switch (escolha){
                            case 1:
                                usuarios.get(contador - 1).setIdUsuario(contador);
                                usuarios.get(contador - 1).cadastrarUsuario();
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(contador - 1).getIdUsuario(), usuarios.get(contador - 1).getNome(), usuarios.get(contador - 1).getEmail(), usuarios.get(contador - 1).getSenha(), usuarios.get(contador - 1).getDataCadastro()));
                                contador++;
                                break;
                            case 2:
                                auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                id = Integer.parseInt(auxiliar);
                                usuarios.get(id - 1).alterarEmail(usuarios, id - 1);
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), usuarios.get(id - 1).getEmail(), usuarios.get(id - 1).getSenha(), usuarios.get(id - 1).getDataCadastro()));
                                break;
                            case 3:
                                auxiliar = JOptionPane.showInputDialog("Informe o ID:");
                                id = Integer.parseInt(auxiliar);
                                usuarios.get(id - 1).alterarSenha(usuarios, id - 1);
                                JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                                        usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), usuarios.get(id - 1).getEmail(), usuarios.get(id - 1).getSenha(), usuarios.get(id - 1).getDataCadastro()));
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    case 2:
                        postagens.add(new Postagem());
                        auxiliar = JOptionPane.showInputDialog("1 - Criar Postagem\n2 - Editar Postagem\n3 - Remover Postagem");
                        escolha = Integer.parseInt(auxiliar);
                        switch (escolha){
                            case 1:
                                postagens.get(contador2 - 1).setIdPostagem(contador2);
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que deseja fazer uma postagem:");
                                id = Integer.parseInt(auxiliar);
                                postagens.get(contador2 - 1).criarPostagem(usuarios, id, postagens, contador2);
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(contador2 - 1).getIdPostagem(), postagens.get(contador2 - 1).getTitulo(), postagens.get(contador2 - 1).getDescricao(), postagens.get(contador2 - 1).getDataPostagem(), postagens.get(contador2 - 1).getStatus()));
                                contador2++;
                                break;
                            case 2:
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                id = Integer.parseInt(auxiliar);
                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser alterada:");
                                idPostagem = Integer.parseInt(auxiliar);
                                postagens.get(idPostagem - 1).editarPostagem(usuarios, id, postagens, idPostagem);
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), postagens.get(idPostagem - 1).getDescricao(), postagens.get(idPostagem - 1).getDataPostagem(), postagens.get(idPostagem - 1).getStatus()));
                                break;
                            case 3:
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem:");
                                id = Integer.parseInt(auxiliar);
                                auxiliar = JOptionPane.showInputDialog("informe o ID da postagem a ser removida:");
                                idPostagem = Integer.parseInt(auxiliar);
                                postagens.get(idPostagem - 1).removerPostagem(usuarios, id, postagens, idPostagem);
                                JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), postagens.get(idPostagem - 1).getDescricao(), postagens.get(idPostagem - 1).getDataPostagem(), postagens.get(idPostagem - 1).getStatus()));
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    case 3:
                        arquivos.add(new Arquivo());
                        auxiliar = JOptionPane.showInputDialog("1 - Importar Arquivo\n2 - Acessar Câmera");
                        escolha = Integer.parseInt(auxiliar);
                        switch (escolha){
                            case 1:
                                arquivos.get(contador3 - 1).setIdArquivo(contador3);
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem");
                                id = Integer.parseInt(auxiliar);
                                auxiliar = JOptionPane.showInputDialog("Informe o ID da postagem que deseja importar o arquivo:");
                                idPostagem = Integer.parseInt(auxiliar);
                                arquivos.get(contador3 - 1).importarArquivo(usuarios, id, postagens, idPostagem, arquivos, contador3);
                                JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n----------------\nID Post: %d\nTítulo Post: %s\n-----------------\nID Arquivo: %d\nNome: %s\nTipo: %s\nTamanho: %f\nURL: %s\nData Upload: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), arquivos.get(contador3 - 1).getIdArquivo(), arquivos.get(contador3 - 1).getNomeArquivo(), arquivos.get(contador3 - 1).getTipo(), arquivos.get(contador3 - 1).getTamanho(), arquivos.get(contador3 - 1).getUrl(), arquivos.get(contador3 - 1).getDataUpload()));
                                contador3++;
                                break;
                            case 2:
                                arquivos.get(contador3 - 1).setIdArquivo(contador3);
                                auxiliar = JOptionPane.showInputDialog("Informe o ID do usuário que possui a postagem");
                                id = Integer.parseInt(auxiliar);
                                auxiliar = JOptionPane.showInputDialog("Informe o ID da postagem que deseja acessar a cãmera:");
                                idPostagem = Integer.parseInt(auxiliar);
                                arquivos.get(contador3 - 1).acessarCamera(usuarios, id, postagens, idPostagem, arquivos, contador3);
                                JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n----------------\nID Post: %d\nTítulo Post: %s\n-----------------\nID Arquivo: %d\nNome: %s\nTipo: %s\nTamanho: %f\nURL: %s\nData Upload: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), arquivos.get(contador3 - 1).getIdArquivo(), arquivos.get(contador3 - 1).getNomeArquivo(), arquivos.get(contador3 - 1).getTipo(), arquivos.get(contador3 - 1).getTamanho(), arquivos.get(contador3 - 1).getUrl(), arquivos.get(contador3 - 1).getDataUpload()));
                                contador3++;
                                break;
                            default:
                                throw new Exception("Escolha inválida");
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        throw new Exception("Escolha inválida");
                }
                opcao = JOptionPane.showInputDialog("Deseja continuar?");
                if (opcao.equalsIgnoreCase("sim")) {
                    continue;
                } else {
                    break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        JOptionPane.showMessageDialog(null, "Programa encerrado.");
        }
    }
}
