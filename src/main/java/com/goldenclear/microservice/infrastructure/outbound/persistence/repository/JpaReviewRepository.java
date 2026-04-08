package com.goldenclear.microservice.infrastructure.outbound.persistence.repository;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity,Long> {
}
