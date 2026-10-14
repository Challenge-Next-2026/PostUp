package br.com.fiap.to;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import javax.swing.*;
import java.time.LocalDate;

public class ArquivoTO {
    // Atributos
    private int idArquivo;
    @NotEmpty
    private String nomeArquivo;
    @NotEmpty
    private String tipo;
    @NotNull
    @Positive
    private int tamanho;
    @NotEmpty
    private String url;
    @PastOrPresent
    private LocalDate dataUpload;

    // Construtores
    public ArquivoTO(){
    }

    public ArquivoTO(int idArquivo, String nomeArquivo, String tipo, int tamanho, String url, LocalDate dataUpload) {
        this.idArquivo = idArquivo;
        this.nomeArquivo = nomeArquivo;
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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
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

    /*

    // Metodos da classe
    // Cadastra um objeto Arquivo na memória
    public String importarArquivo()
    {
        LocalDate dataAtual = LocalDate.now();

        setNomeArquivo(JOptionPane.showInputDialog("Informe o nome do arquivo:"));
        setTipo(JOptionPane.showInputDialog("Informe o tipo do arquivo:"));
        setTamanho(JOptionPane.showInputDialog("Informe o tamanho do arquivo:"));
        setUrl(JOptionPane.showInputDialog("Informe a URL"));
        setDataUpload(dataAtual);
        return "Arquivo Importado!";
    }

    public String alterarArquivo()
    {
        LocalDate dataAtual = LocalDate.now();

        setNomeArquivo(JOptionPane.showInputDialog("Informe o novo nome do arquivo:"));
        setTipo(JOptionPane.showInputDialog("Informe o novo tipo do arquivo:"));
        setTamanho(JOptionPane.showInputDialog("Informe o novo tamanho do arquivo:"));
        setUrl(JOptionPane.showInputDialog("Informe a nova URL:"));
        setDataUpload(dataAtual);
        return "Arquivo editado!";
    }

    // Cadastra um objeto Arquivo com atributos preenchidos na memória
    public String acessarCamera()
    {
        // Preenchimento de atributos das listas arquivos, usuarios e postagens
        LocalDate dataAtual = LocalDate.now();
        setNomeArquivo("Câmera");
        setTipo("img");
        setTamanho("500mb");
        setUrl("https://acesso-camera.img");
        setDataUpload(dataAtual);
        JOptionPane.showMessageDialog(null, "Câmera acessada com sucesso!");
        return "Câmera acessada!";
    }

    */
}
