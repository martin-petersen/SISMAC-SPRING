package com.example.starter.controller;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.AtualizacaoClienteFORM;
import com.example.starter.form.ClienteFORM;
import com.example.starter.model.Cliente;
import com.example.starter.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(@RequestParam(required = false) String nome,
                                                          @RequestParam (required = false) String cpf,
                                                          @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomePaciente")
                                                                     Pageable pageable) throws ServiceException {
        if(cpf!=null) {
            Page<Cliente> clientes = clienteService.buscarPorCpf(cpf, pageable);
            return ResponseEntity.ok(clientes);
        } else if(nome!=null) {
            String clienteNome = "%" + nome.toUpperCase() + "%";
            Page<Cliente> clientes = clienteService.buscarPorNome(clienteNome, pageable);
            return ResponseEntity.ok(clientes);
        } else {
            Page<Cliente> clientes = clienteService.buscarTodos(pageable);
            return ResponseEntity.ok(clientes);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid ClienteFORM pacienteForm, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        clienteService.salvar(pacienteForm.convert());
        Cliente paciente = clienteService.buscarPorCpf(pacienteForm.getCpf());
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody AtualizacaoClienteFORM clienteFORM) throws ServiceException {
        Cliente objClienteBusca = new Cliente(clienteFORM);
        Cliente paciente = clienteService.alterar(id,objClienteBusca);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/deletarCadastro")
    @Transactional
    public ResponseEntity<?> deletarCadastro(@RequestParam(required = true) String cpf) {
        try {
            Cliente objPacienteBusca = new Cliente(cpf);
            clienteService.remover(objPacienteBusca);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
