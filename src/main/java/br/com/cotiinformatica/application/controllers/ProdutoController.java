package br.com.cotiinformatica.application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.ProdutoDashboard;
import br.com.cotiinformatica.domain.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.domain.interfaces.ProdutoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDomainService produtoDomainService;
	
	@PostMapping
	public ResponseEntity<ProdutoResponseDto> insercao(@RequestBody @Valid ProdutoRequestDto request) throws Exception {
		return ResponseEntity.status(201).body(produtoDomainService.insercao(request));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> alteracao(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDto request) throws Exception {
		return ResponseEntity.status(200).body(produtoDomainService.alteracao(id, request));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> delecao(@PathVariable UUID id) throws Exception {
		return ResponseEntity.status(200).body(produtoDomainService.delecao(id));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoResponseDto> leituraPorId(@PathVariable UUID id) throws Exception {
		return ResponseEntity.status(200).body(produtoDomainService.leituraPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDto>> leituraDeLista() throws Exception {
		return ResponseEntity.status(200).body(produtoDomainService.leituraDeLista());
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<List<ProdutoDashboard>> dashboard() throws Exception {
		List<ProdutoDashboard> response = produtoDomainService.dashboard();
		return ResponseEntity.status(200).body(response);
		
		//return ResponseEntity.status(200).body(produtoDomainService.dashboard());
	}
}
