package br.com.cotiinformatica.domain.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ProdutoDashboard;
import br.com.cotiinformatica.domain.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.domain.entities.Produto;
import br.com.cotiinformatica.domain.enums.Tipo;
import br.com.cotiinformatica.domain.interfaces.ProdutoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.ProdutoRepository;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProdutoResponseDto insercao(ProdutoRequestDto request) {
		
		Produto produto = modelMapper.map(request, Produto.class);
		produto.setId(UUID.randomUUID());
		
		produtoRepository.save(produto);
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public ProdutoResponseDto alteracao(UUID id, ProdutoRequestDto request) {
		
		Produto produto = modelMapper.map(request, Produto.class);
		produto.setId(id);
		
		if (produtoRepository.findById(id).isEmpty())
			throw new IllegalArgumentException("Não existe um produto com o ID informado.");
		
		produtoRepository.save(produto);
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public ProdutoResponseDto delecao(UUID id) {
		
		Optional<Produto> produto = produtoRepository.findById(id); 

		if (produto.isEmpty())
			throw new IllegalArgumentException("Não existe um produto com o ID informado.");
		
		produtoRepository.delete(produto.get());
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public ProdutoResponseDto leituraPorId(UUID id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty())
			throw new IllegalArgumentException("Não existe um produto com o ID informado.");
		
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@Override
	public List<ProdutoResponseDto> leituraDeLista() {
		
		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(produto -> modelMapper.map(produto, ProdutoResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ProdutoDashboard> dashboard() {
		
		Integer quantidadeMaterial = 0;
		BigDecimal precoUnitarioMedioMaterial = BigDecimal.ZERO;
		
		List<ProdutoDashboard> produtosDashboard = new ArrayList<>();
		
		List<Produto> produtosMaterial = produtoRepository.findProdutoByTipo(Tipo.MATERIAL);
		
		for (Produto produto : produtosMaterial) {
			quantidadeMaterial += 1;
			precoUnitarioMedioMaterial = precoUnitarioMedioMaterial.add(produto.getPrecoUnitario());
		}
		
		precoUnitarioMedioMaterial = precoUnitarioMedioMaterial.divide(BigDecimal.valueOf(quantidadeMaterial));
		produtosDashboard.add(new ProdutoDashboard(Tipo.MATERIAL, quantidadeMaterial, precoUnitarioMedioMaterial));
		
		Integer quantidadeServico = 0;
		BigDecimal precoUnitarioMedioServico = BigDecimal.ZERO;
		
		List<Produto> produtosServicos = produtoRepository.findProdutoByTipo(Tipo.SERVIÇO);
		
		for (Produto produto : produtosServicos) {
			quantidadeServico += 1;
			precoUnitarioMedioServico = precoUnitarioMedioServico.add(produto.getPrecoUnitario());
		}
		
		precoUnitarioMedioServico = precoUnitarioMedioServico.divide(BigDecimal.valueOf(quantidadeServico));
		produtosDashboard.add(new ProdutoDashboard(Tipo.SERVIÇO, quantidadeServico, precoUnitarioMedioServico));
		
		return produtosDashboard;
	}

}
