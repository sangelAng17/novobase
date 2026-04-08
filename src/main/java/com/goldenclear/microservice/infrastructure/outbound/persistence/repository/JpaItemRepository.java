package com.goldenclear.microservice.infrastructure.outbound.persistence.repository;

import com.goldenclear.microservice.infrastructure.outbound.persistence.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemRepository extends JpaRepository<ItemEntity,Long> {


}
