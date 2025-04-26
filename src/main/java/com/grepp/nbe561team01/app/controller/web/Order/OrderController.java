package com.grepp.nbe561team01.app.controller.web.Order;


import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {

    private final ItemService itemService;

    @GetMapping
    public String displayItems(Model model){
        List<ItemDto> items = itemService.findAll();

        model.addAttribute("items", items);
        return "index";
    }
}
