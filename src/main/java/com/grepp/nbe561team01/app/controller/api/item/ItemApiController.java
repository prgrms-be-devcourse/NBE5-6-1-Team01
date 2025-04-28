package com.grepp.nbe561team01.app.controller.api.item;

import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.infra.response.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/item")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    // 전체 상품 조회
    @GetMapping("list")
    public ResponseEntity<ApiResponse<ItemListPayload>> itemList() {
        List<ItemDto> items = itemService.findAllItems();
        ItemListPayload payload = ItemListPayload.fromDtoList(items);
        return ResponseEntity.ok(ApiResponse.success(payload));
    }
}
