package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CardapioProjection {
    
    Integer getId();
    String getNome();
    String getDescricao();
    String getNomeCategoria();
    BigDecimal getValor();
}
