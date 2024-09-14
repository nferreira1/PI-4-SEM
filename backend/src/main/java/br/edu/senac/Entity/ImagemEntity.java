package br.edu.senac.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "imagens")
@Entity(name = "ImagemEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
