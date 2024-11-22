package br.edu.senac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "descontos")
@Entity(name = "Desconto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescontoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Positive(message = "O valor deve ser positivo.")
  @DecimalMin(value = "1.00", message = "O valor deve ser no mínimo R$ 1,00 ou 1,00%.")
  private double valor;
}
