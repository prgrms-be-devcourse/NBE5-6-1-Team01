package com.grepp.nbe561team01.app.model.order.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

        private Integer orderitemId;
        private Integer orderId;
        private Integer itemId;
        private String itemName;
        private Integer itemPrice;
        private Integer itemCount;

}
