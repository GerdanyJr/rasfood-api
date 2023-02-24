package com.rasmoo.api.rasfood.rasfoodapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import com.rasmoo.api.rasfood.rasfoodapi.entity.Endereço;

public interface EndereçoRepository extends JpaRepository<Endereço,Integer>{
}

