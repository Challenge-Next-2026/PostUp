package br.com.fiap.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO {
    public int obterProximoId() throws SQLException;

    public Object save(Object object);

    public Object update(Object object);

    public ArrayList<Object> findAll();

    public Object findById(Object object);

    public Object delete(Object object);
}
