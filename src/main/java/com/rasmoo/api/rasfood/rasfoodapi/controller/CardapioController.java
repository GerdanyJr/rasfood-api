package com.rasmoo.api.rasfood.rasfoodapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.rasmoo.api.rasfood.rasfoodapi.entity.Cardapio;
import com.rasmoo.api.rasfood.rasfoodapi.repository.CardapioProjection;
import com.rasmoo.api.rasfood.rasfoodapi.repository.CardapioRepository;
import com.rasmoo.api.rasfood.rasfoodapi.util.SortVerification;

@RequestMapping(value = "/cardapio")
@RestController
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<Page<Cardapio>> consultarTodos(@RequestParam("actualPage") Integer actualPage,
            @RequestParam(value = "sort",required = false) Sort.Direction sort, @RequestParam(value = "property",required = false) String property) {
        Pageable pageable = SortVerification.verifySort(actualPage, sort, property);
        return ResponseEntity.status(HttpStatus.OK).body(this.cardapioRepository.findAll(pageable));
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Cardapio> consultarPorId(@PathVariable Integer id) {
        Optional<Cardapio> cardapioEncontrado = this.cardapioRepository.findById(id);
        if (!cardapioEncontrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cardapioEncontrado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/categoria/{categoriaid}")
    public ResponseEntity<Page<Cardapio>> encontrarPorCategoria(@PathVariable("categoriaid") Integer categoriaid,
            @RequestParam("actualPage") Integer actualPage,
            @RequestParam(value = "sort",required = false)Sort.Direction sort,
            @RequestParam(value= "property",required = false)String property) {
        Pageable pageable = SortVerification.verifySort(actualPage, sort, property);
        Page<Cardapio> cardapiosEncontrados = this.cardapioRepository.consultarPorCategoria(categoriaid, pageable);
        if (cardapiosEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cardapiosEncontrados);
    }

    @GetMapping("/{id}/disponivel")
    public ResponseEntity<Page<CardapioProjection>> encontrarTodos(@PathVariable("id") Integer id,
            @RequestParam("actualPage") Integer actualPage,
            @RequestParam(value = "sort",required = false)Sort.Direction sort,
            @RequestParam(value = "property",required = false)String property) {
        Pageable pageable = SortVerification.verifySort(actualPage, sort, property);
        Page<CardapioProjection> cardapiosEncontrados = this.cardapioRepository.consultarPorCategoriaProjection(id,
                pageable);
        if (cardapiosEncontrados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cardapiosEncontrados);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cardapio> salvar(@RequestBody Cardapio cardapio) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cardapioRepository.save(cardapio));
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
    public ResponseEntity<Cardapio> excluir(@PathVariable("id") Integer id) {
        Optional<Cardapio> cardapioEncontrado = this.cardapioRepository.findById(id);
        if (!cardapioEncontrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
