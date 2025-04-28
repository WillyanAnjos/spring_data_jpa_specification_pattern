package com.willyan.spring_jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willyan.spring_jpa.entity.Endereco;
import com.willyan.spring_jpa.repository.EnderecoRepository;
import com.willyan.spring_jpa.specification.EnderecoSpec;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Transactional
	public Endereco save(Endereco endereco) {
		return repository.save(endereco);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findUfAndCidade(String uf, String cidade) {
		Specification<Endereco> spec = Specification.where(EnderecoSpec.likeUf(uf))
				.and(EnderecoSpec.likeCidade(cidade));

		return repository.findAll(spec);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findByUfAndLogradouro(String uf, String logradouro) {
		Specification<Endereco> spec = Specification.where(EnderecoSpec.likeUf(uf))
				.and(EnderecoSpec.likeLogradouro(logradouro));

		return repository.findAll(spec);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findByUfAndCidades(String uf, List<String> cidades) {
		Specification<Endereco> spec = Specification.where(EnderecoSpec.likeUf(uf))
				.and(EnderecoSpec.inCidades(cidades));

		return repository.findAll(spec);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findByAutorNomeOrSobrenome(String nome, String sobrenome) {
		Specification<Endereco> spec = Specification
				.where(EnderecoSpec.likeAutorNome(nome).or(EnderecoSpec.likeAutorSobrenome(sobrenome)));

		return repository.findAll(spec);
	}

	@Transactional(readOnly = true)
	public Endereco findByAutorNomeAndSobrenome(String nome, String sobrenome) {
		return repository.findOne(EnderecoSpec.likeAutorNomeAndSobrenome(nome, sobrenome)).orElseThrow(
				() -> new RuntimeException("Nenhum resultado encontrado com o nome e sobrenome informados"));
	}
	
	@Transactional(readOnly = true)
	public List<Endereco> findByAutorTotalDePostsAndCidades(long total, List<String> cidades) {
		Specification<Endereco> spec = Specification.where(
				EnderecoSpec.byGreaterThanEqualToPosts(total).and(EnderecoSpec.inCidades(cidades)));
		return repository.findAll(spec);
	}
}
