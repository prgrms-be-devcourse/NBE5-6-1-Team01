package com.grepp.nbe561team01.app.model.order.dto;


import com.grepp.nbe561team01.app.model.order.code.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.Locked;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
        private Integer orderId;
        private Integer userId;
        private String email;
        private String address;
        private String postcode;
        private Integer totalPrice;
        private LocalDateTime createdAt;
        private OrderStatus orderStatus;
}

