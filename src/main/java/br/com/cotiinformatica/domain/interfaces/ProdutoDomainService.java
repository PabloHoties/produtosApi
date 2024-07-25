package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.ProdutoResponseDto;

public interface ProdutoDomainService {

	ProdutoResponseDto insercao(ProdutoRequestDto request);
	ProdutoResponseDto alteracao(UUID id, ProdutoRequestDto request);
	ProdutoResponseDto delecao(UUID id);
	ProdutoResponseDto leituraPorId(UUID id);
	List<ProdutoResponseDto> leituraDeLista();
	
}
