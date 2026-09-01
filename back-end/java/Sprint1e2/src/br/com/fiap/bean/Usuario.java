package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Usuario implements ExibicaoObjetos
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
    public void cadastrarUsuario(int idUsuario)
    {
        // Armazenando data com LocalDate
        LocalDate dataAtual = LocalDate.now();
        // Preenchimento de atributos
        setIdUsuario(idUsuario);
        setNome(JOptionPane.showInputDialog("Informe o Nome:"));
        setEmail(JOptionPane.showInputDialog("Informe o Email:"));
        setSenha(JOptionPane.showInputDialog("Informe a Senha:"));
        setDataCadastro(dataAtual);
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    }

    // Realizar login
    public void realizarLogin() throws Exception {
        // Armazenamento de valor nas variaveis
        String email = JOptionPane.showInputDialog("Informe o email:");
        String senha = JOptionPane.showInputDialog("informe a senha:");

        // Verificação
        if (!getEmail().equals(email)) {
            throw new Exception("Email incorreto!");
        } else if (!getSenha().equals(senha)) {
            throw new Exception("Senha Incorreta");
        } else {
            JOptionPane.showMessageDialog(null, String.format("Seja Bem Vindo a PostUp!\n%s", getNome()));
        }
    }

    // Alterar o email de um usuário da lista
    public void alterarEmail()
    {
        // setEmail com entrada de dados
        setEmail(JOptionPane.showInputDialog(String.format("Informe o novo Email: {%s}", getEmail())));
        JOptionPane.showMessageDialog(null, "Email alterado!");
    }

    // Alterar o email de um usuário da lista
    public void alterarSenha()
    {
        // setSenha com entrada de dados
        setSenha(JOptionPane.showInputDialog(String.format("Informe a nova Senha: {%s}", getSenha())));
        JOptionPane.showMessageDialog(null, "Senha alterada!");
    }

    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Exibição de Usuário
        JOptionPane.showMessageDialog(null, String.format("Usuário\nID: %d\nNome: %s\nEmail: %s\nSenha: %s\nData de cadastro: %s",
                usuarios.get(contador - 1).getIdUsuario(), usuarios.get(contador - 1).getNome(), usuarios.get(contador - 1).getEmail(), usuarios.get(contador - 1).getSenha(), usuarios.get(contador - 1).getDataCadastro().format(dtf)));
    }
}
