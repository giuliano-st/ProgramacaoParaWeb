package com.example.projeto10.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<itemPedido> itensPedido;

    private Double valorTotal;
    private String status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
}
