package br.com.fiap.dao;

import br.com.fiap.to.ArquivoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArquivoDAO {
    // Atributo
    private ArquivoTO arquivo;

    // Metodos Exclusivo
    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_arquivo), 0) + 1 AS proximo_id FROM ARQUIVO";
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
    public ArquivoTO save(ArquivoTO arquivo){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload) VALUES (?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, arquivo.getIdArquivo());
            ps.setString(2, arquivo.getNomeArquivo());
            ps.setString(3, arquivo.getTipo());
            ps.setInt(4, arquivo.getTamanho());
            ps.setString(5, arquivo.getUrl());
            ps.setDate(6, java.sql.Date.valueOf(arquivo.getDataUpload()));
            // Verificação
            if (ps.executeUpdate() > 0) {
                return arquivo;
            } else {
                System.out.println("Erro ao Inserir!");
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
    public ArquivoTO update(ArquivoTO arquivo){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "UPDATE ARQUIVO SET nm_arquivo = ?, in_tipoarquivo = ?, ds_tamanho = ?, cod_url = ? WHERE id_arquivo = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando UPDATE
            ps.setString(1, arquivo.getNomeArquivo());
            ps.setString(2, arquivo.getTipo());
            ps.setInt(3, arquivo.getTamanho());
            ps.setString(4, arquivo.getUrl());
            ps.setInt(5, arquivo.getIdArquivo());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return arquivo;
            } else {
                System.out.println("Erro ao Inserir!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    // Metodo para exibir arquivos cadastrados
    public ArrayList<ArquivoTO> findAll(){
        // lista para armazenar todos os usuários
        ArrayList<ArquivoTO> arquivosCadastrados = new ArrayList<>();
        // Comando sql
        String sql = "SELECT id_arquivo, nm_arquivo FROM ARQUIVO ORDER BY id_arquivo";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (rs != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    ArquivoTO arquivo = new ArquivoTO();
                    // Pegando valor das colunas
                    arquivo.setIdArquivo(rs.getInt("id_arquivo"));
                    arquivo.setNomeArquivo(rs.getString("nm_arquivo"));
                    // Adição de objeto à lista
                    arquivosCadastrados.add(arquivo);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        // Retorno de lista
        return arquivosCadastrados;
    }

    // SELECT
    public ArquivoTO findById(int idArquivo){
        arquivo = new ArquivoTO();
        // Comando SQL
        String sql = "SELECT id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload FROM ARQUIVO WHERE id_arquivo = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idArquivo);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    arquivo.setIdArquivo(rs.getInt("id_arquivo"));
                    arquivo.setNomeArquivo(rs.getString("nm_arquivo"));
                    arquivo.setTipo(rs.getString("in_tipoarquivo"));
                    arquivo.setTamanho(rs.getInt("ds_tamanho"));
                    arquivo.setUrl(rs.getString("cod_url"));
                    arquivo.setDataUpload(rs.getDate("dt_upload").toLocalDate());
                } else {
                    System.out.println("Registro não encontrado");
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return arquivo;
    }

    // DELETE
    public boolean delete(int idArquivo){
        // Comando sql para deletar dentro da tabela Postagem
        String sql = "DELETE FROM ARQUIVO WHERE id_arquivo = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idArquivo);
            // Retorno
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return false;
    }

    // UPDATE avaliando a sessão iniciada
    public ArquivoTO saveByOwner(ArquivoTO arquivo, int idUsuario) throws SQLException {
        // Abre a conexão
        Connection con = ConnectionFactory.getConnection();
        // Armazena o Auto commit
        boolean autoCommitOriginal = con.getAutoCommit();
        try {
            // Desativa o auto commit
            con.setAutoCommit(false);
            // Verifica se a postagem pertence ao usuário logado
            String sqlPostagem = " SELECT id_postagem FROM POSTAGEM_USUARIO WHERE id_postagem = ? AND id_usuario = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlPostagem)) {
                // Preenchimento de ?
                ps.setInt(1, arquivo.getIdPostagem());
                ps.setInt(2, idUsuario);
                // try-with-resources
                try (ResultSet rs = ps.executeQuery()) {
                    // Se o SGBD não executar, dá um rollback
                    if (!rs.next()) {
                        System.out.println("DEBUG 1: postagem não pertence ao usuário.");
                        con.rollback();
                        return null;
                    }
                }
            }

            // Impede substituir um arquivo já associado à postagem
            // Consulta que pega a FK arquivo ta tabela Postagem
            String sqlVerificarArquivo = " SELECT arquivo_id_arquivo FROM POSTAGEM WHERE id_postagem = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlVerificarArquivo)) {
                // Preenchendo ?
                ps.setInt(1, arquivo.getIdPostagem());
                // try-with-resources
                try (ResultSet rs = ps.executeQuery()) {
                    // Se a consulta não for executada, ou o campo da FK estiver vazio, dá um rollback
                    if (!rs.next()) {
                        System.out.println("DEBUG 2: postagem inexistente ou já possui arquivo.");
                        con.rollback();
                        return null;
                    }
                }
            }

            // Obtém o próximo ID disponível
            String sqlId = " SELECT NVL(MAX(id_arquivo), 0) + 1 AS proximo_id FROM ARQUIVO";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlId);
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                // Atualiza o id do objeto de acordo com o proximo id disponível no banco
                arquivo.setIdArquivo(rs.getInt("proximo_id"));
            }
            // Atribuição de data
            if (arquivo.getDataUpload() == null) {
                arquivo.setDataUpload(LocalDate.now());
            }
            // Insere o arquivo
            String sqlInsert = " INSERT INTO ARQUIVO (id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload) VALUES (?, ?, ?, ?, ?, ?)";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                // Preenchendo ?
                ps.setInt(1, arquivo.getIdArquivo());
                ps.setString(2, arquivo.getNomeArquivo());
                ps.setString(3, arquivo.getTipo());
                ps.setInt(4, arquivo.getTamanho());
                ps.setString(5, arquivo.getUrl());
                ps.setDate(6, java.sql.Date.valueOf(arquivo.getDataUpload()));
                ps.executeUpdate();
            }

            // Altera o arquivo da postagem, confirmando novamente a propriedade
            String sqlAssociar = " UPDATE POSTAGEM SET arquivo_id_arquivo = ? WHERE id_postagem = ? AND EXISTS ( SELECT 1 FROM POSTAGEM_USUARIO pu WHERE pu.id_postagem = POSTAGEM.id_postagem AND pu.id_usuario = ?)";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlAssociar)) {
                // Preenchendo ?
                ps.setInt(1, arquivo.getIdArquivo());
                ps.setInt(2, arquivo.getIdPostagem());
                ps.setInt(3, idUsuario);
                // Se a consulta não for executada, dá um rollback
                if (ps.executeUpdate() != 1) {
                    System.out.println("DEBUG 3: falha ao associar o arquivo à postagem.");
                    con.rollback();
                    return null;
                }
            }
            // Realiza o commit
            con.commit();
            // Retorna o objeto
            return arquivo;
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

    // UPDATE avaliando a sessão iniciada
    public ArquivoTO updateByOwner(ArquivoTO arquivo, int idUsuario) throws SQLException {
        // Abrindo conexão
        Connection con = ConnectionFactory.getConnection();
        // Comando SQL
        // Verifica se o arquivo pertence ao usuário da sessão e se a postagem está correta
        String sql = " UPDATE ARQUIVO SET nm_arquivo = ?, in_tipoarquivo = ?, ds_tamanho = ?, cod_url = ? WHERE id_arquivo = ? AND EXISTS (SELECT 1 FROM POSTAGEM p JOIN POSTAGEM_USUARIO pu ON pu.id_postagem = p.id_postagem WHERE p.arquivo_id_arquivo = ARQUIVO.id_arquivo AND pu.id_usuario = ?)";
        // try-with-resources
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            // Preenchendo ?
            ps.setString(1, arquivo.getNomeArquivo());
            ps.setString(2, arquivo.getTipo());
            ps.setInt(3, arquivo.getTamanho());
            ps.setString(4, arquivo.getUrl());
            ps.setInt(5, arquivo.getIdArquivo());
            ps.setInt(6, idUsuario);
            // Se o comando não executar, retorna null
            if (ps.executeUpdate() != 1) {
                return null;
            } else {
                return arquivo;
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // DELETE de arquivo de acordo com o usuario logado e a postagem relacionada
    public boolean deleteByOwner(int idArquivo, int idUsuario) throws SQLException {
        // Abrindo conexão
        Connection con = ConnectionFactory.getConnection();
        // Armazenando o auto commit
        boolean autoCommitOriginal = con.getAutoCommit();
        try {
            // Desativando o auto commit
            con.setAutoCommit(false);
            // Verifica se o arquivo está associado a uma postagem do usuário
            String sqlVerificar = "SELECT 1 FROM POSTAGEM p JOIN POSTAGEM_USUARIO pu ON pu.id_postagem = p.id_postagem WHERE p.arquivo_id_arquivo = ? AND pu.id_usuario = ?";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlVerificar)) {
                // Preenchendo ?
                ps.setInt(1, idArquivo);
                ps.setInt(2, idUsuario);
                // try-with-resources
                try (ResultSet rs = ps.executeQuery()) {
                    // Se a consulta não executar, dá um rollback
                    if (!rs.next()) {
                        con.rollback();
                        return false;
                    }
                }
            }

            // Desassocia o arquivo das postagens do usuário
            String sqlDesassociar = "UPDATE POSTAGEM p SET arquivo_id_arquivo = NULL WHERE p.arquivo_id_arquivo = ? AND EXISTS (SELECT 1 FROM POSTAGEM_USUARIO pu WHERE pu.id_postagem = p.id_postagem AND pu.id_usuario = ?)";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlDesassociar)) {
                // Preenchimento de ?
                ps.setInt(1, idArquivo);
                ps.setInt(2, idUsuario);
                // Se o update não for executado, dá um rollback
                if (ps.executeUpdate() < 1) {
                    con.rollback();
                    return false;
                }
            }

            // Exclui somente se nenhuma postagem ainda referenciar o arquivo
            String sqlDelete = "DELETE FROM ARQUIVO a WHERE a.id_arquivo = ? AND NOT EXISTS (SELECT 1 FROM POSTAGEM p WHERE p.arquivo_id_arquivo = a.id_arquivo)";
            // try-with-resources
            try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
                // Preenchendo ?
                ps.setInt(1, idArquivo);
                // Se o delete não for executado, dá um rollback
                if (ps.executeUpdate() != 1) {
                    con.rollback();
                    return false;
                }
            }
            // Confirma as operações com commit
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
}
