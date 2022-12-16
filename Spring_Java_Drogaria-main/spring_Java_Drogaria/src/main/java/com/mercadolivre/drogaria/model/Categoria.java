package com.mercadolivre.drogaria.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;



@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nao pode ser nulo.")
    private String name;


    @OneToMany(mappedBy = "categorias", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categorias")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public void setProduto(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
