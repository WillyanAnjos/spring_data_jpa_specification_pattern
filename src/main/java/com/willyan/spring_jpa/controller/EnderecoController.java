package com.willyan.spring_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.willyan.spring_jpa.entity.Endereco;
import com.willyan.spring_jpa.service.EnderecoService;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	public Endereco salvar(@RequestBody Endereco endereco) {
		return enderecoService.save(endereco);
	}
	
	@GetMapping("/uf/{uf}/cidade/{cidade}")
	public List<Endereco> buscarPorUfECidade(@PathVariable String uf, @PathVariable String cidade) {
		return enderecoService.findUfAndCidade(uf, cidade);
	}
	
	@GetMapping("/uf/{uf}/logradouro/{logradouro}")
	public List<Endereco> buscarPorUfELogradouro(@PathVariable String uf, @PathVariable String logradouro) {
		return enderecoService.findUfAndCidade(uf, logradouro);
	}
	
	@GetMapping("/uf/{uf}/cidades/")
	public List<Endereco> buscarPorUfECidades(@PathVariable String uf, @RequestParam List<String> cidades) {
		return enderecoService.findByUfAndCidades(uf, cidades);
	}
	
	@GetMapping("/autores/nome/{nome}/sobrenome/{sobrenome}")
	public List<Endereco> buscarPorAutoresNomeOrSobrenome(@PathVariable String nome, @PathVariable String sobrenome) {
		return enderecoService.findByAutorNomeOrSobrenome(nome, sobrenome);
	}
	
	@GetMapping("/autor/nome/{nome}/sobrenome/{sobrenome}")
	public Endereco buscarPorAutoreNomeAndSobrenome(@PathVariable String nome, @PathVariable String sobrenome) {
		return enderecoService.findByAutorNomeAndSobrenome(nome, sobrenome);
	}
	
	@GetMapping("/autores/total-posts")
	public List<Endereco> buscarPorAutoresTotalDePostsPorCidades(@RequestParam long total, @RequestParam List<String> cidades) {
		return enderecoService.findByAutorTotalDePostsAndCidades(total, cidades);
	}
}
