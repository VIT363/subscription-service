package com.vitzemtsov.subscriptionservice.subscription.entity;

import com.vitzemtsov.subscriptionservice.subscription.enums.SubscriptionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type", nullable = false, length = 16)
    private SubscriptionType subscriptionType = SubscriptionType.FREE;

    @Column(name = "expires_at")
    private Instant expiresAt;

    protected Subscription() {}

    public boolean hasActivePaidSubscription() {
        return subscriptionType == SubscriptionType.PAID
                && expiresAt != null
                && expiresAt.isAfter(Instant.now());
    }

    public void downgradeToFree() {
        this.subscriptionType = SubscriptionType.FREE;
        this.expiresAt = null;
    }
}