package com.goldenclear.microservice.infrastructure.outbound.persistence.adapter;

import com.goldenclear.microservice.application.ItemService;
import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.domain.port.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class ItemRepositoryAdapterTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void shouldReturnTitles_whenRatingIsValid() {
        Long rating = 3L;

        List<Item> mockItems = List.of(
                Item.builder().id(1L).titulo("podcast").build(),
                Item.builder().id(2L).titulo("musica").build()
        );


        Mockito.when(itemRepository.findDistinctByReviewsCantidad(rating))
                .thenReturn(mockItems);

        List<String> result = itemService.getTitles(rating);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("podcast", result.get(0));
        Assertions.assertEquals("musica", result.get(1));

        Mockito.verify(itemRepository).findDistinctByReviewsCantidad(rating);
    }

    @Test
    void shouldReturnTitles_whenRatingIsDouble() {
        Double rating = 3.0;

        List<Item> mockItems = List.of(
                Item.builder().id(1L).titulo("podcast").build()
        );

        Mockito.when(itemRepository.findDistinctByReviewsCantidad(3L))
                .thenReturn(mockItems);

        List<String> result = itemService.getTitles(rating);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("podcast", result.get(0));
    }

    @Test
    void shouldThrowException_whenRatingIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles((Long) null)
        );

        assertEquals("el valor Rating no puede ser null", ex.getMessage());
    }

    @Test
    void shouldThrowException_whenRatingIsNegative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles(-1L)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void shouldThrowException_whenRatingIsNull_inFindItems() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.findItemsWithAverageRatingLowerThan(null)
        );

        assertEquals("el valor Rating no puede ser null", ex.getMessage());
    }

    @Test
    void shouldThrowException_whenRatingIsNegative_inFindItems() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.findItemsWithAverageRatingLowerThan(-1.0)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void shouldThrowException_whenRatingIsNegative_inDoubleMethod() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles(-1.0)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void shouldThrowException_whenRatingIsNegative_inDoubleMethod_1() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> itemService.getTitles(-1.0)
        );

        assertEquals("Rating no puede ser igual o menor que 0", ex.getMessage());
    }

    @Test
    void shouldReturnItems_whenRatingIsValid_inFindItems() {
        Double rating = 3.0;

        List<Item> mockItems = List.of(
                Item.builder().id(1L).titulo("podcast").build()
        );

        Mockito.when(itemRepository.findItemsWithAverageRatingLowerThan(rating))
                .thenReturn(mockItems);

        List<Item> result = itemService.findItemsWithAverageRatingLowerThan(rating);

        assertEquals(1, result.size());
        Mockito.verify(itemRepository).findItemsWithAverageRatingLowerThan(rating);
    }
}