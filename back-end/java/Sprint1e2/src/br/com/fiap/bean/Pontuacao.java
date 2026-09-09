package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pontuacao implements ExibicaoObjetos
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
    // Cálculo com base a lista avaliacoes
    public void calcularPontuacao(Avaliacao avaliacao, int idPontuacao){
        // Cálculo simples de média aritmética
        LocalDate dataAtual = LocalDate.now();
        float calculo = (avaliacao.getNotaImpacto() + avaliacao.getNotaDificuldade() + avaliacao.getNotaConfiabilidade() + avaliacao.getNotaFrequencia()) / 5;
        setIdPontuacao(idPontuacao);
        setValor(calculo);
        setDataPontuacao(dataAtual);
    }

    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n------------------\nID Post: %d\nTítulo Post: %s\n------------------\nPontuação: %.2f\nData: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), pontuacao.getValor(), pontuacao.getDataPontuacao().format(dtf)));
    }
}
