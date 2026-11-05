package com.vitzemtsov.subscriptionservice.subscription.scheduler;

import com.vitzemtsov.subscriptionservice.subscription.service.SubscriptionExpirationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionExpirationScheduler {

    private final SubscriptionExpirationService expirationService;

    @Scheduled(
            fixedDelay = 60_000,
            initialDelay = 10_000
    )
    @SchedulerLock(
            name = "checkExpiredSubscriptions",
            lockAtMostFor = "PT50S",
            lockAtLeastFor = "PT5S"
    )
    public void checkExpiredSubscriptions() {
        log.info("Starting expired subscriptions check");

        int processed = expirationService.downgradeExpiredSubscriptions();

        log.info("Expired subscriptions check completed. Processed: {}", processed);
    }
}