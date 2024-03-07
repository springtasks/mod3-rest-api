package example.service.impl;

import com.example.dto.Subscription;
import com.example.dto.User;
import service.SubscriptionService;
import example.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    @Autowired
    SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription createSubscription(User user, LocalDate startDate) {
        return subscriptionRepository.save( Subscription.builder().userName(user.getName()).startDate(startDate).build());
    }


    @Override
    public Subscription updateSubscription(Long subscriptionId, User user, LocalDate startDate) {
        Optional<Subscription> result = subscriptionRepository.findById(subscriptionId);
        if(result.isPresent()) {
            subscriptionRepository.deleteById(subscriptionId);
            return subscriptionRepository.save(Subscription.builder().userName(user.getName()).startDate(startDate).build());
        }
        return null;
    }


    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);

    }

    @Override
    public Subscription getSubscription(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).orElse(null);
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }
}
