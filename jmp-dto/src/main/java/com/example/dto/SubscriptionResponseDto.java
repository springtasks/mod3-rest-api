package com.example.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionResponseDto {

    private Long id;
    private String userName;
    private LocalDate startDate;
}
