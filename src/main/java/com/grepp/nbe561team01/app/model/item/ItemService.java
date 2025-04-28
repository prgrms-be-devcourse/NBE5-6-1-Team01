
package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.item.dto.ItemImg;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import com.grepp.nbe561team01.infra.util.file.FileDto;
import com.grepp.nbe561team01.infra.util.file.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileUtil fileUtil;

    @Transactional
    public void addItem(List<MultipartFile> img, ItemDto itemDto) {
        try {
            if (img == null || img.isEmpty()) {
                img = new ArrayList<>();
            }

            List<FileDto> fileDtos = fileUtil.upload(img, "item");

            itemRepository.insertItem(itemDto);

            if (!fileDtos.isEmpty()) {
                ItemImg itemImg = new ItemImg(itemDto.getItemId(), fileDtos.get(0));
                itemRepository.insertImg(itemImg);
            }
        } catch (IOException e) {
            throw new CommonException(ResponseCode.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Transactional
    public List<ItemDto> findAll() {
        return itemRepository.selectAll();
    }

    public ItemDto findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional
    public boolean deleteItem(Long itemId) {
        return itemRepository.removeItem(itemId);
    }

    public List<ItemDto> findAllItems() {
        return itemRepository.selectAll();
    }

    public void updateItem(Long itemId, ItemDto itemDto, Integer itemImgId, List<MultipartFile> img) {
        itemRepository.updateItem(itemId, itemDto);

        // 이미지가 있을 경우
        if (img != null && !img.isEmpty()) {
            if (itemImgId != null && itemImgId > 0) {
                // 기존 이미지가 있을 경우 업데이트
                ItemImg curImg = itemRepository.findByItemImgId(itemImgId);

                if (curImg != null) {
                    try {
                        List<FileDto> fileDtos = fileUtil.upload(img, "item");
                        if (!fileDtos.isEmpty()) {
                            FileDto fileDto = fileDtos.get(0);
                            ItemImg updatedItemImg = new ItemImg(itemDto.getItemId(), fileDto);
                            itemRepository.updateItemImg(curImg.getItemImgId(), updatedItemImg);
                        }
                    } catch (IOException e) {
                        throw new CommonException(ResponseCode.INTERNAL_SERVER_ERROR, e);
                    }
                }
            } else {
                // 새로운 이미지가 있을 경우 삽입
                try {
                    List<FileDto> fileDtos = fileUtil.upload(img, "item");
                    if (!fileDtos.isEmpty()) {
                        FileDto fileDto = fileDtos.get(0);
                        ItemImg itemImg = new ItemImg(itemId, fileDto);
                        itemRepository.insertImg(itemImg);
                    }
                } catch (IOException e) {
                    throw new CommonException(ResponseCode.INTERNAL_SERVER_ERROR, e);
                }
            }
        }
    }
}
