package com.willyan.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willyan.spring_jpa.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByTitulo(String titulo);

    List<Categoria> findByTituloStartsWith(String titulo);

    List<Categoria> findByTituloIn(List<String> titulos);

    List<Categoria> findByOrderByTituloAsc();
}
