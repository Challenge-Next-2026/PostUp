package br.com.fiap.dao;

import br.com.fiap.bean.Postagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public String inserir(List<Postagem> postagems, int idPostagem){
        // Comando sql para inserir dentro da tabela Postagem
        String sql = "INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem) VALUES (?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, postagems.get(idPostagem - 1).getIdPostagem());
            ps.setString(2, postagems.get(idPostagem - 1).getTitulo());
            ps.setString(3, postagems.get(idPostagem - 1).getDescricao());
            ps.setDate(5, java.sql.Date.valueOf(postagems.get(idPostagem - 1).getDataPostagem()));
            ps.setString(6, "S");
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
