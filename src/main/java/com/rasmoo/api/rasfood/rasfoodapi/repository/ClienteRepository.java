package com.rasmoo.api.rasfood.rasfoodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Cliente;
import com.rasmoo.api.rasfood.rasfoodapi.entity.ClienteId;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,ClienteId> {
    
}
