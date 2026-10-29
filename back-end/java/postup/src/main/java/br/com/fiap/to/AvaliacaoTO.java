package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AvaliacaoTO {
    // Atributos
    private Integer idAvaliacao;
    private Integer idPostagem;
    @NotEmpty
    private String criterio;
    @NotNull
    @Min(0)
    @Max(100)
    @PositiveOrZero
    private int notaImpacto;
    @NotNull
    @Min(0)
    @Max(100)
    @PositiveOrZero
    private int notaFrequencia;
    @NotNull
    @Min(0)
    @Max(100)
    @PositiveOrZero
    private int notaDificuldade;
    @NotNull
    @Min(0)
    @Max(100)
    @PositiveOrZero
    private int notaConfiabilidade;
    @PastOrPresent
    private LocalDate dataAvaliacao;

    // Construtores
    public AvaliacaoTO(){
    }

    public AvaliacaoTO(Integer idAvaliacao, String criterio, int notaImpacto, int notaFrequencia, int notaDificuldade, int notaConfiabilidade, LocalDate dataAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.criterio = criterio;
        this.notaImpacto = notaImpacto;
        this.notaFrequencia = notaFrequencia;
        this.notaDificuldade = notaDificuldade;
        this.notaConfiabilidade = notaConfiabilidade;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getter e Setter
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Integer idPostagem) {
        this.idPostagem = idPostagem;
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

    /*

    // Metodos da classe
    // Avaliação de um objeto Postagem e um objeto Arquivo para definição de notas
    public void avaliarPostagem(PostagemTO postagem, ArquivoTO arquivo, int qtdPostagens, int idAvaliacao)
    {
        LocalDate dataAtual = LocalDate.now();
        // verificação de Título da lista informada
        if (postagem.getTitulo().equalsIgnoreCase("Plantar")) {
            setIdAvaliacao(idAvaliacao);
            setCriterio("Plantação");
            setNotaImpacto(100);
            setNotaDificuldade(50);
            if (arquivo.getTipo().equalsIgnoreCase("img") || arquivo.getTipo().equalsIgnoreCase("png") || arquivo.getTipo().equalsIgnoreCase("mp3") || arquivo.getTipo().equalsIgnoreCase("mp4") || arquivo.getTipo().equalsIgnoreCase("jpeg")) {
                setNotaConfiabilidade(100);
            } else {
                setNotaConfiabilidade(0);
            }
            if (qtdPostagens == 1) {
                setNotaFrequencia(25);
            } else if (qtdPostagens == 2) {
                setNotaFrequencia(50);
            } else if (qtdPostagens == 3) {
                setNotaFrequencia(75);
            } else if (qtdPostagens >= 4) {
                setNotaFrequencia(100);
            }
            setDataAvaliacao(dataAtual);
            // verificação de Título da lista informada
        } else if (postagem.getTitulo().equalsIgnoreCase("Transporte público")){
            setIdAvaliacao(idAvaliacao);
            setCriterio("Transporte Público");
            setNotaImpacto(90);
            setNotaDificuldade(70);
            if (arquivo.getTipo().equalsIgnoreCase("img") || arquivo.getTipo().equalsIgnoreCase("png") || arquivo.getTipo().equalsIgnoreCase("mp3") || arquivo.getTipo().equalsIgnoreCase("mp4") || arquivo.getTipo().equalsIgnoreCase("jpeg")) {
                setNotaConfiabilidade(100);
            } else {
                setNotaConfiabilidade(0);
            }
            if (qtdPostagens == 1) {
                setNotaFrequencia(25);
            } else if (qtdPostagens == 2) {
                setNotaFrequencia(50);
            } else if (qtdPostagens == 3) {
                setNotaFrequencia(75);
            } else if (qtdPostagens >= 4) {
                setNotaFrequencia(100);
            }
            setDataAvaliacao(dataAtual);
            // verificação de Título da lista informada
        } else if (postagem.getTitulo().equalsIgnoreCase("Bicicleta")){
            setIdAvaliacao(idAvaliacao);
            setCriterio("Bicicleta");
            setNotaImpacto(80);
            setNotaDificuldade(80);
            if (arquivo.getTipo().equalsIgnoreCase("img") || arquivo.getTipo().equalsIgnoreCase("png") || arquivo.getTipo().equalsIgnoreCase("mp3") || arquivo.getTipo().equalsIgnoreCase("mp4") || arquivo.getTipo().equalsIgnoreCase("jpeg")) {
                setNotaConfiabilidade(100);
            } else {
                setNotaConfiabilidade(0);
            }
            if (qtdPostagens == 1) {
                setNotaFrequencia(25);
            } else if (qtdPostagens == 2) {
                setNotaFrequencia(50);
            } else if (qtdPostagens == 3) {
                setNotaFrequencia(75);
            } else if (qtdPostagens >= 4) {
                setNotaFrequencia(100);
            }
            setDataAvaliacao(dataAtual);
            // verificação de Título da lista informada
        } else if (postagem.getTitulo().equalsIgnoreCase("Veículo Elétrico")){
            setIdAvaliacao(idAvaliacao);
            setCriterio("Mobilidade elétrica");
            setNotaImpacto(70);
            setNotaDificuldade(100);
            if (arquivo.getTipo().equalsIgnoreCase("img") || arquivo.getTipo().equalsIgnoreCase("png") || arquivo.getTipo().equalsIgnoreCase("mp3") || arquivo.getTipo().equalsIgnoreCase("mp4") || arquivo.getTipo().equalsIgnoreCase("jpeg")) {
                setNotaConfiabilidade(100);
            } else {
                setNotaConfiabilidade(0);
            }
            if (qtdPostagens == 1) {
                setNotaFrequencia(25);
            } else if (qtdPostagens == 2) {
                setNotaFrequencia(50);
            } else if (qtdPostagens == 3) {
                setNotaFrequencia(75);
            } else if (qtdPostagens >= 4) {
                setNotaFrequencia(100);
            }
            setDataAvaliacao(dataAtual);
            // verificação de Título da lista informada
        } else if (postagem.getTitulo().equalsIgnoreCase("Reciclagem")){
            setIdAvaliacao(idAvaliacao);
            setCriterio("Reciclagem");
            setNotaImpacto(100);
            setNotaDificuldade(100);
            if (arquivo.getTipo().equalsIgnoreCase("img") || arquivo.getTipo().equalsIgnoreCase("png") || arquivo.getTipo().equalsIgnoreCase("mp3") || arquivo.getTipo().equalsIgnoreCase("mp4") || arquivo.getTipo().equalsIgnoreCase("jpeg")) {
                setNotaConfiabilidade(100);
            } else {
                setNotaConfiabilidade(0);
            }
            if (qtdPostagens == 1) {
                setNotaFrequencia(25);
            } else if (qtdPostagens == 2) {
                setNotaFrequencia(50);
            } else if (qtdPostagens == 3) {
                setNotaFrequencia(75);
            } else if (qtdPostagens >= 4) {
                setNotaFrequencia(100);
            }
            setDataAvaliacao(dataAtual);
        }
    }

    */
}
