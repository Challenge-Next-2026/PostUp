package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Postagem implements ExibicaoObjetos
{
    // Atributos
    private int idPostagem;
    private String titulo;
    private String descricao;
    private LocalDate dataPostagem;

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

    // Metodos da classe
    // Criar uma ou mais postagems dentro de um usuário
    public void criarPostagem(int idPostagem)
    {
        // Armazenando data com LocalDate
        LocalDate dataAtual = LocalDate.now();
        // Definindo ID
        setIdPostagem(idPostagem);
        // Preenchimento de indice nas listas usuarios e postagens
        titulo = JOptionPane.showInputDialog("Títulos possíveis...\nPlantar - Transporte público - Bicicleta - Veículo Elétrico - Reciclagem\nDigite algum: ");
        setTitulo(titulo);
        descricao = JOptionPane.showInputDialog("Descrição:");
        setDescricao(descricao);
        setDataPostagem(dataAtual);
    }

    // Metodo semelhante ao criarPostagem()
    public void editarPostagem()
    {
        LocalDate dataAtual = LocalDate.now();
        String titulo, descricao;
        // Tratamento de erros
        try {
            titulo = JOptionPane.showInputDialog(String.format("Novo Título: {%s}", getTitulo()));
            setTitulo(titulo);
            descricao = JOptionPane.showInputDialog(String.format("Nova Descrição: {%s}", getDescricao()));
            setDescricao(descricao);
            setDataPostagem(dataAtual);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Metodo para remover postagem
    public void removerPostagem(List <Postagem> postagems, int idPostagem)
    {
        postagems.get(idPostagem - 1).setTitulo(null);
        postagems.get(idPostagem - 1).setDescricao(null);
        postagems.get(idPostagem - 1).setDataPostagem(null);
        JOptionPane.showMessageDialog(null, "Postagem removida");
    }

    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID Usuário: %d\nNome User: %s\n-------------------\nID Post: %d\nTítulo: %s\nDescrição: %s\nData: %s\nStatus: %b", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(contador2 - 1).getIdPostagem(), postagens.get(contador2 - 1).getTitulo(), postagens.get(contador2 - 1).getDescricao(), postagens.get(contador2 - 1).getDataPostagem().format(dtf)));
    }
}
