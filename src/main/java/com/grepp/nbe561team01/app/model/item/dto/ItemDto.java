package com.grepp.nbe561team01.app.model.item.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ItemDto {
        private Long itemId;
        private String itemType;
        private String itemName;
        private Integer itemPrice;
        private Integer stock;
        private ItemImg img;
        private Integer itemImgId;
}
