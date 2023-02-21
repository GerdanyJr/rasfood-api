package com.rasmoo.api.rasfood.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordens_cardapio")
public class OrdensCardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordens_id")
    private Ordem ordem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "cardapio_id")
    private Cardapio cardapio;

    @Column(name = "valor_de_registro")
    private BigDecimal valorDeRegistro;

    private Integer quantidade;

    public OrdensCardapio(){}

    public OrdensCardapio(Ordem ordem, Cardapio cardapio, Integer quantidade) {
        this.ordem = ordem;
        this.cardapio = cardapio;
        this.quantidade = quantidade;
        this.valorDeRegistro = cardapio.getValor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public BigDecimal getValorDeRegistro() {
        return valorDeRegistro;
    }

    public void setValorDeRegistro(BigDecimal valor) {
        this.valorDeRegistro = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "OrdensCardapio [id=" + id + ", ordem=" + ordem + ", cardapio=" + cardapio + ", valor=" + valorDeRegistro
                + ", quantidade=" + quantidade + "]";
    } 
}
