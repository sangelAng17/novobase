package com.goldenclear.microservice.infrastructure.outbound.persistence.mapper;

import com.goldenclear.microservice.domain.model.AccountUser;
import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.AccountUserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUserMapper {


    AccountUser toDomain(AccountUserEntity entity);

    AccountUserEntity toEntity(AccountUser localidad);

    List<AccountUser> toDomainList(List<AccountUserEntity> entities);

    List<AccountUserEntity> toEntityList(List<AccountUser> localidades);

}
