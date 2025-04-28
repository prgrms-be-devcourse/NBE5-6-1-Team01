package com.grepp.nbe561team01.app.controller.web.Order.form;

import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank
    private String address;
    @NotBlank
    private String postcode;
    private List<OrderItem> orderItems;
    private int totalPrice;

    public OrderDto toDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(address);
        orderDto.setPostcode(postcode);
        orderDto.setTotalPrice(totalPrice);
        return orderDto;
    }
}
