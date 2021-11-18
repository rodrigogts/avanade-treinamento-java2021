package com.avanade.aplicacao.dao;

import com.avanade.aplicacao.model.PedidoModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PedidoDao {

    private Connection connection;

    public PedidoDao() throws SQLException {
        connection = criarConexao();
    }

    private Connection criarConexao() throws SQLException {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user","fred");
        props.setProperty("password","secret");
        props.setProperty("ssl","true");
        return DriverManager.getConnection(url, props);
    }

    public PedidoModel inserir(PedidoModel pedido) {

        return pedido;
    }

    public PedidoModel atualizar(PedidoModel pedido) {

        return pedido;
    }

    public PedidoModel buscaPorCodigo(Integer codigo) {
        return null;
    }

}
