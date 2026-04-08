package com.goldenclear.microservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Review {

    private Long id;
    private Item item;
    private User user;
    private Long cantidad;
    private Timestamp ultimoreview;

}
