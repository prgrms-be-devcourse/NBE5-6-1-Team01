package com.grepp.nbe561team01.app.controller.api.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.item.dto.ItemImg;
import java.util.List;

public record ItemListPayload (
    List<ItemInfo> itemInfos
) {
    public record ItemInfo(
        Long itemId,
        String itemType,
        String itemName,
        Integer itemPrice,
        Integer itemImgId,
        String originFileName,
        String img
    ){
        public static ItemInfo fromDto(ItemDto item){
            ItemImg img = item.getImg();
            if(img == null || img.getUrl() == null){
                return new ItemInfo(
                    item.getItemId(),
                    item.getItemType(),
                    item.getItemName(),
                    item.getItemPrice(),
                    null,
                    null,
                    null
                );
            }

            return new ItemInfo(
                item.getItemId(),
                item.getItemType(),
                item.getItemName(),
                item.getItemPrice(),
                img.getItemImgId(),
                img.getOriginFileName(),
                img.getUrl()
            );
        }

    }
        public static ItemListPayload fromDtoList(List<ItemDto> items){
            List<ItemInfo> itemInfos = items.stream()
                .map(ItemInfo::fromDto)
                .toList();
            return new ItemListPayload(itemInfos);
        }
}
