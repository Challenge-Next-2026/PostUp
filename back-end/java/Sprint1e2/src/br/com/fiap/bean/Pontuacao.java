package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

public class Pontuacao extends Postagem
{
    // Atributos
    private int idPontuacao;
    private int valor;
    private LocalDate dataPontuacao;

    // Construtores
    public Pontuacao(){

    }

    public Pontuacao(int idPostagem, String titulo, String descricao, LocalDate dataPostagem, Boolean status, int idPontuacao, int valor, LocalDate dataPontuacao) {
        super(idPostagem, titulo, descricao, dataPostagem, status);
        this.idPontuacao = idPontuacao;
        this.valor = valor;
        this.dataPontuacao = dataPontuacao;
    }

    // Getter e Setter
    public int getIdPontuacao() {
        return idPontuacao;
    }

    public void setIdPontuacao(int idPontuacao) {
        this.idPontuacao = idPontuacao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public LocalDate getDataPontuacao() {
        return dataPontuacao;
    }

    public void setDataPontuacao(LocalDate dataPontuacao) {
        this.dataPontuacao = dataPontuacao;
    }

    // Metodos da classe
    public void exibirPontuacao(int idPontuacao)
    {
        JOptionPane.showMessageDialog(null, String.format("ID Postagem: %d\nID Pontuação: %d\nValor: %d\nData: %s",getIdPostagem(), getIdPontuacao(), getValor(), getDataPontuacao()));
    }
}
