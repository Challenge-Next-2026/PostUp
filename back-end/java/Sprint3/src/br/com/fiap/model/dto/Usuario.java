package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

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
    // Cadastro de um objeto Usuário na memória
    public String cadastrarUsuario()
    {
        LocalDate dataAtual = LocalDate.now();

        setNome(JOptionPane.showInputDialog("Informe o Nome:"));
        setEmail(JOptionPane.showInputDialog("Informe o Email:"));
        setSenha(JOptionPane.showInputDialog("Informe a Senha:"));
        setDataCadastro(dataAtual);
        return "Usuário cadastrado com sucesso!";
    }

    // Alterar o email de um objeto Usuário na memória
    public String alterar()
    {
        setEmail(JOptionPane.showInputDialog(String.format("Informe o novo Email: {%s}", getEmail())));
        setSenha(JOptionPane.showInputDialog(String.format("Informe a nova Senha: {%s}", getSenha())));
        return "Email e/ou Senha alterados!";
    }
}
