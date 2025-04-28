package com.willyan.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.willyan.spring_jpa.entity.Post;
import com.willyan.spring_jpa.service.PostService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService service;

    @PostMapping
    public Post salvar(@RequestBody Post post) {
        return this.service.save(post);
    }

    @GetMapping("categoria/{categoria}/autor/{autorId}")
    public List<Post> getByCategoriaAndAutor(@PathVariable String categoria, @PathVariable Long autorId) {
        return this.service.findAllByCategoriaAndAutorId(categoria, autorId);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public List<Post> getByAutor(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.service.findAllByAutor(nome, sobrenome);
    }

    @GetMapping("titulo/{titulo}")
    public List<Post> getByTitulo(@PathVariable String titulo) {
        return this.service.findAllByTitulo(titulo);
    }

    @GetMapping("data-publicacao/{data}")
    public List<Post> getByDataPublicacao(@PathVariable LocalDate data) {
        return this.service.findAllByDataPublicacaoMaiorOuIgual(data);
    }

    @GetMapping("sem-data-publicacao")
    public List<Post> getBySemDataPublicacao() {
        return this.service.findAllBySemDataPublicacao();
    }

    @GetMapping("all")
    public Page<Post> getAllPagination(@PageableDefault(page = 0, size = 10, sort = "dataPublicacao",
                                                        direction = Sort.Direction.DESC) Pageable pageable) {
        return this.service.findAllPagination(pageable);
    }

    @GetMapping("ano/{ano}")
    public Page<Post> getAllPagination(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "dataPublicacao") String sort,
                                       @RequestParam(defaultValue = "desc") String dir,
                                       @PathVariable int ano) {
        return this.service.findAllByAno(ano, page, size, sort, dir);
    }

}
