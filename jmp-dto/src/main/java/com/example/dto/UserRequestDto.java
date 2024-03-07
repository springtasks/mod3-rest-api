package com.example.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
