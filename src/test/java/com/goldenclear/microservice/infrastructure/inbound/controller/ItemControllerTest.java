package com.goldenclear.microservice.infrastructure.inbound.controller;

import com.goldenclear.microservice.application.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;


    @Test
    void deberiaRetornarTitulos_desdeEndpoint() throws Exception {

        Mockito.when(itemService.getTitles(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of("podcast", "musica"));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/getTitles/3.0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]").value("podcast"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1]").value("musica"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Listado de items"));

        Mockito.verify(itemService).getTitles(ArgumentMatchers.anyDouble());
    }

    @Test
    void deberiaRetornarListaVacia_cuandoNoHayResultados() throws Exception {

        Mockito.when(itemService.getTitles(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/items/getTitles/5.0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void deberiaRetornarBadRequest_siRatingEsInvalido() throws Exception {

        Mockito.when(itemService.getTitles(ArgumentMatchers.anyDouble()))
                .thenThrow(new IllegalArgumentException("Rating no puede ser igual o menor que 0"));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/getTitles/-1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deberiaRetornarItemsPorPromedio() throws Exception {

        Mockito.when(itemService.findItemsWithAverageRatingLowerThan(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of(
                        com.goldenclear.microservice.domain.model.Item.builder()
                                .id(1L)
                                .titulo("podcast")
                                .build()
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/by-average/3.0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].titulo").value("podcast"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Listado de items"));
    }


    @Test
    void deberiaRetornarListaVacia_byAverage() throws Exception {

        Mockito.when(itemService.findItemsWithAverageRatingLowerThan(ArgumentMatchers.anyDouble()))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/items/by-average/5.0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void deberiaRetornarItemsPorRating() throws Exception {

        Mockito.when(itemService.getTitles(ArgumentMatchers.anyLong()))
                .thenReturn(List.of("podcast", "musica"));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/by-rating/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]").value("podcast"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1]").value("musica"));
    }

    @Test
    void deberiaRetornarListaVacia_byRating() throws Exception {

        Mockito.when(itemService.getTitles(ArgumentMatchers.anyLong()))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/items/by-rating/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    void deberiaRetornarBadRequest_byAverage() throws Exception {

        Mockito.when(itemService.findItemsWithAverageRatingLowerThan(ArgumentMatchers.anyDouble()))
                .thenThrow(new IllegalArgumentException("Rating no puede ser igual o menor que 0"));

        mockMvc.perform(MockMvcRequestBuilders.get("/items/by-average/-1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}