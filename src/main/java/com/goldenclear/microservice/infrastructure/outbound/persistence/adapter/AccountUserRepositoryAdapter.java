package com.goldenclear.microservice.infrastructure.outbound.persistence.adapter;

import com.goldenclear.microservice.domain.port.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountUserRepositoryAdapter implements AccountUserRepository {
}
