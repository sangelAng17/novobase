package com.goldenclear.microservice.infrastructure.outbound.persistence.mapper;

import com.goldenclear.microservice.domain.model.User;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User localidad);

    List<User> toDomainList(List<UserEntity> entities);

    List<UserEntity> toEntityList(List<User> localidades);

}
