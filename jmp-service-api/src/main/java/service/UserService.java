package service;

import com.example.dto.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User createUser(String userName, String LastName, LocalDate BirthDate);
    User updateUser(Long userId, String userName, String LastName, LocalDate BirthDate);
    void deleteUser(Long userId);
    User getUser(Long userId);
    List<User> getAllUser();

}
