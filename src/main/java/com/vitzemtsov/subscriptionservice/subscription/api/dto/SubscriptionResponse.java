package com.vitzemtsov.subscriptionservice.subscription.api.dto;

import com.vitzemtsov.subscriptionservice.subscription.entity.Subscription;
import com.vitzemtsov.subscriptionservice.subscription.enums.SubscriptionType;

import java.time.Instant;

public record SubscriptionResponse(
        String login,
        SubscriptionType subscriptionType,
        Instant expiresAt,
        boolean activePaidSubscription) {

    public static SubscriptionResponse from(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getLogin(),
                subscription.getSubscriptionType(),
                subscription.getExpiresAt(),
                subscription.hasActivePaidSubscription()
        );
    }
}