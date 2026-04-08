package com.goldenclear.microservice.domain.port;

import com.goldenclear.microservice.domain.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findItemsWithAverageRatingLowerThan(Double rating);

    List<Item> findDistinctByReviewsCantidad(Long cantidad);

}
