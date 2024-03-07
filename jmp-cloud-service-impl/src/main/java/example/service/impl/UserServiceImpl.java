package example.service.impl;

import com.example.dto.User;
import service.UserService;
import example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

@Autowired
public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}


    @Override
    public User createUser(String userName, String lastName, LocalDate birthDate) {
         return userRepository.save( User.builder().name(userName).surname(lastName).birthday(birthDate).build());
    }


    @Override
    public Optional<User> updateUser(Long userId, String userName, String lastName, LocalDate birthDate) {
        Optional<User> result = userRepository.findById(userId);
        if(result.isPresent()) {
            userRepository.deleteById(userId);
            return Optional.of(userRepository.save(User.builder().name(userName).surname(lastName).birthday(birthDate).build()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }


    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
