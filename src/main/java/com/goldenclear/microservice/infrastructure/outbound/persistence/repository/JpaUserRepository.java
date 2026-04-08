package com.goldenclear.microservice.infrastructure.outbound.persistence.repository;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
}
