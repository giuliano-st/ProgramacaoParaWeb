package com.example.minhaapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Telefone> telefones = new ArrayList<>();

    //Utilizado para garantir a consistenciade de dados entre os dois lados do relacionamento (Dica do GPT)
    public void addTelefone(Telefone telefone) {
        telefone.setUsuario(this);
        if (this.telefones == null) {
            this.telefones = new java.util.ArrayList<>();
        }
        this.telefones.add(telefone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, List<Telefone> telefones) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
    }

    public Usuario(Long id, String nome, String email, String senha, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefones=" + telefones +
                '}';
    }
}
