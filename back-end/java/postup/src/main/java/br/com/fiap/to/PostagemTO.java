package br.com.fiap.to;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import javax.swing.*;
import java.time.LocalDate;

public class PostagemTO {
    // Atributos
    private int idPostagem;
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String descricao;
    @PastOrPresent
    private LocalDate dataPostagem;

    // Construtores
    public PostagemTO() {
    }

    public PostagemTO(int idPostagem, String titulo, String descricao, LocalDate dataPostagem) {
        this.idPostagem = idPostagem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPostagem = dataPostagem;
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

    /*

    // Metodos da classe
    // Criar um objeto Postagem dentro da memória
    public String criarPostagem()
    {
        // Tratamento de erros
        try {
            LocalDate dataAtual = LocalDate.now();
            // Definindo ID a partir do metodo obterProximoId() da classe PostagemDAO
            setTitulo(JOptionPane.showInputDialog("Títulos possíveis...\nPlantar - Transporte público - Bicicleta - Veículo Elétrico - Reciclagem\nDigite algum: "));
            setDescricao(JOptionPane.showInputDialog("Descrição:"));
            setDataPostagem(dataAtual);
            return "Postagem Criada!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    // Editar um objeto Postagem na memória
    public String editarPostagem()
    {
        LocalDate dataAtual = LocalDate.now();
        // Tratamento de erros
        try {
            setTitulo(JOptionPane.showInputDialog(String.format("Novo Título: {%s}", getTitulo())));
            setDescricao(JOptionPane.showInputDialog(String.format("Nova Descrição: {%s}", getDescricao())));
            setDataPostagem(dataAtual);
            return "Postagem editada!";
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }

    */
}
