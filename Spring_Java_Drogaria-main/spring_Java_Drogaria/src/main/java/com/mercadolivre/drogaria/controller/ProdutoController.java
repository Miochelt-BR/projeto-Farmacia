package com.mercadolivre.drogaria.controller;


import com.mercadolivre.drogaria.model.Produto;
import com.mercadolivre.drogaria.repository.CategoriaRepository;
import com.mercadolivre.drogaria.repository.ProdutoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;



    //CADASTRAR

    @PostMapping

    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto){

        return categoriaRepository.findById(produto.getCategoria().getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
                .orElse(ResponseEntity.badRequest().build());

    }




    //LISTAR
    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProduto(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }
    //LISTAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // LISTAR POR NOME
    @GetMapping("/name/{name}")
     public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String name){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(name));
    }



    //UPDATE (ALTERAR)
    @PutMapping
    public ResponseEntity<Produto> alterarProduto(@Valid @RequestBody Produto produto){
        if(produtoRepository.existsById(produto.getId())){
            return categoriaRepository.findById(produto.getCategoria().getId())
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
                    .orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.notFound().build();
    }



    //DELETE (EXCLUIR)
    @DeleteMapping(" /{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long id){

        return produtoRepository.findById(id)
                .map(r -> { produtoRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); })
                .orElse(ResponseEntity.notFound().build());


    }

}

