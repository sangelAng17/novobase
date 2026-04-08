package com.goldenclear.microservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemWithRating {


    private Long id;
    private String titulo;
    private String descripcion;

    private Double rating;
}
