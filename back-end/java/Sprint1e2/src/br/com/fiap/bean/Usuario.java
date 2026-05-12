package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Usuario
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

    public Usuario(int idUsuario, String nome, String email, String senha, LocalDate dataCadastro) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
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
        LocalDate dataAtual = LocalDate.now();
        String nome, email, senha;
        try {
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

    public void alterarEmail(List <Usuario> usuarios, int id)
    {
        String email = JOptionPane.showInputDialog("Informe o novo email:");
        usuarios.get(id).setEmail(email);
    }

    public void alterarSenha(List <Usuario> usuarios, int id)
    {
        String senha = JOptionPane.showInputDialog("Informe a nova senha:");
        usuarios.get(id).setSenha(senha);
    }
}
