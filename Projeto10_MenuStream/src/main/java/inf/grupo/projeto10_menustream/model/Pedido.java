package inf.grupo.projeto10_menustream.model;

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
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
    private boolean ativo;
}
