package com.willyan.spring_jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "POSTS")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Long id;

    @Column(name = "titulo", length = 65, nullable = false, unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_tem_categorias",
        joinColumns = @JoinColumn(name = "id_post"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                '}';
    }
}
