package com.vitzemtsov.subscriptionservice.subscription.service;

import org.springframework.context.ApplicationEventPublisher;
import com.vitzemtsov.subscriptionservice.subscription.event.SubscriptionExpiredEvent;
import com.vitzemtsov.subscriptionservice.subscription.entity.Subscription;
import com.vitzemtsov.subscriptionservice.subscription.enums.SubscriptionType;
import com.vitzemtsov.subscriptionservice.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionExpirationService {

    private final SubscriptionRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public int downgradeExpiredSubscriptions() {
        Instant now = Instant.now();
        List<Subscription> expired = repository.findBySubscriptionTypeAndExpiresAtLessThanEqual(SubscriptionType.PAID, now);

        if (expired.isEmpty()) {
            return 0;
        }

        for (Subscription sub : expired) {
            sub.downgradeToFree();
            repository.save(sub);

            eventPublisher.publishEvent(
                    new SubscriptionExpiredEvent(sub.getLogin()));
        }

        return expired.size();
    }
}