package org.example.projeto5.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String modelo;
    private String cor;
    /*
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
     */
    @ManyToMany
    private List<Pessoa> pessoa;
}
