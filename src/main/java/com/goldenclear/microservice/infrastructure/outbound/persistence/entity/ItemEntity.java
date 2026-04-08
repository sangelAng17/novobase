package com.goldenclear.microservice.infrastructure.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "item",schema = "public")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iditem")
    private Long id;

    private String titulo;

    private String descripcion;

    @OneToMany(mappedBy = "itemEntity")
    private List<ReviewEntity> reviews;

}
