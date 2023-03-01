package com.rasmoo.api.rasfood.rasfoodapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Cardapio;
import com.rasmoo.api.rasfood.rasfoodapi.repository.CardapioRepository;

@RequestMapping(value = "/cardapio")
@RestController
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<Cardapio>> consultarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAll());
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Cardapio> consultarPorId(@PathVariable Integer id){
        Optional<Cardapio> cardapioEncontrado = cardapioRepository.findById(id);
        if (!cardapioEncontrado.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cardapioEncontrado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cardapio> salvar(@RequestBody Cardapio cardapio){
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.save(cardapio));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cardapio> atualizar(@PathVariable("id") Integer id, @RequestBody Cardapio cardapio)
            throws JsonMappingException {
        Optional<Cardapio> cardapioEncontrado = this.cardapioRepository.findById(id);
        if (cardapioEncontrado.isPresent()) {
            objectMapper.updateValue(cardapioEncontrado.get(), cardapio);
            return ResponseEntity.status(HttpStatus.OK).body(this.cardapioRepository.save(cardapioEncontrado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Cardapio> excluir(@PathVariable("id")Integer id){
        Optional<Cardapio> cardapioEncontrado = cardapioRepository.findById(id);
        if (!cardapioEncontrado.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
