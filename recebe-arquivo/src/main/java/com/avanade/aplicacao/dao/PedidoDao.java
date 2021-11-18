package com.avanade.aplicacao.dao;

import com.avanade.aplicacao.model.ClienteModel;
import com.avanade.aplicacao.model.PedidoModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class PedidoDao {

    private Connection connection;

    public PedidoDao() throws SQLException {
        connection = criarConexao();
        log.info("Conexão executada com sucesso");
    }

    private Connection criarConexao() throws SQLException {
        String url = "jdbc:postgresql://localhost/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        return DriverManager.getConnection(url, props);
    }

    public PedidoModel inserir(PedidoModel pedido) {

        return pedido;
    }

    public PedidoModel atualizar(PedidoModel pedido) {

        return pedido;
    }

    public Optional<PedidoModel> buscaPorCodigo(Integer codigoQry) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("select codigo ");
        sb.append("     , codigo_cliente ");
        sb.append("     , valor_total ");
        sb.append("     , numero_cartao ");
        sb.append("     , data ");
        sb.append("  from pedidos ");
        sb.append(" where codigo = " + codigoQry);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sb.toString());

        if (!rs.next()) {
            return Optional.empty();
        }

        int idx = 1;
        Integer codigo = rs.getInt(idx++);
        Integer codigoCliente = rs.getInt(idx++);
        BigDecimal valorTotal = rs.getBigDecimal(idx++);
        String numeroCartao = rs.getString(idx++);
        Date data = rs.getDate(idx);

        ClienteModel cliente = ClienteModel.builder()
                .codigo(codigoCliente)
                .build();

        PedidoModel pedido = PedidoModel.builder()
                .codigo(codigo)
                .cliente(cliente)
                .valorTotal(valorTotal)
                .numeroCartao(numeroCartao)
                .data(data).build();

        return Optional.of(pedido);
    }

}
