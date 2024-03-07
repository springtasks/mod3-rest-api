package com.example.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionRequestDto {

    private Long id;
    private Long userId;
}
