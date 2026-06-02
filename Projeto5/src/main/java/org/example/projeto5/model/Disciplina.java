package org.example.projeto5.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    @ManyToMany (mappedBy = "disciplinas") //Neste caso é necessario uma tabela adicional para os dois n
    private List<Estudante> estudantes;
}
