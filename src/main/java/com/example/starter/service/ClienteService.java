package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Cliente;
import com.example.starter.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    public ClienteRepository clienteRepository;

    @Transactional
    public Cliente alterar(Long id, Cliente attCliente) throws ServiceException {
        if (clienteRepository.findById(id).isPresent()) {
            Cliente cliente = clienteRepository.findById(id).get();
            cliente.setClienteUpdate(attCliente);
            clienteRepository.save(cliente);
            return cliente;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Cliente", "Não foi encontrado esse cliente no sistema");
        }
    }

    public boolean salvar(Cliente cliente) {
        clienteRepository.save(cliente);
        return true;
    }

    public Page<Cliente> buscarTodos(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> buscarPorNome(String nome, Pageable pageable) throws ServiceException {
        if(clienteRepository.findByNomeCliente(nome,pageable) != null) {
            return clienteRepository.findByNomeCliente(nome,pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cliente","Não foram encontrados clientes com esse nome");
        }
    }

    public void remover (Cliente cliente) {
        Cliente aSerRemovido = new Cliente();
        if(cliente == null) {
            return;
        } else if (cliente.getCpf() != null) {
            aSerRemovido.setCliente(clienteRepository.findByCpf(cliente.getCpf()));
        }
        clienteRepository.deleteById(aSerRemovido.getId());
    }

    public Cliente buscarPorCpf(String cpf) throws ServiceException {
        if(clienteRepository.findByCpf(cpf) != null) {
            return clienteRepository.findByCpf(cpf);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cliente","Não foi encontrado cliente com esse cpf");
        }
    }

    public Page<Cliente> buscarPorCpf(String cpf, Pageable pageable) throws ServiceException {
        if(clienteRepository.findByCpf(cpf) != null) {
            return clienteRepository.findByCpf(cpf, pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cliente","Não foi encontrado cliente com esse cpf");
        }
    }
}
