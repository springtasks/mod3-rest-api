package com.example.controller;

import example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

    private final UserServiceImpl userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        logger.log(Level.INFO, "Request - " + requestDto + "Method: POST ");
        try {
            User user = convertToUser(requestDto);
            User response = userService.createUser(user.getName(), user.getSurname(), user.getBirthday());
            return new ResponseEntity<>(convertFromUser(response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        try {
            List<User> response = userService.getAllUser();
            return new ResponseEntity<>(convertFromUsers(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") long id) {
        logger.log(Level.INFO, "Request - " + id + "Method: GET ");
        Optional<User> userData = userService.getUser(id);

        return userData.map(user -> new ResponseEntity<>(convertFromUser(user),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.log(Level.INFO, "Request - " + id + "Method: PUT " );
        Optional<User> userData = userService.updateUser(id, user.getName(), user.getSurname(), user.getBirthday());

            return userData.map(u -> new ResponseEntity<>(convertFromUser(u),
                    HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<UserResponseDto> convertFromUsers(List<User> users) {
        return users.stream()
                .map(this::convertFromUser)
                .collect(Collectors.toList());
    }

    private UserResponseDto convertFromUser(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday().toString())
                .build();
    }

    private User convertToUser(UserRequestDto userRequestDto) {

        return User.builder()
                .name(userRequestDto.getName())
                .surname(userRequestDto.getSurname())
                .birthday(LocalDate.parse(userRequestDto.getBirthday()))
                .build();
    }

}
