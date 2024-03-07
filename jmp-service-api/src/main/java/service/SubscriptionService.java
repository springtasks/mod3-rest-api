package service;

import java.time.LocalDate;
import java.util.List;
import com.example.dto.User;
import com.example.dto.Subscription;

public interface SubscriptionService {

    Subscription createSubscription(User user, LocalDate startDate);
    Subscription updateSubscription(Long id, User user, LocalDate startDate);
    void deleteSubscription(Long subscriptionId);
    Subscription getSubscription(Long subscriptionId);
    List<Subscription> getAllSubscription();

}
