package br.com.cotiinformatica.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.domain.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.domain.interfaces.ProdutoDomainService;

@Service
public class ProdutoDomainServiceImpl implements ProdutoDomainService {

	@Override
	public ProdutoResponseDto insercao(ProdutoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoResponseDto alteracao(UUID id, ProdutoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoResponseDto delecao(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoResponseDto leituraPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoResponseDto> leituraDeLista() {
		// TODO Auto-generated method stub
		return null;
	}

}
