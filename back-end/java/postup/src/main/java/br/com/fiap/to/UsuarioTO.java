package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioTO {
    // Atributos
    private Integer idUsuario;
    @NotBlank
    private String nome;
    @NotEmpty
    @Email
    private String email;
    // Annotation que envia a senha digitada na Request, mas não exibe ela na Response
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    private String senha;
    @PastOrPresent
    private LocalDate dataCadastro;

    // Construtor vazio
    public UsuarioTO() {
    }

    // Getter e Setter
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
