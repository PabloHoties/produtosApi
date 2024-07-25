package br.com.cotiinformatica.domain.dtos;

import java.math.BigDecimal;

import br.com.cotiinformatica.domain.enums.Tipo;
import lombok.Data;

@Data
public class ProdutoDashboard {

	private Tipo tipo;
	private Integer quantidade;
	private BigDecimal precoUnitarioMedio;
	
	public ProdutoDashboard(Tipo tipo, Integer quantidade, BigDecimal precoUnitarioMedio) {
		super();
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.precoUnitarioMedio = precoUnitarioMedio;
	} 
	
	
}
