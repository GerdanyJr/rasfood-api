package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.rasmoo.api.rasfood.rasfoodapi.entity.OrdensCardapio;

public interface OrdensCardapioRepository extends PagingAndSortingRepository<OrdensCardapio, Integer> {
    
    OrdensCardapio save(OrdensCardapio ordensCardapio);

    OrdensCardapio delete(OrdensCardapio ordensCardapio);

    Optional<OrdensCardapio> findById(Integer id);

    @Query("SELECT oc FROM OrdensCardapio oc WHERE oc.ordem.id = :clienteId")
    Page<OrdensCardapio> findByOrdensId(@Param("clienteId")Integer clienteId,Pageable pageable);
}
