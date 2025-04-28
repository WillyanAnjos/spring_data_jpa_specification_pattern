package com.willyan.spring_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.entity.Categoria;
import com.willyan.spring_jpa.entity.Post;
import com.willyan.spring_jpa.repository.PostRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AutorService autorService;
    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    public Post save(Post post) {
        Autor autor = this.autorService.findById(post.getAutor().getId());
        post.setAutor(autor);

        List<String> titulos = post.getCategorias().stream().map(Categoria::getTitulo).toList();
        List<Categoria> categorias = this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);

        return this.postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria, Long autorId) {
        return this.postRepository.findByCategoriasTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByAutor(String autorNome, String autorSobrenome) {
        return this.postRepository.findByAutorNomeOrAutorSobrenome(autorNome, autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByTitulo(String titulo) {
        return this.postRepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByDataPublicacaoMaiorOuIgual(LocalDate data) {
        return this.postRepository.findByDataPublicacaoIsGreaterThanEqual(data);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao() {
        return this.postRepository.findByDataPublicacaoIsNull();
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPagination(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByAno(int ano, int page, int size, String sort, String dir) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(dir), sort);
        return this.postRepository.findByAno(ano, pageable);
    }

}
