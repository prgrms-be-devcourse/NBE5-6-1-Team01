package com.grepp.nbe561team01.app.controller.web.admin.item;

import com.grepp.nbe561team01.app.controller.web.admin.item.form.ItemRegistForm;
import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin/item")
public class ItemController {

    private final ItemService itemService;

    // 전체 상품 목록
    @GetMapping("list")
    public List<ItemDto> getItemList() {
        return itemService.findAll();
    }

    // 상품 등록
    @PostMapping("add")
    public ResponseEntity<?> addItem(
        @ModelAttribute ItemRegistForm form
    ) {
        itemService.addItem(form.toDto());
        return ResponseEntity.ok("상품 등록 완료");
    }

    // 특정 상품 조회
    @GetMapping("{itemId}")
    @ResponseBody
    public ItemDto getItem(@PathVariable Long itemId) {
        return itemService.findById(itemId);
    }

    // 상품 수정
    @PutMapping("{itemId}/update")
    public ResponseEntity<?> editItem(
        @PathVariable Long itemId,
        @RequestBody ItemDto item) {
        itemService.updateItem(itemId, item);
        return ResponseEntity.ok().build();
    }

    // 상품 삭제
    @DeleteMapping("{itemId}/remove")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        boolean isDeleted = itemService.deleteItem(itemId);

        if (isDeleted) {
            return ResponseEntity.ok("상품 삭제 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당하는 상품을 찾을 수 없습니다.");
        }
    }
}
