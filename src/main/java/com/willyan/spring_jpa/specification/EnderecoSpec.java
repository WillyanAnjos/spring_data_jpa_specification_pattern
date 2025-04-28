package com.willyan.spring_jpa.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.entity.Endereco;
import com.willyan.spring_jpa.entity.Post;

import jakarta.persistence.criteria.Join;

public class EnderecoSpec {

	public static Specification<Endereco> likeUf(String uf) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("uf"), uf));
	}

	public static Specification<Endereco> likeCidade(String cidade) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("cidade"), cidade));
	}

	public static Specification<Endereco> likeLogradouro(String logradouro) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("logradouro"), "%" + logradouro + "%"));
	}

	public static Specification<Endereco> inCidades(List<String> cidades) {
		return ((root, query, criteriaBuilder) -> root.get("cidade").in(cidades));
	}

	public static Specification<Endereco> likeAutorNome(String nome) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("autor").get("nome"), nome));
	}

	public static Specification<Endereco> likeAutorSobrenome(String sobrenome) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("autor").get("sobrenome"), sobrenome));
	}

	public static Specification<Endereco> likeAutorNomeAndSobrenome(String nome, String sobrenome) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
				criteriaBuilder.like(root.get("autor").get("nome"), nome),
				criteriaBuilder.like(root.get("autor").get("sobrenome"), sobrenome)));
	}
	
	public static Specification<Endereco> byGreaterThanEqualToPosts(long total) {
		return (root, query, criteriaBuilder) -> {
			
			// Faz join entre endereco e autor
			Join<Endereco,Autor> autor = root.join("autor"); 
			
			//Faz join entre autor e post
			Join<Autor, Post> post = autor.join("posts");
			
			// agrupa os posts por autor
			query.groupBy(post.get("autor"));
			
			// Conta o numero de posts por autor e aplica o having
			query.having(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.count(post.get("id")), total));
			
			return query.getRestriction();
		};
	}
}
