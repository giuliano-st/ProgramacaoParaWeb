package org.example.projeto5.model;


import jakarta.persistence.*;

@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int numero;
    private String tipo;
    @ManyToOne
    @JoinColumn (name = "id_estudante")
    private Estudante estudante;
}
