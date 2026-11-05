package com.vitzemtsov.subscriptionservice.subscription.service;

import com.vitzemtsov.subscriptionservice.subscription.entity.Subscription;
import com.vitzemtsov.subscriptionservice.subscription.repository.SubscriptionRepository;
import com.vitzemtsov.subscriptionservice.subscription.exception.SubscriptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    @Transactional(readOnly = true)
    public Subscription getByLogin(String login) {
        return subscriptionRepository.findById(login)
                .orElseThrow(() -> new SubscriptionNotFoundException(login));
    }
}