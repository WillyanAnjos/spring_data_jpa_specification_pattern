package com.willyan.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.entity.InfoAutor;
import com.willyan.spring_jpa.projection.AutorInfoProjection;
import com.willyan.spring_jpa.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        service.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        service.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        service.delete(id);
        return "Autor id = " + id + " foi excluído com sucesso";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Autor> getAll() {
        return service.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAutoresByNomeOrSobrenome(@RequestParam String termo) {
        return service.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalDeAutores() {
        return service.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return service.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getByCargo(@RequestParam String cargo) {
        return service.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection salvarInfoAutor(@RequestParam Long id) {
        return service.findAutorInfoById(id);
    }


}
