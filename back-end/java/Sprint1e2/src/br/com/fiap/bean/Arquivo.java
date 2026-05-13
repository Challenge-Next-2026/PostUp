package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Arquivo extends Avaliacao
{
    // Atributos
    private int idArquivo;
    private String nomeArquivo;
    private String tipo;
    private float tamanho;
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
    // Simulação de importação com listas
    public void importarArquivo(List <Usuario> usuarios, int id, List <Postagem> postagems, int idPostagem, List <Arquivo> arquivos, int idArquivo)
    {
        LocalDate dataAtual = LocalDate.now();
        String nome, tipo, url, auxiliar;
        // Tratamento de erros
        try {
            // Preenchimento de atributos das listas arquivos, usuarios e postagens
            nome = JOptionPane.showInputDialog("Informe o nome do arquivo:");
            usuarios.get(id - 1).setNomeArquivo(nome);
            postagems.get(idPostagem - 1).setNomeArquivo(nome);
            arquivos.get(idArquivo - 1).setNomeArquivo(nome);
            tipo = JOptionPane.showInputDialog("Informe o tipo do arquivo:");
            usuarios.get(id - 1).setTipo(tipo);
            postagems.get(idPostagem - 1).setTipo(tipo);
            arquivos.get(idArquivo - 1).setTipo(tipo);
            auxiliar = JOptionPane.showInputDialog("Informe o tamanho do arquivo:");
            float tamanho = Float.parseFloat(auxiliar);
            usuarios.get(id - 1).setTamanho(tamanho);
            postagems.get(idPostagem - 1).setTamanho(tamanho);
            arquivos.get(idArquivo - 1).setTamanho(tamanho);
            url = JOptionPane.showInputDialog("Informe a URL");
            usuarios.get(id - 1).setUrl(url);
            postagems.get(idPostagem - 1).setUrl(url);
            arquivos.get(idArquivo - 1).setUrl(url);
            usuarios.get(id - 1).setDataUpload(dataAtual);
            postagems.get(idPostagem - 1).setDataUpload(dataAtual);
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
        usuarios.get(id - 1).setNomeArquivo("Câmera");
        postagems.get(idPostagem - 1).setNomeArquivo("Câmera");
        arquivos.get(idArquivo - 1).setNomeArquivo("Câmera");
        usuarios.get(id - 1).setTipo("img");
        postagems.get(idPostagem - 1).setTipo("img");
        arquivos.get(idArquivo - 1).setTipo("img");
        usuarios.get(id - 1).setTamanho(0);
        postagems.get(idPostagem - 1).setTamanho(0);
        arquivos.get(idArquivo - 1).setTamanho(0);
        usuarios.get(id - 1).setUrl("xxxxxxxxxxxxxxx.img");
        postagems.get(idPostagem - 1).setUrl("xxxxxxxxxxxxxxx.img");
        arquivos.get(idArquivo - 1).setUrl("xxxxxxxxxxxxxxx.img");
        usuarios.get(id - 1).setDataUpload(dataAtual);
        postagems.get(idPostagem - 1).setDataUpload(dataAtual);
        arquivos.get(idArquivo - 1).setDataUpload(dataAtual);
        JOptionPane.showMessageDialog(null, "Câmera acessada com sucesso!");
    }
}
