package com.mercadolivre.drogaria.controller;

import com.mercadolivre.drogaria.model.Categoria;
import com.mercadolivre.drogaria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {



    @Autowired
    private CategoriaRepository categoriaRepository;



    //CADASTRAR
    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    //READ(LISTAR)
    @GetMapping
    private ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }


    @GetMapping("/{id}")
    private ResponseEntity<Categoria> getById(@PathVariable Long id){

        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Categoria>> getByTipo(@PathVariable String name){
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(name));

    }

    //UPDATE (ATUALZIAR OU ALTERAR)
    @PutMapping
    public ResponseEntity<Categoria> alterarCategoria(@Valid @RequestBody Categoria categoria){
        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.ok().body(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETE (DELETAR)
    @DeleteMapping
    private ResponseEntity<?> excluirCategoria(@PathVariable Long id){
        return categoriaRepository.findById(id)
                .map(resposta -> {categoriaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
