package com.avanade.conta.corrente.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    private String nome;
    @OneToMany
    private Set<ContaCorrenteModel> contasCorrente;

}
