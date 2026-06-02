package com.example.projeto7.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Telefone() {
    }

    public Telefone(Usuario usuario, String numero) {
        this.usuario = usuario;
        this.numero = numero;
    }

    public Telefone(int id, String numero, Usuario usuario) {
        this.id = id;
        this.numero = numero;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
