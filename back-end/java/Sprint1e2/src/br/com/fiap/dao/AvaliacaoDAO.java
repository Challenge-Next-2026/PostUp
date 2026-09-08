package br.com.fiap.dao;

import br.com.fiap.bean.Avaliacao;
import br.com.fiap.bean.Postagem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public AvaliacaoDAO(Connection con) {
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
        String sql = "SELECT NVL(MAX(id_avaliacao), 0) + 1 AS proximo_id FROM AVALIACAO";
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

    public String inserir(Avaliacao avaliacao){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, avaliacao.getIdAvaliacao());
            ps.setString(2, avaliacao.getCriterio());
            ps.setInt(3, avaliacao.getNotaImpacto());
            ps.setInt(4, avaliacao.getNotaDificuldade());
            ps.setInt(5, avaliacao.getNotaConfiabilidade());
            ps.setInt(6, avaliacao.getNotaFrequencia());
            ps.setDate(7, java.sql.Date.valueOf(avaliacao.getDataAvaliacao()));
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

    public Avaliacao exibir(Avaliacao avaliacao, int idAvaliacao){
        // Comando SQL
        String sql = "SELECT id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao FROM AVALIACAO WHERE id_avaliacao = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idAvaliacao);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
                    avaliacao.setCriterio(rs.getString("ds_criterioavaliacao"));
                    avaliacao.setNotaImpacto(rs.getInt("vl_notaimpacto"));
                    avaliacao.setNotaDificuldade(rs.getInt("vl_notadificuldade"));
                    avaliacao.setNotaConfiabilidade(rs.getInt("vl_notaconfiabilidade"));
                    avaliacao.setNotaFrequencia(rs.getInt("vl_notafrequencia"));
                    avaliacao.setDataAvaliacao(rs.getDate("dt_avaliacao").toLocalDate());
                    return avaliacao;
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
}
