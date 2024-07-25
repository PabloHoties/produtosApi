package br.com.cotiinformatica.domain.dtos;

import java.math.BigDecimal;

import br.com.cotiinformatica.domain.enums.Tipo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDto {

	@Size(min = 8, max = 150, message = "Informe no mínimo 8 e no máximo 150 caracteres.")
	@NotEmpty(message = "Informe o nome do produto.")
	private String nome;
	
	@NotNull(message = "Informe o tipo do produto.")
	private Tipo tipo;

	@DecimalMin(value = "0.00", message = "Informe um preço maior do zero.")
	@NotNull(message = "Informe o preço do produto.")
	private BigDecimal precoUnitario;
}
