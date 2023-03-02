package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rasmoo.api.rasfood.rasfoodapi.dto.CardapioDto;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Cardapio;

public interface CardapioRepository extends PagingAndSortingRepository<Cardapio,Integer>{
    
    @Query("SELECT new com.rasmoo.api.rasfood.rasfoodapi.dto.CardapioDto(c.nome,c.descricao,c.valor,c.categoria.nome) FROM Cardapio c WHERE c.nome LIKE %:nome% AND c.disponivel = true")
    Page<CardapioDto> findAllByNome(String nome, Pageable pageable);

    @Query(value = "SELECT * FROM cardapio c WHERE c.categoria_id = ?1 AND c.disponivel = true",nativeQuery = true,countQuery = "SELECT count(*) FROM cardapio")
    Page<Cardapio> consultarPorCategoria(Integer categoria, Pageable pageable);

    @Query(value = "SELECT c.nome AS nome,c.descricao AS descricao,c.valor AS valor,cat.nome AS nomeCategoria FROM cardapio c INNER JOIN categorias cat ON c.categoria_id = cat.id WHERE c.categoria_id = :id AND c.disponivel = true",nativeQuery = true)
    Page<CardapioProjection> consultarPorCategoriaProjection(Integer id, Pageable pageable);

    Optional<Cardapio> findById(Integer id);

    Cardapio save(Cardapio cardapio);
}
