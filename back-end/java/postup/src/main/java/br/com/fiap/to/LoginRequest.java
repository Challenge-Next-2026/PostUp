package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Classe que representa os dados que o usuário precisa para realizar login
public class LoginRequest {
    // Atributos
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

    // Construtor vazio
    public LoginRequest() {
    }

    // Getter e Setter
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
}
