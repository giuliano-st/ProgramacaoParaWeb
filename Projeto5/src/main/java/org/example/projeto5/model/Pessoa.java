package org.example.projeto5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Pessoa {
    @Id
    private int id;
    private String nome;
    private String idade;
    @OneToMany
    private List<Veiculo> veiculos;
}
