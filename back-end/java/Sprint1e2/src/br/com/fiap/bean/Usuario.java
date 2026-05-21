package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Usuario extends Postagem implements ExibicaoObjetos
{
    // Atributos
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataCadastro;

    // Construtores
    public Usuario() {
    }

    // Getter e Setter
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Metodos Exclusivos
    public void cadastrarUsuario()
    {
        // Armazenando data com LocalDate
        LocalDate dataAtual = LocalDate.now();
        // variáveis de apoio
        String nome, email, senha;
        // Tratamento de erros
        try {
            // Preenchimento de atributos
            nome = JOptionPane.showInputDialog("Informe o nome:");
            setNome(nome);
            email = JOptionPane.showInputDialog("Informe o email:");
            setEmail(email);
            senha = JOptionPane.showInputDialog("Informe a senha:");
            setSenha(senha);
            setDataCadastro(dataAtual);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Alterar o email de um usuário da lista
    public void alterarEmail(List <Usuario> usuarios, int id)
    {
        String email = JOptionPane.showInputDialog("Informe o novo email:");
        // Selecionando o indice da lista usuarios
        usuarios.get(id - 1).setEmail(email);
    }

    // Alterar o email de um usuário da lista
    public void alterarSenha(List <Usuario> usuarios, int id)
    {
        String senha = JOptionPane.showInputDialog("Informe a nova senha:");
        // Selecionando o indice da lista usuarios
        usuarios.get(id - 1).setSenha(senha);
    }

    public void exibir(List <Usuario> usuarios, int contador) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Exibição de Usuário
        JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                usuarios.get(contador - 1).getIdUsuario(), usuarios.get(contador - 1).getNome(), usuarios.get(contador - 1).getEmail(), usuarios.get(contador - 1).getSenha(), usuarios.get(contador - 1).getDataCadastro().format(dtf)));
    }
}
