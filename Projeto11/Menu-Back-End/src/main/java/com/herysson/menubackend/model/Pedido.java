package com.herysson.menubackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente clienteId;

    @OneToMany(mappedBy = "pedidoId", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    private Double valorTotal;
    private String status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
}
