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
import com.rasmoo.api.rasfood.rasfoodapi.entity.Categoria;
import com.rasmoo.api.rasfood.rasfoodapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<Page<Categoria>> encontrarTodos(@RequestParam("actualPage")Integer actualPage){
        Pageable pageable = PageRequest.of(actualPage, 5);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll(pageable));
    }

    @GetMapping("/consultar/{nome}")
    public ResponseEntity<Page<Categoria>> encontrarPorNome(@PathVariable("nome")String nome,@RequestParam("actualPage")Integer actualPage){
        Pageable pageable = PageRequest.of(actualPage, 5);
        Page<Categoria> categoriasEncontradas = categoriaRepository.findByNomeStartingWith(nome,pageable);
        if(categoriasEncontradas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoriasEncontradas);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
        if(categoriaEncontrada.isPresent()){
            categoriaRepository.delete(categoriaEncontrada.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Categoria categoria,@PathVariable("id") Integer id) throws JsonMappingException{
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
        if (categoriaEncontrada.isPresent()){
            objectMapper.updateValue(categoriaEncontrada.get(), categoria);
            return ResponseEntity.status(HttpStatus.OK).body(this.categoriaRepository.save(categoriaEncontrada.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
