package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;

public class Arquivo extends Postagem
{
    // Atributos
    private int idArquivo;
    private String nome;
    private String tipo;
    private float tamanho;
    private String url;
    private LocalDate dataUpload;

    // Construtores
    public Arquivo(){
    }

    public Arquivo(int idUsuario, String nome, String email, String senha, LocalDate dataCadastro, int idPostagem, String titulo, String descricao, LocalDate dataPostagem, Boolean status, int idArquivo, String nome1, String tipo, float tamanho, String url, LocalDate dataUpload) {
        super(idUsuario, nome, email, senha, dataCadastro, idPostagem, titulo, descricao, dataPostagem, status);
        this.idArquivo = idArquivo;
        this.nome = nome1;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.url = url;
        this.dataUpload = dataUpload;
    }

    // Getter e Setter
    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
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
    public void importarArquivo(int idPostagem)
    {
        LocalDate dataAtual = LocalDate.now();
        String nome, tipo, url, auxiliar;
        try {
            nome = JOptionPane.showInputDialog("Informe o nome do arquivo:");
            setNome(nome);
            tipo = JOptionPane.showInputDialog("Informe o tipo do arquivo:");
            setTipo(tipo);
            auxiliar = JOptionPane.showInputDialog("Informe o tamanho do arquivo:");
            float tamanho = Float.parseFloat(auxiliar);
            setTamanho(tamanho);
            url = JOptionPane.showInputDialog("Informe a URL");
            setUrl(url);
            setDataUpload(dataAtual);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void acessarCamera()
    {
        JOptionPane.showMessageDialog(null, "Câmera acessada com sucesso!");
    }
}
