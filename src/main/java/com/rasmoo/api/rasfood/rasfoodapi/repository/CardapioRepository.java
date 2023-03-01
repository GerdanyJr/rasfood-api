package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rasmoo.api.rasfood.rasfoodapi.dto.CardapioDto;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio,Integer>{
    
    @Query("SELECT new com.rasmoo.api.rasfood.rasfoodapi.dto.CardapioDto(c.nome,c.descricao,c.valor,c.categoria.nome) FROM Cardapio c WHERE c.nome LIKE %:nome% AND c.disponivel = true")
    List<CardapioDto> findAllByNome(String nome);

    @Query(value = "SELECT * FROM cardapio c WHERE c.categoria_id = ?1 AND c.disponivel = true",nativeQuery = true)
    List<Cardapio> consultarPorCategoria(Integer categoria);

    @Query(value = "SELECT c.nome AS nome,c.descricao AS descricao,c.valor AS valor,cat.nome AS nomeCategoria FROM cardapio c INNER JOIN categorias cat ON c.categoria_id = cat.id WHERE c.categoria_id = :id AND c.disponivel = true",nativeQuery = true)
    List<CardapioProjection> consultarPorCategoriaProjection(Integer id);
}
