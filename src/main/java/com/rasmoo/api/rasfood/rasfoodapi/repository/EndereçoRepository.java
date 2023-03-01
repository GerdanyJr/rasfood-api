package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

public interface EndereçoRepository extends JpaRepository<Endereço,Integer>{

    List<Endereço> findByCep(String Cep);
}

