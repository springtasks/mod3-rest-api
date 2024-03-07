package example.service.impl;

import com.example.dto.Subscription;
import com.example.dto.User;
import service.SubscriptionService;
import example.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public Subscription createSubscription(String userName, LocalDate startDate) {
        return subscriptionRepository.save( Subscription.builder().userName(userName).startDate(startDate).build());
    }


    @Override
    public Optional<Subscription> updateSubscription(Long subscriptionId, String userName, LocalDate startDate) {
        Optional<Subscription> result = subscriptionRepository.findById(subscriptionId);
        if(result.isPresent()) {
            subscriptionRepository.deleteById(subscriptionId);
            return Optional.of(subscriptionRepository.save(Subscription.builder().userName(userName).startDate(startDate).build()));
        }
        return Optional.empty();
    }


    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);

    }

    @Override
    public Optional<Subscription> getSubscription(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId);
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }
}
