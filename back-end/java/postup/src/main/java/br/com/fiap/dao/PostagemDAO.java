package br.com.fiap.dao;

import br.com.fiap.to.PostagemTO;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.sql.Types.INTEGER;

public class PostagemDAO {
    // Atributo
    private PostagemTO postagem;

    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_postagem), 0) + 1 AS proximo_id FROM POSTAGEM";
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
    public PostagemTO save(PostagemTO postagem){
        // Comando sql para inserir dentro da tabela Postagem
        String sql = "INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, postagem.getIdPostagem());
            ps.setString(2, postagem.getTitulo());
            ps.setString(3, postagem.getDescricao());
            ps.setDate(4, java.sql.Date.valueOf(postagem.getDataPostagem()));
            ps.setString(5, "T");
            ps.setInt(6, 1);
            ps.setInt(7, 1);
            ps.setInt(8, 1);
            ps.setInt(9, 1);
            ps.setInt(10, 1);
            // Verificação
            if (ps.executeUpdate() > 0) {
                return postagem;
            } else {
                System.out.println("Erro ao inserir!");
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
    public PostagemTO update(PostagemTO postagem){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET ds_titulopost = ?, ds_postagem = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setString(1, postagem.getTitulo());
            ps.setString(2, postagem.getDescricao());
            ps.setInt(3, postagem.getIdPostagem());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return postagem;
            } else {
                System.out.println("Erro ao Alterar!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    // SELECT
    public PostagemTO findById(int idPostagem){
        postagem = new PostagemTO();
        // Comando SQL
        String sql = "SELECT id_postagem, ds_titulopost, ds_postagem, dt_postagem FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idPostagem);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    postagem.setIdPostagem(rs.getInt("id_postagem"));
                    postagem.setTitulo(rs.getString("ds_titulopost"));
                    postagem.setDescricao(rs.getString("ds_postagem"));
                    postagem.setDataPostagem(rs.getDate("dt_postagem").toLocalDate());
                } else {
                    System.out.println("Registro não encontrado!");
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return postagem;
    }

    // Metodo para exibir postagens cadastradas
    public ArrayList<PostagemTO> findAll(){
        // Comando sql
        String sql = "SELECT id_postagem, ds_titulopost FROM POSTAGEM ORDER BY id_postagem";
        // lista para armazenar todos os usuários
        ArrayList<PostagemTO> postsCadastrados = new ArrayList<>();
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (rs != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    PostagemTO postagem = new PostagemTO();
                    // Pegando valor das colunas
                    postagem.setIdPostagem(rs.getInt(1));
                    postagem.setTitulo(rs.getString(2));
                    // Adição de objeto à lista
                    postsCadastrados.add(postagem);
                }
            } else {
                System.out.println("Não há postagens cadastradas!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        // Retorno de lista
        return postsCadastrados;
    }

    // DELETE
    public boolean delete(int idPostagem){
        // Comando sql para deletar dentro da tabela Postagem
        String sql = "DELETE FROM POSTAGEM WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1,idPostagem);
            // Retorno
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    // Metodo para atribuir a criação de uma postagem a um usuário já logado, com sua sessão iniciada
    public PostagemTO savePostWithUser(PostagemTO postagem, int idUsuario) throws SQLException {
        // Comando SQL para inserir uma postagem
        String sqlPostagem = "INSERT INTO POSTAGEM (id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Comando SQL para registrar a postagem criada anterior ao usuário logado
        String sqlRelacionamento = "INSERT INTO POSTAGEM_USUARIO (id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (?, ?, ?)";
        // abrindo conexão
        var conexao = ConnectionFactory.getConnection();
        // Verificando se ela existe
        if (conexao == null) {
            throw new SQLException("Não foi possível estabelecer conexão com o banco.");
        }
        // Se tudo der certo faz o commit no banco automático
        boolean autocommitAnterior = conexao.getAutoCommit();

        try {
            conexao.setAutoCommit(false);
            // Gera o ID dentro da mesma conexão e transação.
            try (PreparedStatement psId = conexao.prepareStatement(
                    "SELECT NVL(MAX(id_postagem), 0) + 1 FROM POSTAGEM");
                 ResultSet rs = psId.executeQuery()) {
                if (rs.next()) {
                    // Pega o próximo id disponível, a partir da consulta armazenada no objeto psId
                    postagem.setIdPostagem(rs.getInt(1));
                } else {
                    throw new SQLException("Não foi possível gerar o ID da postagem.");
                }
            }
            // Insere a postagem.
            try (PreparedStatement ps = conexao.prepareStatement(sqlPostagem)) {
                ps.setInt(1, postagem.getIdPostagem());
                ps.setString(2, postagem.getTitulo());
                ps.setString(3, postagem.getDescricao());
                ps.setDate(4, Date.valueOf(postagem.getDataPostagem()));
                ps.setString(5, "T");
                ps.setInt(6, 1);
                ps.setNull(7, INTEGER);
                ps.setInt(8, 1);
                ps.setNull(9, INTEGER);
                ps.setNull(10, INTEGER);

                ps.executeUpdate();
            }

            // Vincula a postagem ao usuário logado.
            try (PreparedStatement ps = conexao.prepareStatement(sqlRelacionamento)) {
                ps.setInt(1, idUsuario);
                ps.setInt(2, postagem.getIdPostagem());
                ps.setInt(3, 99);

                ps.executeUpdate();
            }

            conexao.commit();
            return postagem;

        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException erroRollback) {
                e.addSuppressed(erroRollback);
            }

            throw e;

        } finally {
            try {
                conexao.setAutoCommit(autocommitAnterior);
            } finally {
                ConnectionFactory.closeConnection();
            }
        }
    }

    // UPDATE com confirmação de idUsuario e idPostagem
    public boolean updateByOwner(PostagemTO postagem, int idUsuario) throws SQLException {
        // Comando SQL
        // Consulta encadeada para verificar se na tabela Postagem_Usuario há o id da postagem a ser alterada, e o id do usuário que criou a postagem
        String sql = "UPDATE POSTAGEM p SET ds_titulopost = ?, ds_postagem = ? WHERE p.id_postagem = ? AND EXISTS (SELECT 1 FROM POSTAGEM_USUARIO pu WHERE pu.id_postagem = p.id_postagem AND pu.id_usuario = ?)";
        // try-with-resouces
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            // Preenchendo os ?
            ps.setString(1, postagem.getTitulo());
            ps.setString(2, postagem.getDescricao());
            ps.setInt(3, postagem.getIdPostagem());
            ps.setInt(4, idUsuario);
            // Retorno
            return ps.executeUpdate() > 0;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // DELETE com confirmação de idUsuario e idPostagem
    public boolean deleteByOwner(int idPostagem, int idUsuario) throws SQLException {
        // Abertura da conexão
        Connection con = ConnectionFactory.getConnection();
        // Verificação
        if (con == null) {
            throw new SQLException("Não foi possível conectar ao banco.");
        }
        // Preparando o auto commit se der tudo certo
        boolean autoCommitOriginal = con.getAutoCommit();
        try {
            // Desativando o auto commit
            con.setAutoCommit(false);
            // Verifica se a postagem pertence ao usuário
            String sqlVerificar = "SELECT 1 FROM POSTAGEM_USUARIO WHERE id_postagem = ? AND id_usuario = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlVerificar)) {
                // Preenchendo os ?
                ps.setInt(1, idPostagem);
                ps.setInt(2, idUsuario);
                // try-with-resources
                try (ResultSet rs = ps.executeQuery()) {
                    // Se o SGBD der erro e não executar, um rollback é aplicado
                    if (!rs.next()) {
                        con.rollback();
                        return false;
                    }
                }
            }
            // Exclui o registro de Postagem_Usuário
            String sqlVinculo = "DELETE FROM POSTAGEM_USUARIO WHERE id_postagem = ? AND id_usuario = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlVinculo)) {
                // Preenchimento de ?
                ps.setInt(1, idPostagem);
                ps.setInt(2, idUsuario);
                // Se der erro, executa um rollback
                if (ps.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }
            // Exclui a postagem
            String sqlPostagem = "DELETE FROM POSTAGEM WHERE id_postagem = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlPostagem)) {
                // Preenchimento de ?
                ps.setInt(1, idPostagem);
                // Se der erro, executa um rollback
                if (ps.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }
            // Confirma as duas exclusões com commit
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            try {
                con.setAutoCommit(autoCommitOriginal);
            } finally {
                ConnectionFactory.closeConnection();
            }
        }
    }

    // Retorna FK
    public int exibirFKArquivo(int idPostagem){
        int idArquivo;
        // Comando SQL
        String sql = "SELECT arquivo_id_arquivo FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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
            System.out.println("Erro de SQL!: " + e.getMessage());
            return 0;
        }
    }

    // Retorna FK
    public int exibirFKAvaliacao(int idPostagem){
        int idAvaliacao;
        // Comando SQL
        String sql = "SELECT avaliacao_id_avaliacao FROM POSTAGEM WHERE id_postagem = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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
            System.out.println("Erro de SQL!: " + e.getMessage());
            return 0;
        }
    }

    // Altera FK
    public String alterarFKArquivo(int idPostagem, int idArquivo){
        // Comando sql para alterar dentro da tabela Postagem
        String sql = "UPDATE POSTAGEM SET arquivo_id_arquivo = ? WHERE id_postagem = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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

    // Conta a quantidade de postagens de um usuário logado
    public int countPosts(int idUsuario) {
        // Comando SQL
        String sql = "SELECT COUNT(*) FROM POSTAGEM_USUARIO WHERE id_usuario = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            // Preenchendo ?
            ps.setInt(1, idUsuario);
            // try-with-resources
            try (ResultSet rs = ps.executeQuery()) {
                // Se executar, retorna o numero de postagens
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao contar postagens do usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return 0;
    }
}
