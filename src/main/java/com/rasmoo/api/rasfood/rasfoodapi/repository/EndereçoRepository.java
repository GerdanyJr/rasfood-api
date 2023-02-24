package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

public interface EndereçoRepository extends JpaRepository<Endereço,Integer>{
    
    @Query("SELECT e FROM Endereço e WHERE e.cliente.clienteId.email = :id OR e.cliente.clienteId.cpf = :id")
    Optional<Endereço> findClienteByEndereço(@Param("id") String id);
}

