package br.com.cotiinformatica.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Produto;
import br.com.cotiinformatica.domain.enums.Tipo;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	@Query("SELECT p FROM Produto p WHERE p.tipo = :tipo")
	List<Produto> findProdutoByTipo(@Param("tipo") Tipo tipo);
}
