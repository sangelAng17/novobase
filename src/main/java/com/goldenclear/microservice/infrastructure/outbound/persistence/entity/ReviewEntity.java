package com.goldenclear.microservice.infrastructure.outbound.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "review",schema = "public")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idreview")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iditem_fk")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "iduser_fk")
    private UserEntity userEntity;

    private Long cantidad;

    private Timestamp ultimoreview;

}
