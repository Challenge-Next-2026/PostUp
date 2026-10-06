package br.com.fiap.model.dto;

import javax.swing.*;
import java.time.LocalDate;

public class Postagem
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
}
