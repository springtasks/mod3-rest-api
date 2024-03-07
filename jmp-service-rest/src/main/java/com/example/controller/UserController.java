package com.example.controller;

import example.service.impl.UserServiceImpl;
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
