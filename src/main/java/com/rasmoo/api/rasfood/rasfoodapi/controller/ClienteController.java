package com.rasmoo.api.rasfood.rasfoodapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Cliente;
import com.rasmoo.api.rasfood.rasfoodapi.entity.ClienteId;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;
import com.rasmoo.api.rasfood.rasfoodapi.repository.ClienteRepository;

@RequestMapping(value = "/cliente")
@RestController
public class ClienteController {

    @Autowired
    public ClienteRepository clienteRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/{email}/{cpf}")
    public ResponseEntity<Cliente> consultarPorEmailCpf(@PathVariable("email")String email,@PathVariable("cpf")String cpf){
        ClienteId clienteId = new ClienteId(email, cpf);
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(!cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id")String id ,@RequestBody Cliente cliente) throws JsonMappingException{
        Optional<Cliente> clienteEncontrado = this.clienteRepository.findByEmailOrCpf(id);
        if(clienteEncontrado.isPresent()){
            objectMapper.updateValue(clienteEncontrado.get(),cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteEncontrado.get());
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
