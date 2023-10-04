package com.mercadolivre.conversor.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta")
@Builder
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moedaOrigem;
    private String moedaDestino;
    private Double valorOriginal;
    private Double valorConvertido;

}
