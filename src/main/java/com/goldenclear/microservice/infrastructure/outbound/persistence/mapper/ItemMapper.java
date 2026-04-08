package com.goldenclear.microservice.infrastructure.outbound.persistence.mapper;

import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toDomain(ItemEntity entity);

    ItemEntity toEntity(Item localidad);

    List<Item> toDomainList(List<ItemEntity> entities);

    List<ItemEntity> toEntityList(List<Item> localidades);


}
