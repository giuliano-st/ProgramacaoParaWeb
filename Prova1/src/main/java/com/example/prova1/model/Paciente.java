package com.example.prova1.model;

import jakarta.persistence.*;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String cpf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Paciente() {
    }

    public Paciente(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Paciente(long id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }
}
