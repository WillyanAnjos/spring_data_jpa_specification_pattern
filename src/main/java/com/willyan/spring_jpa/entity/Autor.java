package com.willyan.spring_jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AUTORES")
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private Long id;

    @Column(name = "nome", length = 45, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 45, nullable = false)
    private String sobrenome;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "id_info")
    private InfoAutor infoAutor;
    
    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private List<Post> posts;
    
    
    

    public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public InfoAutor getInfoAutor() {
        return infoAutor;
    }

    public void setInfoAutor(InfoAutor infoAutor) {
        this.infoAutor = infoAutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                '}';
    }
}
