package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AvaliarPostagemRequest {
    // Atributo
    @NotNull
    @Positive
    private Integer idPostagem;

    // Construtor vazio
    public AvaliarPostagemRequest() {
    }

    // Getter e Setter
    public Integer getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Integer idPostagem) {
        this.idPostagem = idPostagem;
    }
}
