package com.goldenclear.microservice.infrastructure.outbound.persistence.mapper;

import com.goldenclear.microservice.domain.model.Review;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ReviewEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


    Review toDomain(ReviewEntity entity);

    ReviewEntity toEntity(Review localidad);

    List<Review> toDomainList(List<ReviewEntity> entities);

    List<ReviewEntity> toEntityList(List<Review> localidades);

}
