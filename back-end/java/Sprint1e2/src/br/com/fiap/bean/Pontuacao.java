package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pontuacao extends Postagem
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
    public void calcularPontuacao(Pontuacao pontuacao, List <Avaliacao> avaliacoes, List<Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem){
        // Verificação de postagens e avaliacoes
        if (postagems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há postagens realizadas");
        } else if (avaliacoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A postagem ainda não foi avaliada");
        } else {
            // Cálculo simples de média aritmética
            LocalDate dataAtual = LocalDate.now();
            float calculo = (avaliacoes.getFirst().getNotaImpacto() + avaliacoes.getFirst().getNotaDificuldade() + avaliacoes.getFirst().getNotaConfiabilidade() + avaliacoes.getFirst().getNotaFrequencia()) / 5;
            pontuacao.setIdPontuacao(1);
            pontuacao.setValor(calculo);
            pontuacao.setDataPontuacao(dataAtual);
        }
    }

    public void exibirPontuacao(Pontuacao pontuacao, List<Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n------------------\nID Post: %d\nTítulo Post: %s\n------------------\nPontuação: %.2f\nData: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagems.get(idPostagem - 1).getIdPostagem(), postagems.get(idPostagem - 1).getTitulo(), pontuacao.getValor(), pontuacao.getDataPontuacao().format(dtf)));
    }
}
