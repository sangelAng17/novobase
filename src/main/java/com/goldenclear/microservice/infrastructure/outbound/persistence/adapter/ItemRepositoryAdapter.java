package com.goldenclear.microservice.infrastructure.outbound.persistence.adapter;

import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.domain.port.ItemRepository;
import com.goldenclear.microservice.infrastructure.outbound.persistence.mapper.ItemMapper;
import com.goldenclear.microservice.infrastructure.outbound.persistence.mapper.ItemWithRatingMapper;
import com.goldenclear.microservice.infrastructure.outbound.persistence.repository.JpaItemWithRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemRepositoryAdapter  implements ItemRepository {

    private final JpaItemWithRatingRepository jpaItemWithRatingRepository;
    private final ItemWithRatingMapper itemWithRatingMapper;

    @Override
    public List<Item> findItemsWithAverageRatingLowerThan(Double rating) {
            return itemWithRatingMapper.toDomainList(
                    jpaItemWithRatingRepository.findByRatingLessThanEqual(rating));
    }

    @Override
    public List<Item> findDistinctByReviewsCantidad(Long cantidad) {
        return jpaItemWithRatingRepository.findByRating(Double.valueOf(cantidad))
                .stream()
                .map(itemWithRatingMapper::toDomain)
                .toList();
    }

}
