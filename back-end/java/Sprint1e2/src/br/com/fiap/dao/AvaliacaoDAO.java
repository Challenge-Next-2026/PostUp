package br.com.fiap.dao;

import br.com.fiap.bean.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public String inserir(List<Avaliacao> avaliacoes){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, avaliacoes.getFirst().getIdAvaliacao());
            ps.setString(2, avaliacoes.getFirst().getCriterio());
            ps.setInt(3, avaliacoes.getFirst().getNotaImpacto());
            ps.setInt(4, avaliacoes.getFirst().getNotaDificuldade());
            ps.setInt(5, avaliacoes.getFirst().getNotaConfiabilidade());
            ps.setInt(6, avaliacoes.getFirst().getNotaFrequencia());
            ps.setDate(7, java.sql.Date.valueOf(avaliacoes.getFirst().getDataAvaliacao()));
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
