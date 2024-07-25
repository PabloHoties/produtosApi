package br.com.cotiinformatica.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.cotiinformatica.domain.enums.Tipo;
import lombok.Data;

@Data
public class ProdutoResponseDto {

	private UUID id;
	private String nome;
	private Tipo tipo;
	private BigDecimal precoUnitario;
}
