package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClienteDAO {

    public ClienteEntity findByDni(String dni) throws SQLException;

    public void save (Connection connection, ClienteEntity cliente) throws SQLException;

    }
