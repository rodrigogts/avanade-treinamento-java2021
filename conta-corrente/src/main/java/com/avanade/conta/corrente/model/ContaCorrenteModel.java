package com.avanade.conta.corrente.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "conta_corrente")
public class ContaCorrenteModel {

    @Id
    private String codigo;
    @ManyToOne
    private ClienteModel cliente;
    private Date dataHora;
    private BigDecimal valorEntrada;
    private BigDecimal valorSaida;
}
