package com.willyan.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.willyan.spring_jpa.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco> {
}