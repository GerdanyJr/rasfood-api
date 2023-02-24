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
import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;
import com.rasmoo.api.rasfood.rasfoodapi.repository.EndereçoRepository;

@RequestMapping(value = "/endereco")
@RestController
public class EndereçoController {

    @Autowired
    private EndereçoRepository endereçoRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @GetMapping
    public ResponseEntity<List<Endereço>> consultarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(endereçoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereço> consultarPorId(@PathVariable("id") Integer id){
        Optional<Endereço> endereçoEncontrado = endereçoRepository.findById(id);
        if(!endereçoEncontrado.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(endereçoEncontrado.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Endereço> atualizarEndereço(@PathVariable("id") Integer id,@RequestBody Endereço endereço) throws JsonMappingException{
        Optional<Endereço> endereçoEncontrado = endereçoRepository.findById(id);
        if(!endereçoEncontrado.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        objectMapper.updateValue(endereçoEncontrado.get(), endereço);
        return ResponseEntity.status(HttpStatus.OK).body(endereçoEncontrado.get());
    }
}
