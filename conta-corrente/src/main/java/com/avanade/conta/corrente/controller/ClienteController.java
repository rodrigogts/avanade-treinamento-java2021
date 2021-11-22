package com.avanade.conta.corrente.controller;

import com.avanade.conta.corrente.model.ClienteModel;
import com.avanade.conta.corrente.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cliente")
@OpenAPIDefinition(info = @Info(title = "Cliente Controler", version = "1.0", description = "CRUD Cliente"))
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/salvar")
    public ResponseEntity<ClienteModel> salvar(@RequestBody ClienteModel cliente) {
        ClienteModel clienteResponse = repository.save(cliente);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteModel> buscaPorCodigo(@PathVariable Integer codigo) {
        Optional<ClienteModel> cliente = repository.findById(codigo);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscaPorNome")
    public ResponseEntity<List<ClienteModel>> buscaPorNome(@RequestParam String nome) {
        List<ClienteModel> clientes = repository.findByNome(nome);
        return ResponseEntity.ok(clientes);
    }


}
