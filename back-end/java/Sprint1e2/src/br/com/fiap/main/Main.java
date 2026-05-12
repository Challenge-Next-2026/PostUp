package br.com.fiap.main;

import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int escolha, id, contador = 1;
        String auxiliar, opcao = "sim";
        List <Usuario> usuarios = new ArrayList<>();
        while (opcao.equalsIgnoreCase("sim")){
            try {
                auxiliar = JOptionPane.showInputDialog("1 - Usuário\n2 - Postagem\n3 - Arquivo\n4 - Avaliação\n5 - Pontuação");
                escolha = Integer.parseInt(auxiliar);
                switch (escolha){
                    case 1:
                        //Usuario usuario = new Usuario();
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
                        break;
                    case 3:
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
