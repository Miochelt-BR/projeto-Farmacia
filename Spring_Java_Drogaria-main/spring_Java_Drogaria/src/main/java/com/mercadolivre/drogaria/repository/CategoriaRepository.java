package com.mercadolivre.drogaria.repository;


import com.mercadolivre.drogaria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
}
