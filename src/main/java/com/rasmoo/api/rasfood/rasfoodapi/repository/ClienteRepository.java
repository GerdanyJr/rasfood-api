package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Cliente;
import com.rasmoo.api.rasfood.rasfoodapi.entity.ClienteId;
import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente,ClienteId> {
    
    @Query("SELECT c FROM Cliente c WHERE c.clienteId.email = :id OR c.clienteId.cpf = :id")
    Optional<Cliente> findByEmailOrCpf(@Param("id") String id);

    @Query("SELECT e FROM Endereço e WHERE e.cliente.clienteId.cpf = :id OR e.cliente.clienteId.email = :id")
    Page<Endereço> findEndereços(@Param("id")String id,@RequestParam Pageable pageable);

    Optional<Cliente> findById(@Param("ClienteId")ClienteId id);

    Cliente save(Cliente cliente);
}
