package com.example.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;
}
