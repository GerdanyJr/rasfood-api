package com.rasmoo.api.rasfood.rasfoodapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    
    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Endereço> endereçosList = new ArrayList<>();

    public Cliente(){}

    public Cliente(String nome,Endereço endereço,String email,String cpf) {
        clienteId = new ClienteId(email, cpf);
        this.nome = nome;
        this.endereçosList.add(endereço);
    }

    public Cliente( String nome) {
        this.nome = nome;
    }

    public void addEndereço(Endereço endereço){
        endereço.setCliente(this);
        this.endereçosList.add(endereço);
    }

    public String getCpf(){
        return clienteId.getCpf();
    }

    public void setCpf(String cpf){
        this.clienteId.setCpf(cpf);
    }

    public String getEmail(){
        return clienteId.getEmail();
    }

    public void setEmail(String email){
        this.clienteId.setEmail(email);
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereço> getEndereçosList() {
        return endereçosList;
    }

    public void setEndereçosList(List<Endereço> endereçosList) {
        this.endereçosList = endereçosList;
    }

    @Override
    public String toString() {
        return "Cliente [clienteId=" + clienteId + ", nome=" + nome + ", contato=" + contato + ", endereçosList="
                + endereçosList + "]";
    }
}
