package com.vitzemtsov.subscriptionservice.subscription.api;

import com.vitzemtsov.subscriptionservice.subscription.entity.Subscription;
import com.vitzemtsov.subscriptionservice.subscription.api.constants.SubscriptionApiPaths;
import com.vitzemtsov.subscriptionservice.subscription.api.dto.SubscriptionResponse;
import com.vitzemtsov.subscriptionservice.subscription.service.SubscriptionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SubscriptionApiPaths.BASE)
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionQueryService subscriptionQueryService;

    @GetMapping(SubscriptionApiPaths.BY_LOGIN)
    public SubscriptionResponse getByLogin(@PathVariable String login) {
        Subscription subscription = subscriptionQueryService.getByLogin(login);

        return SubscriptionResponse.from(subscription);
    }
}