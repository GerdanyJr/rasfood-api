package com.rasmoo.api.rasfood.rasfoodapi.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Categoria;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,Integer> {
    
    Page<Categoria> findByNomeStartingWith(String nome,Pageable pageable);

    Categoria save(Categoria categoria);

    Optional<Categoria> findById(Integer id);

    Categoria delete(Categoria categoria);
}
