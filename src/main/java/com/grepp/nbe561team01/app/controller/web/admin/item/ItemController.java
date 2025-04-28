package com.grepp.nbe561team01.app.controller.web.admin.item;

import com.grepp.nbe561team01.app.controller.web.admin.item.form.ItemRegistForm;
import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.item.dto.ItemImg;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 상품 등록
    @PostMapping("add")
    public ResponseEntity<?> addItem(
        @RequestParam(value = "img", required = false) List<MultipartFile> img,
        @Valid @ModelAttribute ItemRegistForm itemRegistForm,
        BindingResult result
    ) {
        if (img != null && !img.isEmpty()) {
            itemService.addItem(img, itemRegistForm.toDto());
        } else {
            itemService.addItem(null, itemRegistForm.toDto());
        }

        return ResponseEntity.ok().build();
    }

    // 상품 수정
    @PostMapping("{itemId}/update")
    public ResponseEntity<?> editItem(
        @PathVariable Long itemId,
        @RequestParam(value = "img", required = false) List<MultipartFile> img,
        @ModelAttribute @Valid ItemRegistForm form) {

        Integer itemImgId = form.getItemImgId();
        itemService.updateItem(itemId, form.toDto(), itemImgId, img);
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
