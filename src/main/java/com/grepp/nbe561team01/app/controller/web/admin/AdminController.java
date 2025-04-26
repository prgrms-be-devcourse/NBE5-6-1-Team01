package com.grepp.nbe561team01.app.controller.web.admin;

import com.grepp.nbe561team01.app.controller.web.user.form.SignupRequest;
import com.grepp.nbe561team01.app.model.order.OrderService;
import com.grepp.nbe561team01.app.model.order.code.OrderStatus;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin")
public class AdminController {

    private final UserService adminService;
    private final OrderService orderService;

    @GetMapping("signup")
    public String signup(SignupRequest form){
        return "admin/signup";
    }

    @PostMapping("signup")
    public String signup(
        @Valid SignupRequest form,
        BindingResult bindingResult,
        Model model
    ){
        if(bindingResult.hasErrors()){ return "admin/signup"; }

        adminService.signup(form.toDto(), Role.ROLE_ADMIN);

        return "redirect:/user/signin";
    }

    @GetMapping("")
    public String adminPage() {
        return "admin/index";
    }

    @GetMapping("mypage")
    public String mypage(Authentication authentication, Model model){

        String email = authentication.getName();
        UserDto admin = adminService.findByEmail(email)
            .orElseThrow(() -> new CommonException(ResponseCode.UNAUTHORIZED));

        List<OrderInfoDto> orders = orderService.getAllOrders();

        Map<String, List<String>> orderItemNamesMap = new HashMap<>();
        for (OrderInfoDto order : orders) {
            List<String> itemNames = orderService.getItemNamesByOrderId(order.getOrderId());
            orderItemNamesMap.put(order.getOrderId(), itemNames);
        }

        model.addAttribute("orderItemNamesMap", orderItemNamesMap);
        model.addAttribute("admin", admin);
        model.addAttribute("orders", orders);
        model.addAttribute("itemMap", orderItemNamesMap);

        return "admin/mypage";
    }

    @PostMapping("mypage")
    public String mypage(@RequestParam("orderid") int orderId, Model model){
        adminService.cancelOrder(orderId, "CANCEL");
        return "redirect:/admin/mypage";
    }


    @GetMapping("itemManagement")
    public String itemManagement(SignupRequest form){
        return "admin/item-management";
    }

}
