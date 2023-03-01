package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    
    List<Categoria> findByNomeStartingWith(String nome);
}
