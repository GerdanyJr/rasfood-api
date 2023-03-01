package com.rasmoo.api.rasfood.rasfoodapi.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardapioDto {
    
    private Integer id;
    private String nome;
    private String descrição;
    private BigDecimal valor;
    private String nomeCategoria;

    public CardapioDto(){}


    public CardapioDto(String nome, String descrição, BigDecimal valor, String nomeCategoria) {
        this.nome = nome;
        this.descrição = descrição;
        this.valor = valor;
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
