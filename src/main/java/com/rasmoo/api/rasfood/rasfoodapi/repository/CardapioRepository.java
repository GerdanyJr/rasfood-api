package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio,Integer>{
    
    @Query(value = "SELECT * FROM cardapio c WHERE c.categoria_id = ?1 AND c.disponivel = true",nativeQuery = true)
    List<Cardapio> consultarPorCategoria(Integer categoria);
}
