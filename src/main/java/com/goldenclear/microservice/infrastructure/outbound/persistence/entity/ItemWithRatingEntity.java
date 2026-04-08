package com.goldenclear.microservice.infrastructure.outbound.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "item_with_rating")
public class ItemWithRatingEntity {

    @Id
    @Column(name = "iditem")
    private Long id;

    private String titulo;
    private String descripcion;

    private Double rating;
}
