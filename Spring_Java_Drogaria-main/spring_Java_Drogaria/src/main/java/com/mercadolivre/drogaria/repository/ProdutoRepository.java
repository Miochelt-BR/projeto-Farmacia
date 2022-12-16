package com.mercadolivre.drogaria.repository;


import com.mercadolivre.drogaria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
    public List<Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
    public List<Produto> findByFabricanteEquals(String fabricante);

}
