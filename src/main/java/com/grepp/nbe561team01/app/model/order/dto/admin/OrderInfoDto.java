package com.grepp.nbe561team01.app.model.order.dto.admin;

import com.grepp.nbe561team01.app.model.order.code.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    private Integer orderId;
    private String email;
    private String address;
    private LocalDateTime createdAt;
    private String postcode;
    private OrderStatus orderStatus;
    private Integer totalPrice;
    private List<String> itemNames;
}
