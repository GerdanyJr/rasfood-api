package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Cliente;
import com.rasmoo.api.rasfood.rasfoodapi.entity.ClienteId;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,ClienteId> {
    
    @Query("SELECT c FROM Cliente c WHERE c.clienteId.email = :id OR c.clienteId.cpf = :id")
    Optional<Cliente> findByEmailOrCpf(@Param("id") String id);

    @Query("SELECT e FROM Endereço e WHERE e.cliente.clienteId.cpf = :id OR e.cliente.clienteId.email = :id")
    List<Endereço> findEndereços(@Param("id")String id);
}
