package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Postagem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PostagemDAO implements IDAO{
    // Atributo
    private Connection con;
    private Postagem postagem;

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

    // INSERT
    public String inserir(Object object){
        // Conversão
        postagem = (Postagem) object;
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

    // UPDATE
    public String alterar(Object object){
        // Conversão
        postagem = (Postagem) object;
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET ds_titulopost = ?, ds_postagem = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setString(1, postagem.getTitulo());
            ps.setString(2, postagem.getDescricao());
            ps.setInt(3, postagem.getIdPostagem());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "Alterado com Sucesso!";
            } else {
                return "Erro ao Alterar!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // SELECT
    public String exibir(Object object){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Conversão
        postagem = (Postagem) object;
        // Comando SQL
        String sql = "SELECT id_postagem, ds_titulopost, ds_postagem, dt_postagem FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, postagem.getIdPostagem());
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    postagem.setIdPostagem(rs.getInt("id_postagem"));
                    postagem.setTitulo(rs.getString("ds_titulopost"));
                    postagem.setDescricao(rs.getString("ds_postagem"));
                    postagem.setDataPostagem(rs.getDate("dt_postagem").toLocalDate());
                    return String.format("ID: %d\nTítulo: %s\nDescrição: %s\nData: %s", postagem.getIdPostagem(), rs.getString("ds_titulopost"), rs.getString("ds_postagem"), rs.getDate("dt_postagem").toLocalDate().format(dtf));
                } else {
                    return "Registro não encontrado!";
                }
            } catch (SQLException e) {
                return "Erro: " + e.getMessage();
            }
        } catch (SQLException e) {
            return "Erro de SQL!: " + e.getMessage();
        }
    }

    // Exibir formato Objeto
    public Postagem exibirObjeto(Object object){
        // Conversão
        postagem = (Postagem) object;
        // Comando SQL
        String sql = "SELECT id_postagem, ds_titulopost, ds_postagem, dt_postagem FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, postagem.getIdPostagem());
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
            return null;
        }
    }

    // DELETE
    public String excluir(Object object){
        postagem = (Postagem) object;
        // Comando sql para deletar dentro da tabela Postagem
        String sql = "DELETE FROM POSTAGEM WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, postagem.getIdPostagem());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "Excluido com Sucesso!";
            } else {
                return "Erro ao excluir!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Retorna FK
    public int exibirFKArquivo(int idPostagem){
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

    // Retorna FK
    public int exibirFKAvaliacao(int idPostagem){
        int idAvaliacao;
        // Comando SQL
        String sql = "SELECT avaliacao_id_avaliacao FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPostagem);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    idAvaliacao = rs.getInt("avaliacao_id_avaliacao");
                    return idAvaliacao;
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

    // Altera FK
    public String alterarFKArquivo(int idPostagem, int idArquivo){
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
                return "FK Alterada com Sucesso!";
            } else {
                return "Erro ao Alterar a FK!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Altera FK
    public String alterarFKAvaliacao(int idPostagem, int idAvaliacao){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET avaliacao_id_avaliacao = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idAvaliacao);
            ps.setInt(2, idPostagem);
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "FK Alterada com Sucesso!";
            } else {
                return "Erro ao Alterar a FK!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Altera FK
    public String alterarFKPontuacao(int idPostagem, int idPontuacao){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET pontuacao_id_pontuacao = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idPontuacao);
            ps.setInt(2, idPostagem);
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "FK Alterada com Sucesso!";
            } else {
                return "Erro ao Alterar a FK!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // Conta a quantidade de postagens
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
}
