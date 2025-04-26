package com.grepp.nbe561team01.app.model.order.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderInfoDto {
    private String email;
    private String address;
    private LocalDateTime createdAt;
    private String orderStatus;
}