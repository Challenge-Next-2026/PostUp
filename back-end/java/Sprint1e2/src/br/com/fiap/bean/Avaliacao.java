package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Avaliacao implements ExibicaoObjetos
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
    // Avaliação com base em determinada postagem de determinado usuário
    public void avaliarPostagem(Postagem postagem, Arquivo arquivo, int qtdPostagens, int idAvaliacao)
    {
        LocalDate dataAtual = LocalDate.now();
        // verificação de Título da lista informada
        if (postagem.getTitulo().equalsIgnoreCase("Plantar")) {
            // definição de parâmetros para atributos
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
            // definição de parâmetros para atributos
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
            // definição de parâmetros para atributos
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
            // definição de parâmetros para atributos
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
            // definição de parâmetros para atributos
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

    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao)
    {
        // Exibição de avaliação com listas e DateTimeFormatter
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n------------------\nID Post: %d\nTítulo Post: %s\n------------------\nCritério avaliado: %s\nNota Impacto: %d\nNota Dificuldade: %d\nNota Confiabifidade: %d\nNota Frequência: %d\nData: %s", usuarios.get(id).getIdUsuario(), usuarios.get(id).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), avaliacoes.getFirst().getCriterio(), avaliacoes.getFirst().getNotaImpacto(), avaliacoes.getFirst().getNotaDificuldade(), avaliacoes.getFirst().getNotaConfiabilidade(), avaliacoes.getFirst().getNotaFrequencia(), avaliacoes.getFirst().getDataAvaliacao().format(dtf)));
    }
}
