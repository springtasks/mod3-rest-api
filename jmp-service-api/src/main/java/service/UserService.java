package service;

import com.example.dto.User;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(String userName, String LastName, LocalDate BirthDate);
    Optional<User> updateUser(Long userId, String userName, String LastName, LocalDate BirthDate);
    void deleteUser(Long userId);
    Optional<User> getUser(Long userId);
    List<User> getAllUser();

}
