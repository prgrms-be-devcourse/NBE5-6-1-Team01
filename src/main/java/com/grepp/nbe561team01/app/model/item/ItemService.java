package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(ItemDto itemDto) {
        itemRepository.insertItem(itemDto);
    }

    @Transactional
    public List<ItemDto> findAll() {
        return itemRepository.selectAll();
    }

    @Transactional
    public boolean deleteItem(Long itemId) {
        return itemRepository.removeItem(itemId);
    }


    public ItemDto findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public void updateItem(Long itemId, ItemDto item) {
        itemRepository.updateItem(itemId, item);
    }
}
