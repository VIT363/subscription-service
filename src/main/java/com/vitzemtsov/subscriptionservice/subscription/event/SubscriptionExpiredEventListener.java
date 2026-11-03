
package com.vitzemtsov.subscriptionservice.subscription.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionExpiredEventListener {

    private final KafkaTemplate<@NonNull String,@NonNull String> kafkaTemplate;

     //  обработчик выполнится только после успешного завершения транзакции PostgreSQL.
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(SubscriptionExpiredEvent event) {
        kafkaTemplate.send("subscription-expired", event.login());

        log.info("Sent Kafka event after transaction commit for user '{}'", event.login());
    }
}
