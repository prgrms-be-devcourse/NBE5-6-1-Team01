package com.grepp.nbe561team01.app.controller.web.admin.item.form;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.item.dto.ItemImg;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemRegistForm {

    private Integer itemId;
    private String itemType;
    @NotBlank
    private String itemName;
    @NotNull
    private Integer itemPrice;
    private List<MultipartFile> img;
    private Integer itemImgId;

    public ItemDto toDto(){
        ItemDto item = new ItemDto();
        item.setItemType(itemType);
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        return item;
    }
}
