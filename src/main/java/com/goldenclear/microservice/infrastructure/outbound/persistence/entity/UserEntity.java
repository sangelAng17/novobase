package com.goldenclear.microservice.infrastructure.outbound.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "user",schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iduser")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private AccountUserEntity accountUserEntity;
    private String nombre;
    private String apellido;
    private Long documento;



}
