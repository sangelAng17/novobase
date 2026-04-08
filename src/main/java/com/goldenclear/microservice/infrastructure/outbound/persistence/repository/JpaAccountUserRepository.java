package com.goldenclear.microservice.infrastructure.outbound.persistence.repository;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountUserRepository extends JpaRepository<AccountUserEntity,Long> {
}
