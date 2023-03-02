package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

public interface EndereçoRepository extends PagingAndSortingRepository<Endereço,Integer>{

    Page<Endereço> findByCep(String Cep,Pageable pageable);

    Optional<Endereço> findById(Integer id);

    Endereço save(Endereço endereço);

    Endereço delete(Endereço endereço);
}

