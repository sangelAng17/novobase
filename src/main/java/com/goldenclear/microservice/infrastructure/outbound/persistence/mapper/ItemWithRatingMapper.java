package com.goldenclear.microservice.infrastructure.outbound.persistence.mapper;

import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ItemWithRatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemWithRatingMapper {


    Item toDomain(ItemWithRatingEntity entity);

    List<Item> toDomainList(List<ItemWithRatingEntity> entities);
}