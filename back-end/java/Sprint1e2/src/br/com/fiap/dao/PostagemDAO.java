package br.com.fiap.dao;

import br.com.fiap.bean.Postagem;
import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostagemDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public PostagemDAO(Connection con) {
        this.con = con;
    }

    // Getter
    public Connection getCon() {
        return con;
    }

    // Metodo Exclusivo

    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_postagem), 0) + 1 AS proximo_id FROM POSTAGEM";
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

    public String inserir(Postagem postagem){
        // Comando sql para inserir dentro da tabela Postagem
        String sql = "INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, postagem.getIdPostagem());
            ps.setString(2, postagem.getTitulo());
            ps.setString(3, postagem.getDescricao());
            ps.setDate(4, java.sql.Date.valueOf(postagem.getDataPostagem()));
            ps.setString(5, "S");
            ps.setInt(6, 1);
            ps.setInt(7, 1);
            ps.setInt(8, 1);
            ps.setInt(9, 1);
            ps.setInt(10, 1);
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

    public Postagem exibir(Postagem postagem, int idPostagem){
        // Comando SQL
        String sql = "SELECT id_postagem, ds_titulopost, ds_postagem, dt_postagem FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPostagem);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    postagem.setIdPostagem(rs.getInt("id_postagem"));
                    postagem.setTitulo(rs.getString("ds_titulopost"));
                    postagem.setDescricao(rs.getString("ds_postagem"));
                    postagem.setDataPostagem(rs.getDate("dt_postagem").toLocalDate());
                    return postagem;
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

    public int exibirFKArquivo(Postagem postagem, int idPostagem){
        int idArquivo;
        // Comando SQL
        String sql = "SELECT arquivo_id_arquivo FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPostagem);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    idArquivo = rs.getInt("arquivo_id_arquivo");
                    return idArquivo;
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                return 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public int contarPostagens(){
        // Comando SQL
        String sql = "SELECT COUNT(*) FROM POSTAGEM";
        // try-with-resouces
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 0;
    }

    public void alterar(Postagem postagem, int idPostagem){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET ds_titulopost = ?, ds_postagem = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setString(1, postagem.getTitulo());
            ps.setString(2, postagem.getDescricao());
            ps.setInt(3, idPostagem);
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

    public void alterarFKArquivo(int idPostagem, int idArquivo){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET arquivo_id_arquivo = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idArquivo);
            ps.setInt(2, idPostagem);
            // Verificação
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "FK Alterada com Sucesso!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Alterar a FK!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para exibir usuários cadastrados
    public ArrayList<Postagem> listarCadastrados(){
        // Comando sql
        String sql = "SELECT id_postagem, ds_titulopost FROM POSTAGEM ORDER BY id_postagem";
        // lista para armazenar todos os usuários
        ArrayList<Postagem> postsCadastrados = new ArrayList<>();
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (ps != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    Postagem postagem = new Postagem();
                    // Pegando valor das colunas
                    postagem.setIdPostagem(rs.getInt(1));
                    postagem.setTitulo(rs.getString(2));
                    // Adição de objeto à lista
                    postsCadastrados.add(postagem);
                }
                // Retorno de lista
                return postsCadastrados;
            } else {
                JOptionPane.showMessageDialog(null, "Não há postagens cadastradas!");
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void excluir(int idPostagem){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "DELETE FROM POSTAGEM WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idPostagem);
            // Verificação
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Excluido com Sucesso!", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
