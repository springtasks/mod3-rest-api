package com.example.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
