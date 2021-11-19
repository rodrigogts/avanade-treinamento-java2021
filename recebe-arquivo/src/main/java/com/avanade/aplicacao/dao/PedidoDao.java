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

    public Optional<PedidoModel> inserir(PedidoModel pedido) throws SQLException {

        // TODO Se pedido já exisistir, chamar o método atualizar(PedidoModel pedido)
        StringBuilder sb = new StringBuilder();
        sb.append("insert into pedidos  ");
        sb.append("     ( codigo ");
        sb.append("     , codigo_cliente ");
        sb.append("     , valor_total ");
        sb.append("     , numero_cartao ");
        sb.append("     , data )");
        sb.append("values ");
        sb.append("     ( ? ");
        sb.append("     , ? ");
        sb.append("     , ? ");
        sb.append("     , ? ");
        sb.append("     , ? )");

        int idx = 1;
        PreparedStatement pst = connection.prepareStatement(sb.toString());
        pst.setInt(idx++, pedido.getCodigo());
        pst.setInt(idx++, pedido.getCliente().getCodigo());
        pst.setBigDecimal(idx++, pedido.getValorTotal());
        pst.setString(idx++, pedido.getNumeroCartao());
        pst.setDate(idx, (java.sql.Date)pedido.getData());

        int qtdLinhas = pst.executeUpdate();
        if (qtdLinhas == 0) {
            throw new SQLException("Nenhum registro inserido para o pedido []");
        }

        return Optional.of(pedido);
    }

    public Optional<PedidoModel> atualizar(PedidoModel pedido) {
        return Optional.empty();
    }

    public Optional<PedidoModel> buscaPorCodigo(Integer codigoQry) throws SQLException {

        // TODO Criar tabela cliente e relacionar com pedido
        StringBuilder sb = new StringBuilder();
        sb.append("select codigo ");
        sb.append("     , codigo_cliente ");
        sb.append("     , valor_total ");
        sb.append("     , numero_cartao ");
        sb.append("     , data ");
        sb.append("  from pedidos ");
        sb.append(" where codigo = ?");

        int idx = 1;
        PreparedStatement pst = connection.prepareStatement(sb.toString());
        pst.setInt(idx, codigoQry);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return Optional.empty();
        }

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
