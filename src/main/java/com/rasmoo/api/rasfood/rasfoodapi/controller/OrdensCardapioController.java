package com.rasmoo.api.rasfood.rasfoodapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmoo.api.rasfood.rasfoodapi.entity.OrdensCardapio;
import com.rasmoo.api.rasfood.rasfoodapi.repository.OrdensCardapioRepository;

@RestController
@RequestMapping("/ordenscardapio")
public class OrdensCardapioController {

    @Autowired
    OrdensCardapioRepository ordensCardapioRepository;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/salvar")
    public ResponseEntity<OrdensCardapio> salvar(@RequestBody OrdensCardapio ordensCardapio) {
        return ResponseEntity.ok().body(ordensCardapioRepository.save(ordensCardapio));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Page<OrdensCardapio>> consultarPorOrdensId(@PathVariable("id") String ordensId,
            @RequestParam("actualPage") Integer actualPage) {
        Pageable pageable = PageRequest.of(actualPage, 1);
        return ResponseEntity.ok().body(ordensCardapioRepository.findByOrdensId(actualPage, pageable));
    }

    @PatchMapping("/id")
    public ResponseEntity<OrdensCardapio> atualizar(@RequestBody OrdensCardapio ordensCardapio,
            @PathVariable("id") Integer id) throws JsonMappingException {
        Optional<OrdensCardapio> ordensCardapioEncontrado = ordensCardapioRepository.findById(id);
        if (ordensCardapioEncontrado.isPresent()) {
            objectMapper.updateValue(ordensCardapioEncontrado, ordensCardapio);
            return ResponseEntity.ok().body(ordensCardapioRepository.save(ordensCardapioEncontrado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<OrdensCardapio> deletar(@PathVariable("id") Integer id) {
        Optional<OrdensCardapio> ordensCardapioEncontrado = ordensCardapioRepository.findById(id);
        if (ordensCardapioEncontrado.isPresent()) {
            return ResponseEntity.ok().body(ordensCardapioRepository.delete(ordensCardapioEncontrado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
