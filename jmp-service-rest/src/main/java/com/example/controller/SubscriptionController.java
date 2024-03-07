package com.example.controller;

import com.example.dto.*;
import example.service.impl.SubscriptionServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RestController
public class SubscriptionController {
    private final SubscriptionServiceImpl subscriptionService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public SubscriptionController(SubscriptionServiceImpl subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto requestDto) {
        logger.log(Level.INFO, "Request - " + requestDto + "Method: POST " );
        try {
            Subscription subscription = convertToSubscription(requestDto);
            Subscription response = subscriptionService.createSubscription(subscription.getUserName(), subscription.getStartDate());
            return new ResponseEntity<>(convertFromSubscription(response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions() {
        try {
            List<Subscription> response = subscriptionService.getAllSubscription();
            return new ResponseEntity<>(convertFromSubscriptions(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionResponseDto> getSubscriptionById(@PathVariable("id") long id) {
        logger.log(Level.INFO, "Request - " + id + "Method: GET " );
        Optional<Subscription> subscriptionData = subscriptionService.getSubscription(id);

        return subscriptionData.map(subscription -> new ResponseEntity<>(convertFromSubscription(subscription),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable("id") long id, @RequestBody Subscription subscription) {
        logger.log(Level.INFO, "Request - " + id + "Method: PUT " );
        Optional<Subscription> subscriptionData = subscriptionService.updateSubscription(id, subscription.getUserName(), subscription.getStartDate());

        return subscriptionData.map(s -> new ResponseEntity<>(convertFromSubscription(s),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable("id") long id) {
        try {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<SubscriptionResponseDto> convertFromSubscriptions(List<Subscription> subscriptions) {
        return subscriptions.stream()
                .map(this::convertFromSubscription)
                .collect(Collectors.toList());
    }

    private SubscriptionResponseDto convertFromSubscription(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .userName(subscription.getUserName())
                .startDate(subscription.getStartDate())
                .build();
    }

    private Subscription convertToSubscription(SubscriptionRequestDto requestDto) {

        return Subscription.builder()
                .id(requestDto.getId())
                .startDate(requestDto.getStartDate())
                .userName(requestDto.getUserName())
                .build();
    }

}
