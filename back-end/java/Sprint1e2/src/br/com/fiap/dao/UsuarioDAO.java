package br.com.fiap.dao;

import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class UsuarioDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parãmetro
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // Getter
    public Connection getCon() {
        return con;
    }

    // Metodo exclusivo
    public String inserir(List<Usuario> usuarios, int idUsuario){
        // Comando sql para inserir dentro da tabela Usuario
        String sql = "INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, usuarios.get(idUsuario - 1).getIdUsuario());
            ps.setString(2, usuarios.get(idUsuario - 1).getNome());
            ps.setString(3, usuarios.get(idUsuario - 1).getEmail());
            ps.setString(4, usuarios.get(idUsuario - 1).getSenha());
            ps.setDate(5, java.sql.Date.valueOf(usuarios.get(idUsuario - 1).getDataCadastro()));
            ps.setString(6, "S");
            ps.setInt(7, idUsuario);
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso!";
            } else {
                return "Erro ao Inserir!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
}
