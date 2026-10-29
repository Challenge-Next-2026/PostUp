package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PontuacaoTO {
    // Atributos
    private int idPontuacao;
    @NotNull
    @Min(0)
    @Max(100)
    @PositiveOrZero
    private float valor;
    @PastOrPresent
    private LocalDate dataPontuacao;

    // Construtores
    public PontuacaoTO(){

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
}
