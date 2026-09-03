package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Arquivo implements ExibicaoObjetos
{
    // Atributos
    private int idArquivo;
    private String nomeArquivo;
    private String tipo;
    private String tamanho;
    private String url;
    private LocalDate dataUpload;

    // Construtores
    public Arquivo(){
    }

    // Getter e Setter
    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDate dataUpload) {
        this.dataUpload = dataUpload;
    }

    // Metodos da classe
    // Simulação de importação com listas
    public void importarArquivo(List <Arquivo> arquivos, int idArquivo)
    {
        LocalDate dataAtual = LocalDate.now();
        String nome, tipo, url, auxiliar;
        // Tratamento de erros
        try {
            // Preenchimento de atributos das listas arquivos, usuarios e postagens
            nome = JOptionPane.showInputDialog("Informe o nome do arquivo:");
            arquivos.get(idArquivo - 1).setNomeArquivo(nome);
            tipo = JOptionPane.showInputDialog("Informe o tipo do arquivo:");
            arquivos.get(idArquivo - 1).setTipo(tipo);
            String tamanho = JOptionPane.showInputDialog("Informe o tamanho do arquivo:");
            arquivos.get(idArquivo - 1).setTamanho(tamanho);
            url = JOptionPane.showInputDialog("Informe a URL");
            arquivos.get(idArquivo - 1).setUrl(url);
            arquivos.get(idArquivo - 1).setDataUpload(dataAtual);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Simulação de acesso com listas
    public void acessarCamera(List <Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem, List <Arquivo> arquivos, int idArquivo)
    {
        // Preenchimento de atributos das listas arquivos, usuarios e postagens
        LocalDate dataAtual = LocalDate.now();
        arquivos.get(idArquivo - 1).setNomeArquivo("Câmera");
        arquivos.get(idArquivo - 1).setTipo("img");
        arquivos.get(idArquivo - 1).setTamanho("0");
        arquivos.get(idArquivo - 1).setUrl("xxxxxxxxxxxxxxx.img");
        arquivos.get(idArquivo - 1).setDataUpload(dataAtual);
        JOptionPane.showMessageDialog(null, "Câmera acessada com sucesso!");
    }

    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, String.format("ID User: %d\nNome User: %s\n----------------\nID Post: %d\nTítulo Post: %s\n-----------------\nID Arquivo: %d\nNome: %s\nTipo: %s\nTamanho: %s\nURL: %s\nData Upload: %s", usuarios.get(id - 1).getIdUsuario(), usuarios.get(id - 1).getNome(), postagens.get(idPostagem - 1).getIdPostagem(), postagens.get(idPostagem - 1).getTitulo(), arquivos.get(contador3 - 1).getIdArquivo(), arquivos.get(contador3 - 1).getNomeArquivo(), arquivos.get(contador3 - 1).getTipo(), arquivos.get(contador3 - 1).getTamanho(), arquivos.get(contador3 - 1).getUrl(), arquivos.get(contador3 - 1).getDataUpload().format(dtf)));
    }
}
