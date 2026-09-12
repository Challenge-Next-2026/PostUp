package br.com.fiap.model.dao;

import java.sql.SQLException;

public interface IDAO {
    public int obterProximoId() throws SQLException;

    public String inserir(Object object);

    public String alterar(Object object);

    public String exibir(Object object);

    public String excluir(Object object);
}
