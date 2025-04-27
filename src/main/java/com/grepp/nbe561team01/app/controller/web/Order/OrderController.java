package com.grepp.nbe561team01.app.controller.web.Order;


import com.grepp.nbe561team01.app.controller.web.Order.form.OrderRequest;
import com.grepp.nbe561team01.app.model.auth.dto.Principal;
import com.grepp.nbe561team01.app.model.item.ItemService;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.order.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping
    public String displayItems(Model model){
        List<ItemDto> items = itemService.findAll();

        model.addAttribute("items", items);
        return "index";
    }

    @PostMapping
    public String order(OrderRequest orderRequest,
        Authentication authentication){

        Principal principal = (Principal) authentication.getPrincipal();
        orderService.createOrder(orderRequest.toDto(), principal.getEmail(), orderRequest.getOrderItems());

        System.out.println(orderRequest);

        return "redirect:/user/mypage";
    }
}
