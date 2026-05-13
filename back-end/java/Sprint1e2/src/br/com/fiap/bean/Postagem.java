package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.jar.JarEntry;

public class Postagem extends Arquivo
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
    // Criar uma ou mais postagems dentro de um usuário
    public void criarPostagem(List <Usuario> usuarios, int id, List <Postagem> postagems, int contador)
    {
        // verificação se o usuário informado é existente
        if (usuarios.get(id - 1) != null) {
            LocalDate dataAtual = LocalDate.now();
            String titulo, descricao;
            Boolean status = true;
            // tratamento de erros
            try {
                // Preenchimento de indice nas listas usuarios e postagens
                titulo = JOptionPane.showInputDialog("Título:");
                usuarios.get(id - 1).setTitulo(titulo);
                postagems.get(contador - 1).setTitulo(titulo);
                descricao = JOptionPane.showInputDialog("Descrição:");
                usuarios.get(id - 1).setDescricao(descricao);
                postagems.get(contador - 1).setDescricao(descricao);
                usuarios.get(id - 1).setDataPostagem(dataAtual);
                postagems.get(contador - 1).setDataPostagem(dataAtual);
                usuarios.get(id - 1).setStatus(status);
                postagems.get(contador - 1).setStatus(status);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necessário criar um usuário antes de fazer qualquer postagem");
        }
    }

    // Metodo semelhante ao criarPostagem()
    public void editarPostagem(List <Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem)
    {
        LocalDate dataAtual = LocalDate.now();
        String titulo, descricao;
        Boolean status = true;
        // Tratamento de erros
        try {
            titulo = JOptionPane.showInputDialog("Novo Título:");
            usuarios.get(id - 1).setTitulo(titulo);
            postagems.get(idPostagem - 1).setTitulo(titulo);
            descricao = JOptionPane.showInputDialog("Nova Descrição:");
            usuarios.get(id - 1).setDescricao(descricao);
            postagems.get(idPostagem - 1).setDescricao(descricao);
            usuarios.get(id - 1).setDataPostagem(dataAtual);
            postagems.get(idPostagem - 1).setDataPostagem(dataAtual);
            usuarios.get(id - 1).setStatus(status);
            postagems.get(idPostagem - 1).setStatus(status);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Metodo para remover postagem
    public void removerPostagem(List <Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem)
    {
        usuarios.get(id - 1).setTitulo(null);
        postagems.get(idPostagem - 1).setTitulo(null);
        usuarios.get(id - 1).setDescricao(null);
        postagems.get(idPostagem - 1).setDescricao(null);
        usuarios.get(id - 1).setDataPostagem(null);
        postagems.get(idPostagem - 1).setDataPostagem(null);
        usuarios.get(id - 1).setStatus(false);
        postagems.get(idPostagem - 1).setStatus(false);
        JOptionPane.showMessageDialog(null, "Postagem removida");
    }
}
