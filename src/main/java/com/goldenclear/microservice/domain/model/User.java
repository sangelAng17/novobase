package com.goldenclear.microservice.domain.model;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.AccountUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Long id;
    private AccountUser accountUser;
    private String nombre;
    private String apellido;
    private Long documento;


}
