package br.com.cotiinformatica.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.domain.entities.Produto;
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

}
