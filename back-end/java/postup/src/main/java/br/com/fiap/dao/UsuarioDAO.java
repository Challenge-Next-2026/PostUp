package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO{
    // Atributo
    private UsuarioTO usuario;

    // Metodos exclusivos
    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_usuario), 0) + 1 AS proximo_id FROM USUARIO";
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
    public UsuarioTO save(UsuarioTO usuario) throws SQLException{
        // Comando sql para inserir dentro da tabela Usuario
        String sql = "INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setDate(5, java.sql.Date.valueOf(usuario.getDataCadastro()));
            ps.setString(6, "T");
            ps.setInt(7, usuario.getIdUsuario());
            // Verificação
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Login
    public UsuarioTO login(String email, String senha) throws SQLException{
        // Comando SQL
        String sql = "SELECT id_usuario, nm_usuario, ds_email, dt_cadastro FROM USUARIO WHERE ds_email = ? AND ds_senha = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            // Substituindo os ?
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                // Se o Select retornar um usuário
                if (rs.next()) {
                    // Novo objeto usuario
                    UsuarioTO usuario = new UsuarioTO();
                    // Preenchimento de atributos
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nm_usuario"));
                    usuario.setEmail(rs.getString("ds_email"));
                    usuario.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
                    // Retorno
                    return usuario;
                }
                return null;
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // Verificação de email
    public boolean findEmail(String email) throws SQLException {
        // Comando SQL
        String sql = "SELECT 1 FROM USUARIO WHERE ds_email = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            // Preenchimento de ?
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                // Se o select retornar alguma coisa, o metodo retorna true
                return rs.next();
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // UPDATE permite a edição apenas do usuário logado
    public UsuarioTO update(UsuarioTO usuario, int idUsuarioLogado) throws SQLException {
        // Comando SQL
        String sql = "UPDATE USUARIO SET ds_email = ?, ds_senha = ? WHERE id_usuario = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            // Preenchimento de ?
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, idUsuarioLogado);
            // Se o comando executar, retorna o objeto TO
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    // SELECT
    public UsuarioTO findById(int idUsuario){
        usuario = new UsuarioTO();
        // Comando SQL
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        // try-with-resources
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há usuários
                if (rs.next()) {
                    usuario.setIdUsuario(idUsuario);
                    usuario.setNome(rs.getString("nm_usuario"));
                    usuario.setEmail(rs.getString("ds_email"));
                    usuario.setSenha(rs.getString("ds_senha"));
                    usuario.setDataCadastro(rs.getDate("dt_cadastro").toLocalDate());
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
        return usuario;
    }

    // Metodo para exibir usuários cadastrados
    public ArrayList<UsuarioTO> findAll(){
        // lista para armazenar todos os usuários
        ArrayList<UsuarioTO> usuariosCadastrados = new ArrayList<>();
        // Comando sql
        String sql = "SELECT id_usuario, nm_usuario FROM USUARIO ORDER BY id_usuario";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            // Confere se há usuários na tabela
            if (rs != null) {
                // laço que pega usuário por usuário
                while (rs.next()){
                    // Novo objeto
                    UsuarioTO usuario = new UsuarioTO();
                    // Pegando valor das colunas
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nm_usuario"));
                    // Adição de objeto à lista
                    usuariosCadastrados.add(usuario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuariosCadastrados;
    }

    // Excluir
    public boolean delete(int idUsuario){
        // Comando sql para excluir dentro da tabela Usuario
        String sql = "DELETE FROM USUARIO WHERE id_usuario = ?";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, idUsuario);
            // Retorno
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
