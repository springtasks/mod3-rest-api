package service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.dto.User;
import com.example.dto.Subscription;

public interface SubscriptionService {

    Subscription createSubscription(String userName, LocalDate startDate);
    Optional<Subscription> updateSubscription(Long id, String userName, LocalDate startDate);
    void deleteSubscription(Long subscriptionId);
    Optional<Subscription> getSubscription(Long subscriptionId);
    List<Subscription> getAllSubscription();

}
