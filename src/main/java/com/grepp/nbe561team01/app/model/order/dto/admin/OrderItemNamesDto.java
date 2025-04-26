package com.grepp.nbe561team01.app.model.order.dto.admin;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemNamesDto {

    private String orderId;
    private List<String> itemNames;

}
