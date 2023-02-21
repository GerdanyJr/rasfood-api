package com.rasmoo.api.rasfood.rasfoodapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereços")
public class Endereço {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String rua;
    private String complemento;
    private String estado;
    private String cidade;

    @JsonIgnore
    @ManyToOne
    private Cliente cliente;

    public Endereço(){}

    public Endereço(String cep, String rua, String complemento, String estado, String cidade, Cliente cliente) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.estado = estado;
        this.cidade = cidade;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Endereço [id=" + id + ", cep=" + cep + ", rua=" + rua + ", complemento=" + complemento + ", estado="
                + estado + ", cidade=" + cidade + "]";
    }
}
