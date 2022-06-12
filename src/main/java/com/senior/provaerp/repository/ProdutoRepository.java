package com.senior.provaerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senior.provaerp.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
