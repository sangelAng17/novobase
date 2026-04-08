package com.goldenclear.microservice.infrastructure.outbound.persistence.adapter;

import com.goldenclear.microservice.domain.port.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewRepositoryAdapter implements ReviewRepository {
}
