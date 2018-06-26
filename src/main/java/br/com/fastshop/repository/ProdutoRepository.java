package br.com.fastshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fastshop.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	  @Query("select p from Produto p where p.id = ?1")
	  Produto findProdutoById(Long id);

}
