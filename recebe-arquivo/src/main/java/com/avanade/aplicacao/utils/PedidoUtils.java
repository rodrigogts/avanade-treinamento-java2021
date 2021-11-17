package com.avanade.aplicacao.utils;

import com.avanade.aplicacao.model.ClienteModel;
import com.avanade.aplicacao.model.PedidoModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Slf4j
public final class PedidoUtils {

    public static final int TAMANHO_CAMPOS_PEDIDO = 7;
    private static final SimpleDateFormat SDF;

    static {
        SDF = new SimpleDateFormat("ddMMyyyy");
    }

    private PedidoUtils() {
    }

    public static Optional<PedidoModel> criarPedido(String...campos) {
        if (campos.length != TAMANHO_CAMPOS_PEDIDO) {
            return Optional.empty();
        }

        try {
            Idx idx = Idx.create();
            PedidoModel pedido = PedidoModel.builder()
                    .codigo(Integer.valueOf(campos[idx.inc()]))
                    .cliente(criarCliente(idx, campos).orElse(null))
                    .valorTotal(new BigDecimal(campos[idx.inc()]))
                    .numeroCartao(campos[idx.inc()])
                    .data(SDF.parse(campos[idx.inc()]))
                    .build();
            return Optional.of(pedido);

        } catch (Exception ex) {
            log.error("Falha ao criar Pedido [Campos=>{}]", campos, ex);
            return Optional.empty();
        }
    }

    private static Optional<ClienteModel> criarCliente(Idx idx, String...campos) {
        try {
            ClienteModel cliente = ClienteModel.builder()
                    .codigo(Integer.valueOf(campos[idx.inc()]))
                    .nome(campos[idx.inc()])
                    .build();
            return Optional.of(cliente);
        } catch (Exception ex) {
            log.error("Falha ao criar Cliente [Campos=>{}]", campos, ex);
            return Optional.empty();
        }
    }

    static class Idx {
        private int idx;

        public static Idx create() {
            return new Idx();
        }

        Idx() {
            idx = 1;
        }

        public int inc() {
            return idx++;
        }
    }

}
