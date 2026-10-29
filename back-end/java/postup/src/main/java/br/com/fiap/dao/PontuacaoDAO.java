package br.com.fiap.dao;

import br.com.fiap.to.PontuacaoTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;

public class PontuacaoDAO {
    // Atributo
    private PontuacaoTO pontuacao;

    // Metodos exclusivo
    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_pontuacao), 0) + 1 AS proximo_id FROM PONTUACAO";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Condicional que avalia se a próxima linha está preenchida
            if (rs.next()) {
                // retorna o próximo id
                return rs.getInt("proximo_id");
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
        throw new SQLException("Não foi possível obter o próximo ID.");
    }

    // CREATE / INSERT
    public PontuacaoTO save(PontuacaoTO pontuacao){
        // Comando sql para inserir dentro da tabela Usuario
        String sql = "INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao) VALUES (?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, pontuacao.getIdPontuacao());
            ps.setFloat(2, pontuacao.getValor());
            ps.setString(3, "Avaliação");
            ps.setDate(4, java.sql.Date.valueOf(pontuacao.getDataPontuacao()));
            // Verificação
            if (ps.executeUpdate() > 0) {
                return pontuacao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    // UPDATE
    public PontuacaoTO update(PontuacaoTO pontuacao){
        // Comando sql para alterar dentro da tabela Usuario
        String sql = "UPDATE PONTUACAO SET vl_pontuacao = ?, dt_pontuacao = ? WHERE id_pontuacao = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setDouble(1, pontuacao.getValor());
            ps.setDate(2, Date.valueOf(pontuacao.getDataPontuacao()));
            ps.setInt(3, pontuacao.getIdPontuacao());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return pontuacao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQl: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    // SELECT
    public PontuacaoTO findById(int idPontuacao){
        pontuacao = new PontuacaoTO();
        // Comando SQL
        String sql = "SELECT * FROM PONTUACAO WHERE id_pontuacao = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPontuacao);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há usuários
                if (rs.next()) {
                    pontuacao.setIdPontuacao(idPontuacao);
                    pontuacao.setValor(rs.getFloat("vl_pontuacao"));
                    pontuacao.setDataPontuacao(rs.getDate("dt_pontuacao").toLocalDate());
                } else {
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pontuacao;
    }

    // Metodo para exibir usuários cadastrados
    public ArrayList<PontuacaoTO> findAll(){
        // lista para armazenar todos os usuários
        ArrayList<PontuacaoTO> pontuacoesCadastradas = new ArrayList<>();
        // Comando sql
        String sql = "SELECT id_pontuacao, vl_pontuacao FROM PONTUACAO ORDER BY id_pontuacao";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (rs != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    PontuacaoTO pontuacao = new PontuacaoTO();
                    // Pegando valor das colunas
                    pontuacao.setIdPontuacao(rs.getInt("id_pontuacao"));
                    pontuacao.setValor(rs.getFloat("vl_pontuacao"));
                    // Adição de objeto à lista
                    pontuacoesCadastradas.add(pontuacao);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pontuacoesCadastradas;
    }

    // Excluir
    public boolean delete(int idPontuacao){
        // Comando sql para excluir dentro da tabela Usuario
        String sql = "DELETE FROM PONTUACAO WHERE id_pontuacao = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idPontuacao);
            // Retorno
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    // Salvar pontuação com base na avaliação da postagem de um usuário cadastrado
    public PontuacaoTO saveByOwner(Connection con, PontuacaoTO pontuacao, int idPostagem) throws SQLException {
        // Consulta para pegar o próximo id disponível
        String sqlId = "SELECT NVL(MAX(id_pontuacao), 0) + 1 FROM PONTUACAO";
        // try-with-resources
        try (PreparedStatement ps = con.prepareStatement(sqlId);
             ResultSet rs = ps.executeQuery()) {
            // Se a consulta não for executada, lança uma exceção
            if (!rs.next()) {
                throw new SQLException("Não foi possível gerar o ID da pontuação.");
            } else {
                // Atribui o numero resgatado ao id
                pontuacao.setIdPontuacao(rs.getInt(1));
            }
        }
        // Se a data for nula, atribui a data atual
        if (pontuacao.getDataPontuacao() == null) {
            pontuacao.setDataPontuacao(java.time.LocalDate.now());
        }
        // Insert de nova pontuação
        String sqlInsert = "INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao) VALUES (?, ?, ?, ?)";
        // try-with-resources
        try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
            // Preenchimento de ?
            ps.setInt(1, pontuacao.getIdPontuacao());
            ps.setFloat(2, pontuacao.getValor());
            ps.setString(3, "Avaliação automática");
            ps.setDate(4, Date.valueOf(pontuacao.getDataPontuacao()));
            // Se não executar, lança uma exceção
            if (ps.executeUpdate() != 1) {
                throw new SQLException("Não foi possível salvar a pontuação.");
            }
        }
        // Update na tabela postagem de fk pontuação
        String sqlAssociar = "UPDATE POSTAGEM SET pontuacao_id_pontuacao = ? WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = con.prepareStatement(sqlAssociar)) {
            // Preenchendo ?
            ps.setInt(1, pontuacao.getIdPontuacao());
            ps.setInt(2, idPostagem);
            // Se não executar, lança uma exceção
            if (ps.executeUpdate() != 1) {
                throw new SQLException("Não foi possível associar a pontuação à postagem.");
            }
        }
        // Retorna o objeto
        return pontuacao;
    }
}
