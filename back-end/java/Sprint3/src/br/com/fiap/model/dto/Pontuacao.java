package br.com.fiap.model.dto;

import java.time.LocalDate;

public class Pontuacao
{
    // Atributos
    private int idPontuacao;
    private float valor;
    private LocalDate dataPontuacao;

    // Construtores
    public Pontuacao(){

    }

    // Getter e Setter
    public int getIdPontuacao() {
        return idPontuacao;
    }

    public void setIdPontuacao(int idPontuacao) {
        this.idPontuacao = idPontuacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getDataPontuacao() {
        return dataPontuacao;
    }

    public void setDataPontuacao(LocalDate dataPontuacao) {
        this.dataPontuacao = dataPontuacao;
    }

    // Metodos da classe
    // Cálcular pontuação de um objeto Avaliacao
    public void calcularPontuacao(Avaliacao avaliacao, int idPontuacao){
        // Cálculo simples de média aritmética
        LocalDate dataAtual = LocalDate.now();
        float calculo = (float) (avaliacao.getNotaImpacto() + avaliacao.getNotaDificuldade() + avaliacao.getNotaConfiabilidade() + avaliacao.getNotaFrequencia()) / 5;
        setIdPontuacao(idPontuacao);
        setValor(calculo);
        setDataPontuacao(dataAtual);
    }
}
