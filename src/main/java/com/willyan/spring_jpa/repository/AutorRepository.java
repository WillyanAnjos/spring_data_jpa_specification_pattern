package com.willyan.spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.projection.AutorInfoProjection;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select a from Autor a where a.infoAutor.cargo like :cargo order by a.nome asc")
    List<Autor> findByCargo(@Param("cargo") String cargo);

    @Query("select a from Autor a where a.nome like :termo OR a.sobrenome like :termo")
    List<Autor> findAllByNomeOrSobrenome(String termo);

    @Query("select a.nome as nome, a.sobrenome as sobrenome, a.infoAutor.cargo as cargo, a.infoAutor.bio as bio " +
            "from Autor a where a.id = :id")
    AutorInfoProjection findAutorInfoById(Long id);
}
