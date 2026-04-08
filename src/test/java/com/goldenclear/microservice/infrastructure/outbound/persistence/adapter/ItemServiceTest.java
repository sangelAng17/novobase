package com.goldenclear.microservice.infrastructure.outbound.persistence.adapter;

import com.goldenclear.microservice.application.ItemService;
import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.domain.port.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void deberiaRetornarTitulos_cuandoRatingEsValido() {
        Long rating = 3L;

        List<Item> itemsMock = List.of(
                Item.builder().id(1L).titulo("podcast").build(),
                Item.builder().id(2L).titulo("musica").build()
        );

        Mockito.when(itemRepository.findDistinctByReviewsCantidad(rating))
                .thenReturn(itemsMock);

        List<String> resultado = itemService.getTitles(rating);

        assertEquals(2, resultado.size());
        assertIterableEquals(List.of("podcast", "musica"), resultado);

        Mockito.verify(itemRepository, Mockito.times(1))
                .findDistinctByReviewsCantidad(rating);
    }

    @Test
    void deberiaRetornarListaVacia_cuandoNoHayResultados() {
        Long rating = 5L;

        Mockito.when(itemRepository.findDistinctByReviewsCantidad(rating))
                .thenReturn(List.of());

        List<String> resultado = itemService.getTitles(rating);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deberiaConvertirDoubleALong_correctamente() {
        Double rating = 3.0;

        Mockito.when(itemRepository.findDistinctByReviewsCantidad(3L))
                .thenReturn(List.of(Item.builder().titulo("podcast").build()));

        List<String> resultado = itemService.getTitles(rating);

        assertEquals(1, resultado.size());
        assertEquals("podcast", resultado.get(0));
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles((Long) null)
        );

        assertEquals("el valor Rating no puede ser null", ex.getMessage());
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNegativo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles(-1L)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void deberiaRetornarItems_cuandoRatingEsValido() {
        Double rating = 3.0;

        List<Item> itemsMock = List.of(
                Item.builder().id(1L).titulo("podcast").build()
        );

        Mockito.when(itemRepository.findItemsWithAverageRatingLowerThan(rating))
                .thenReturn(itemsMock);

        List<Item> resultado = itemService.findItemsWithAverageRatingLowerThan(rating);

        assertEquals(1, resultado.size());
        Mockito.verify(itemRepository)
                .findItemsWithAverageRatingLowerThan(rating);
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNegativo_enFindItems() {

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.findItemsWithAverageRatingLowerThan(-1.0)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNull_enFindItems() {

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.findItemsWithAverageRatingLowerThan(null)
        );

        assertEquals("el valor Rating no puede ser null", ex.getMessage());
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNull_enGetTitlesDouble() {

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles((Double) null)
        );

        assertEquals("el valor Rating no puede ser null", ex.getMessage());
    }

    @Test
    void deberiaLanzarExcepcion_siRatingEsNegativo_enGetTitlesDouble() {

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles(-1.0)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }
}