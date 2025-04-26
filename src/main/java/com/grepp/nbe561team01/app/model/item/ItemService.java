package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemDto> getAllItems(){
        return itemRepository.selectAll();
    }
}
