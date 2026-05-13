package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Avaliacao
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
    public void avaliarPostagem(List <Avaliacao> avaliacoes, List<Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem)
    {
        LocalDate dataAtual = LocalDate.now();
        // verificação de Título da lista informada
        if (usuarios.get(id - 1).getTitulo().equalsIgnoreCase("Plantar") && postagems.get(idPostagem - 1).getTitulo().equalsIgnoreCase("Plantar")) {
            // definição de parâmetros para atributos
            avaliacoes.getFirst().setIdAvaliacao(1);
            avaliacoes.getFirst().setCriterio("Plantação");
            avaliacoes.getFirst().setNotaImpacto(100);
            avaliacoes.getFirst().setNotaDificuldade(50);
            if (usuarios.get(id - 1).getTipo().equalsIgnoreCase("img") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("png") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp3") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp4") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("jpeg")) {
                avaliacoes.getFirst().setNotaConfiabilidade(100);
            } else {
                avaliacoes.getFirst().setNotaConfiabilidade(0);
            }
            if (postagems.size() == 1) {
                avaliacoes.getFirst().setNotaFrequencia(25);
            } else if (postagems.size() == 2) {
                avaliacoes.getFirst().setNotaFrequencia(50);
            } else if (postagems.size() == 3) {
                avaliacoes.getFirst().setNotaFrequencia(75);
            } else if (postagems.size() >= 4) {
                avaliacoes.getFirst().setNotaFrequencia(100);
            }
            avaliacoes.getFirst().setDataAvaliacao(dataAtual);
        // verificação de Título da lista informada
        } else if (usuarios.get(id - 1).getTitulo().equalsIgnoreCase("Transporte público") && postagems.get(idPostagem - 1).getTitulo().equalsIgnoreCase("Transporte público")){
            // definição de parâmetros para atributos
            avaliacoes.getFirst().setIdAvaliacao(1);
            avaliacoes.getFirst().setCriterio("Transporte Público");
            avaliacoes.getFirst().setNotaImpacto(90);
            avaliacoes.getFirst().setNotaDificuldade(70);
            if (usuarios.get(id - 1).getTipo().equalsIgnoreCase("img") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("png") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp3") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp4") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("jpeg")) {
                avaliacoes.getFirst().setNotaConfiabilidade(100);
            } else {
                avaliacoes.getFirst().setNotaConfiabilidade(0);
            }
            if (postagems.size() == 1) {
                avaliacoes.getFirst().setNotaFrequencia(25);
            } else if (postagems.size() == 2) {
                avaliacoes.getFirst().setNotaFrequencia(50);
            } else if (postagems.size() == 3) {
                avaliacoes.getFirst().setNotaFrequencia(75);
            } else if (postagems.size() >= 4) {
                avaliacoes.getFirst().setNotaFrequencia(100);
            }
            avaliacoes.getFirst().setDataAvaliacao(dataAtual);
        // verificação de Título da lista informada
        } else if (usuarios.get(id - 1).getTitulo().equalsIgnoreCase("Bicicleta") && postagems.get(idPostagem - 1).getTitulo().equalsIgnoreCase("Bicicleta")){
            // definição de parâmetros para atributos
            avaliacoes.getFirst().setIdAvaliacao(1);
            avaliacoes.getFirst().setCriterio("Bicicleta");
            avaliacoes.getFirst().setNotaImpacto(80);
            avaliacoes.getFirst().setNotaDificuldade(80);
            if (usuarios.get(id - 1).getTipo().equalsIgnoreCase("img") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("png") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp3") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp4") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("jpeg")) {
                avaliacoes.getFirst().setNotaConfiabilidade(100);
            } else {
                avaliacoes.getFirst().setNotaConfiabilidade(0);
            }
            if (postagems.size() == 1) {
                avaliacoes.getFirst().setNotaFrequencia(25);
            } else if (postagems.size() == 2) {
                avaliacoes.getFirst().setNotaFrequencia(50);
            } else if (postagems.size() == 3) {
                avaliacoes.getFirst().setNotaFrequencia(75);
            } else if (postagems.size() >= 4) {
                avaliacoes.getFirst().setNotaFrequencia(100);
            }
            avaliacoes.getFirst().setDataAvaliacao(dataAtual);
        // verificação de Título da lista informada
        } else if (usuarios.get(id - 1).getTitulo().equalsIgnoreCase("Veículo Elétrico") && postagems.get(idPostagem - 1).getTitulo().equalsIgnoreCase("Veículo Elétrico")){
            // definição de parâmetros para atributos
            avaliacoes.getFirst().setIdAvaliacao(1);
            avaliacoes.getFirst().setCriterio("Mobilidade elétrica");
            avaliacoes.getFirst().setNotaImpacto(70);
            avaliacoes.getFirst().setNotaDificuldade(100);
            if (usuarios.get(id - 1).getTipo().equalsIgnoreCase("img") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("png") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp3") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp4") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("jpeg")) {
                avaliacoes.getFirst().setNotaConfiabilidade(100);
            } else {
                avaliacoes.getFirst().setNotaConfiabilidade(0);
            }
            if (postagems.size() == 1) {
                avaliacoes.getFirst().setNotaFrequencia(25);
            } else if (postagems.size() == 2) {
                avaliacoes.getFirst().setNotaFrequencia(50);
            } else if (postagems.size() == 3) {
                avaliacoes.getFirst().setNotaFrequencia(75);
            } else if (postagems.size() >= 4) {
                avaliacoes.getFirst().setNotaFrequencia(100);
            }
            avaliacoes.getFirst().setDataAvaliacao(dataAtual);
        // verificação de Título da lista informada
        } else if (usuarios.get(id - 1).getTitulo().equalsIgnoreCase("Reciclagem") && postagems.get(idPostagem - 1).getTitulo().equalsIgnoreCase("Reciclagem")){
            // definição de parâmetros para atributos
            avaliacoes.getFirst().setIdAvaliacao(1);
            avaliacoes.getFirst().setCriterio("Reciclagem");
            avaliacoes.getFirst().setNotaImpacto(100);
            avaliacoes.getFirst().setNotaDificuldade(100);
            if (usuarios.get(id - 1).getTipo().equalsIgnoreCase("img") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("png") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp3") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("mp4") || usuarios.get(id - 1).getTipo().equalsIgnoreCase("jpeg")) {
                avaliacoes.getFirst().setNotaConfiabilidade(100);
            } else {
                avaliacoes.getFirst().setNotaConfiabilidade(0);
            }
            if (postagems.size() == 1) {
                avaliacoes.getFirst().setNotaFrequencia(25);
            } else if (postagems.size() == 2) {
                avaliacoes.getFirst().setNotaFrequencia(50);
            } else if (postagems.size() == 3) {
                avaliacoes.getFirst().setNotaFrequencia(75);
            } else if (postagems.size() >= 4) {
                avaliacoes.getFirst().setNotaFrequencia(100);
            }
            avaliacoes.getFirst().setDataAvaliacao(dataAtual);
        }
    }

    public void mostarAvaliacao(List <Avaliacao> avaliacoes, List<Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem)
    {
        // Exibição de avaliação com listas e DateTimeFormatter
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n------------------\nID Post: %d\nTítulo Post: %s\n------------------\nCritério avaliado: %s\nNota Impacto: %d\nNota Dificuldade: %d\nNota Confiabifidade: %d\nNota Frequência: %d\nData: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagems.get(idPostagem - 1).getIdPostagem(), postagems.get(idPostagem - 1).getTitulo(), avaliacoes.getFirst().getCriterio(), avaliacoes.getFirst().getNotaImpacto(), avaliacoes.getFirst().getNotaDificuldade(), avaliacoes.getFirst().getNotaConfiabilidade(), avaliacoes.getFirst().getNotaFrequencia(), avaliacoes.getFirst().getDataAvaliacao().format(dtf)));
    }
}
