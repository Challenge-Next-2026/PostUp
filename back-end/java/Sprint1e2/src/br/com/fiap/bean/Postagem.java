package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.util.jar.JarEntry;

public class Postagem extends Usuario
{
    // Atributos
    private int idPostagem;
    private String titulo;
    private String descricao;
    private LocalDate dataPostagem;
    private Boolean status;

    // Construtores
    public Postagem() {
    }

    public Postagem(int idUsuario, String nome, String email, String senha, LocalDate dataCadastro, int idPostagem, String titulo, String descricao, LocalDate dataPostagem, Boolean status) {
        super(idUsuario, nome, email, senha, dataCadastro);
        this.idPostagem = idPostagem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPostagem = dataPostagem;
        this.status = status;
    }

    // Getter e Setter
    public int getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(int idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Metodos da classe
    public void criarPostagem()
    {
        LocalDate dataAtual = LocalDate.now();
        String titulo, descricao;
        Boolean status = true;
        try {
            titulo = JOptionPane.showInputDialog("Título:");
            setTitulo(titulo);
            descricao = JOptionPane.showInputDialog("Descrição:");
            setDescricao(descricao);
            setDataPostagem(dataAtual);
            setStatus(status);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void editarPostagem(int idPostagem)
    {
        LocalDate dataAtual = LocalDate.now();
        String titulo, descricao;
        Boolean status = true;
        try {
            titulo = JOptionPane.showInputDialog("Novo Título:");
            setTitulo(titulo);
            descricao = JOptionPane.showInputDialog("Nova Descrição:");
            setDescricao(descricao);
            setDataPostagem(dataAtual);
            setStatus(status);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void removerPostagem(int idPostagem)
    {
        setTitulo(null);
        setDescricao(null);
        setDataPostagem(null);
        setStatus(false);
        JOptionPane.showMessageDialog(null, "Postagem removida");
    }
}
