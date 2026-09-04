package br.com.fiap.dao;

import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
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

    // Metodos exclusivo
    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_usuario), 0) + 1 AS proximo_id FROM USUARIO";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Condicional que avalia se a próxima linha está preenchida
            if (rs.next()) {
                // retorna o próximo id
                return rs.getInt("proximo_id");
            }
        }
        throw new SQLException("Não foi possível obter o próximo ID.");
    }

    // Metodo para buscar um usuário no banco com base no ID para LOGIN
    public Usuario buscarUsuario(int idUsuario, String email, String senha) throws SQLException {
        // Comando SQL para fazer a consolta
        String sql = "SELECT id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro FROM USUARIO WHERE id_usuario = ? AND ds_email = ? AND ds_senha = ?";
        // try-with-resources passando o comando sql para um objeto ps
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            // Completando o comando sql substituindo as interrogações
            ps.setInt(1, idUsuario);
            ps.setString(2, email);
            ps.setString(3, senha);
            // try-with-resources passando o metodo de execução do comando sql para um objeto rs
            try (ResultSet rs = ps.executeQuery()) {
                // Condição avaliando se há um campo preenchido no banco
                if (rs.next()) {
                    // novo objeto Usuário
                    Usuario usuario = new Usuario();
                    // Armazenamento de resultado da consulta nos Setters
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nm_usuario"));
                    usuario.setEmail(rs.getString("ds_email"));
                    usuario.setSenha(rs.getString("ds_senha"));
                    usuario.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
                    // Retorno de objeto
                    return usuario;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        // retorno nulo
        return null;
    }

    public String inserir(Usuario usuario){
        // Comando sql para inserir dentro da tabela Usuario
        String sql = "INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setDate(5, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(6, "S");
            ps.setInt(7, usuario.getIdUsuario());
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

    // Metodo para exibir usuários cadastrados
    public ArrayList<Usuario> listarCadastrados(){
        // Comando sql
        String sql = "SELECT id_usuario, nm_usuario FROM USUARIO ORDER BY id_usuario";
        // lista para armazenar todos os usuários
        ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (ps != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    Usuario usuario = new Usuario();
                    // Pegando valor das colunas
                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNome(rs.getString(2));
                    // Adição de objeto à lista
                    usuariosCadastrados.add(usuario);
                }
                // Retorno de lista
                return usuariosCadastrados;
            } else {
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Usuario exibir(Usuario usuario, int idUsuario){
        // Comando SQL
        String sql = "SELECT id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro FROM USUARIO WHERE id_usuario = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há usuários
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nm_usuario"));
                    usuario.setEmail(rs.getString("ds_email"));
                    usuario.setSenha(rs.getString("ds_senha"));
                    usuario.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
                    return usuario;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void alterarEmail(Usuario usuario){
        // Comando sql para alterar dentro da tabela Usuario
        String sql = "UPDATE USUARIO SET ds_email = ? WHERE id_usuario = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setString(1, usuario.getEmail());
            ps.setInt(2, usuario.getIdUsuario());
            // Verificação
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Alterar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void alterarSenha(Usuario usuario){
        // Comando sql para alterar dentro da tabela Usuario
        String sql = "UPDATE USUARIO SET ds_senha = ? WHERE id_usuario = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setString(1, usuario.getSenha());
            ps.setInt(2, usuario.getIdUsuario());
            // Verificação
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Alterar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
