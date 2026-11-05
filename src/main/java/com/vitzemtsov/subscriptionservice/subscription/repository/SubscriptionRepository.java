package com.vitzemtsov.subscriptionservice.subscription.repository;

import com.vitzemtsov.subscriptionservice.subscription.entity.Subscription;
import com.vitzemtsov.subscriptionservice.subscription.enums.SubscriptionType;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SubscriptionRepository
        extends JpaRepository<@NonNull Subscription,@NonNull String> {

    List<Subscription> findBySubscriptionTypeAndExpiresAtLessThanEqual(
            SubscriptionType type,
            Instant now
    );
}