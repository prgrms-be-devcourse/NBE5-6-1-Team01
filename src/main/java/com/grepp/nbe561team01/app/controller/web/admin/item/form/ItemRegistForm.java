package com.grepp.nbe561team01.app.controller.web.admin.item.form;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemRegistForm {

    private String itemType;
    @NotBlank(message = "상품명을 입력해주세요.")
    @Size(min = 1, message = "상품명은 1자 이상이어야 합니다.")
    private String itemName;
    @NotNull(message = "상품가격을 입력해주세요.")
    private Integer itemPrice;
    private List<MultipartFile> img;

    public ItemDto toDto(){
        ItemDto item = new ItemDto();
        item.setItemType(itemType);
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        return item;
    }
}
