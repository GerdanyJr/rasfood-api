package com.rasmoo.api.rasfood.rasfoodapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.OrdensCardapio;

public interface OrdensCardapioRepository extends PagingAndSortingRepository<OrdensCardapio, Integer> {
    
    OrdensCardapio save(OrdensCardapio ordensCardapio);

    OrdensCardapio delete(OrdensCardapio ordensCardapio);
}
