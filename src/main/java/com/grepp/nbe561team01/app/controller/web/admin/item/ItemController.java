package com.grepp.nbe561team01.app.controller.web.admin.item;

import com.grepp.nbe561team01.app.controller.web.admin.item.form.ItemRegistRequest;
import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
