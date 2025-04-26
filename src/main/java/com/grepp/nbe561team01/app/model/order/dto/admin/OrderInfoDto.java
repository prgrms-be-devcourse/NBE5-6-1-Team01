package com.grepp.nbe561team01.app.model.order.dto.admin;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderInfoDto {
    private String orderId;
    private String email;
    private String address;
    private LocalDateTime createdAt;
    private String orderStatus;
}
