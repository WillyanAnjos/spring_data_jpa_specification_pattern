package com.willyan.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.willyan.spring_jpa.entity.Categoria;
import com.willyan.spring_jpa.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @PostMapping
    public List<Categoria> salvar(@RequestBody List<Categoria> categorias) {
        return this.service.save(categorias);
    }

    @GetMapping("titulo/{titulo}")
    public Categoria getByTitulo(@PathVariable String titulo) {
        return this.service.findByTitulo(titulo);
    }

    @GetMapping("titulo/inicio/{titulo}")
    public List<Categoria> getByInicioTitulo(@PathVariable String titulo) {
        return this.service.findByInicioTitulo(titulo);
    }

    @GetMapping("titulos")
    public List<Categoria> getByTitulos(@RequestParam(name = "t") List<String> titulos) {
        return this.service.findByTitulos(titulos);
    }

    @GetMapping
    public List<Categoria> getCategoriasOrdenadasAsc() {
        return this.service.findAllOrderByTituloAsc();
    }

}
