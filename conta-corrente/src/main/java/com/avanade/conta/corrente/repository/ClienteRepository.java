package com.avanade.conta.corrente.repository;

import com.avanade.conta.corrente.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    List<ClienteModel> findByNome(String nome);
}
