package com.goldenclear.microservice.infrastructure.outbound.persistence.repository;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ItemEntity;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ItemWithRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemWithRatingRepository extends JpaRepository<ItemWithRatingEntity,Long> {

    List<ItemWithRatingEntity> findByRatingLessThanEqual(Double rating);
    List<ItemWithRatingEntity> findByRating(Double rating);

}
