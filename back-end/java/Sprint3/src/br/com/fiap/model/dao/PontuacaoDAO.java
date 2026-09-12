package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Pontuacao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PontuacaoDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public PontuacaoDAO(Connection con) {
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
        String sql = "SELECT NVL(MAX(id_pontuacao), 0) + 1 AS proximo_id FROM PONTUACAO";
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

    // INSERT
    public String inserir(Pontuacao pontuacao){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao) VALUES (?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, pontuacao.getIdPontuacao());
            ps.setFloat(2, pontuacao.getValor());
            ps.setString(3, "Avaliação");
            ps.setDate(4, java.sql.Date.valueOf(pontuacao.getDataPontuacao()));
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

    // SELECT
    public Pontuacao exibir(Pontuacao pontuacao, int idPontuacao){
        // Comando SQL
        String sql = "SELECT id_pontuacao, vl_pontuacao, dt_pontuacao FROM PONTUACAO WHERE id_pontuacao = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPontuacao);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    pontuacao.setIdPontuacao(rs.getInt("id_pontuacao"));
                    pontuacao.setValor(rs.getInt("vl_pontuacao"));
                    pontuacao.setDataPontuacao(rs.getDate("dt_pontuacao").toLocalDate());
                    return pontuacao;
                } else {
                    JOptionPane.showMessageDialog(null, "Não há nenhuma pontuação no banco", "Erro", JOptionPane.ERROR_MESSAGE);
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
}
