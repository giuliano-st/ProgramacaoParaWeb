package org.example.projeto5.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "discentes")
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEstudante")
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Transient
    private int idade;
    @Embedded
    private Endereco endereco;
    @OneToMany (mappedBy = "estudante")
    private List<Telefone> telefones;
    @ManyToMany
    @JoinTable (
            name = "estudante_disciplina",
            joinColumns = @JoinColumn(name = "estudante_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;
}