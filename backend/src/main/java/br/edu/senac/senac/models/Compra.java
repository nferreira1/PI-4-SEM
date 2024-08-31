package br.edu.senac.senac.models;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "compras")
@Entity(name = "Compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")//essa compra vai ter obg um cliente id
    private Cliente cliente;
    private String nome;
}
