package com.avanade.aplicacao.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @Builder
public class ItemModel {

    private Integer numero;
    private BigDecimal quantidade;
    private Integer codigoProduto;
    private String nomeProduto;
    private BigDecimal valorUnitario;
    private BigDecimal valor;

}
