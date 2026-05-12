package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

public class Avaliacao extends Postagem
{
    // Atributos
    private int idAvaliacao;
    private String criterio;
    private int notaImpacto;
    private int notaFrequencia;
    private int notaDificuldade;
    private int notaConfiabilidade;
    private LocalDate dataAvaliacao;

    // Construtores
    public Avaliacao(){
    }

    public Avaliacao(int idPostagem, String titulo, String descricao, LocalDate dataPostagem, Boolean status, int idAvaliacao, String criterio, int notaImpacto, int notaFrequencia, int notaDificuldade, int notaConfiabilidade, LocalDate dataAvaliacao) {
        super(idPostagem, titulo, descricao, dataPostagem, status);
        this.idAvaliacao = idAvaliacao;
        this.criterio = criterio;
        this.notaImpacto = notaImpacto;
        this.notaFrequencia = notaFrequencia;
        this.notaDificuldade = notaDificuldade;
        this.notaConfiabilidade = notaConfiabilidade;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getter e Setter
    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public int getNotaImpacto() {
        return notaImpacto;
    }

    public void setNotaImpacto(int notaImpacto) {
        this.notaImpacto = notaImpacto;
    }

    public int getNotaFrequencia() {
        return notaFrequencia;
    }

    public void setNotaFrequencia(int notaFrequencia) {
        this.notaFrequencia = notaFrequencia;
    }

    public int getNotaDificuldade() {
        return notaDificuldade;
    }

    public void setNotaDificuldade(int notaDificuldade) {
        this.notaDificuldade = notaDificuldade;
    }

    public int getNotaConfiabilidade() {
        return notaConfiabilidade;
    }

    public void setNotaConfiabilidade(int notaConfiabilidade) {
        this.notaConfiabilidade = notaConfiabilidade;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    // Metodos da classe
    public void avaliarPostagem(int idPostagem)
    {

    }

    public void mostarAvaliacao(int idAvaliacao)
    {
        JOptionPane.showMessageDialog(null, String.format("ID Postagem: %d\nID Avaliação: %d\nCritério: %d\nImpacto: %d\nFrequencia: %d\nDificuldade: %d\nConfiabilidade: %d\nData: %s",getIdPostagem(), getIdAvaliacao(), getCriterio(), getNotaImpacto(), getNotaFrequencia(), getNotaDificuldade(), getNotaConfiabilidade(), getDataAvaliacao()));
    }
}
