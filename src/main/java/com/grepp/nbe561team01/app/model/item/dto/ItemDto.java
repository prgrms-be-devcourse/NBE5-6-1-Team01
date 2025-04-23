package com.grepp.nbe561team01.app.model.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ItemDto {
        private Integer itemId;
        private String itemType;
        private String itemName;
        private Integer itemPrice;
        private Integer stock;
        private String img;

}
