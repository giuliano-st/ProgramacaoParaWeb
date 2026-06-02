package org.example.projeto5.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

}
