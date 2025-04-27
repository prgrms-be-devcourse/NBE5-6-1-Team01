package com.grepp.nbe561team01.app.model.item.dto;

import com.grepp.nbe561team01.infra.util.file.FileDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemImg {

    private Integer itemImgId;
    private Long itemId;
    private String originFileName;
    private String renameFileName;
    private String savePath;
    private LocalDateTime createdAt;


    public ItemImg(Long itemId, FileDto fileDto){
        this.itemId = itemId;
        this.originFileName = fileDto.originFileName();
        this.renameFileName = fileDto.renameFileName();
        this.savePath = fileDto.savePath();
    }

    public String getUrl(){
        return "/download/" + savePath + renameFileName;
    }
}
