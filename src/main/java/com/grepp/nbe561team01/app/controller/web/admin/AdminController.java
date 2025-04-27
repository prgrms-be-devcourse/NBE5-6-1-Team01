package com.grepp.nbe561team01.app.controller.web.admin;

import com.grepp.nbe561team01.app.controller.web.admin.item.form.ItemRegistForm;
import com.grepp.nbe561team01.app.controller.web.user.form.SignupRequest;
import com.grepp.nbe561team01.app.model.order.OrderService;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

        List<OrderDto> orders = orderService.getAllOrders();
        List<Integer> statuses = orderService.getTotalOrderStatuses();

        // email-address-OrderInfo Map
        Map<String, Map<String, List<OrderInfoDto>>> emailAddressOrderMap = new LinkedHashMap<>();
        for (OrderDto order : orders) {
            // OrderInfoDto 생성
            List<String> itemNames = orderService.getItemNamesByOrderId(order.getOrderId());
            OrderInfoDto orderInfo = new OrderInfoDto(
                order.getOrderId(),
                order.getCreatedAt(),
                order.getPostcode(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                itemNames
            );
            // 이메일을 키로 한 서브 맵 생성 (이메일별로 주문 정보 관리)
            emailAddressOrderMap
                .computeIfAbsent(order.getEmail(), k -> new LinkedHashMap<>())  // 이메일별 서브맵 생성
                .computeIfAbsent(order.getAddress(), k -> new ArrayList<>())  // 주소별 리스트 생성
                .add(orderInfo);  // 리스트에 OrderInfoDto 추가
        }

        model.addAttribute("admin", admin);
        model.addAttribute("orderMap", emailAddressOrderMap);
        model.addAttribute("statuses", statuses);

        return "admin/mypage";
    }

    @PostMapping("mypage")
    public String mypage(@RequestParam("orderid") int orderId, Model model){
        adminService.cancelOrder(orderId, "CANCEL");
        return "redirect:/admin/mypage";
    }


    @GetMapping("itemManagement")
    public String itemManagement(ItemRegistForm form, Model model){
        model.addAttribute("itemRegistForm", form);
        return "admin/item-management";
    }

    @GetMapping("addressDetail")
    public String addressDetail(){

        return "admin/addressDetail";
    }

    @PostMapping("addressDetail")
    public String addressDetail(@RequestParam("orderid") Integer orderId, Model model){
        OrderDto order = orderService.findById(orderId);
        List<OrderItemDto> items = orderService.findItemById(orderId);

        model.addAttribute("orderInfo", order);
        model.addAttribute("items", items);
        return "admin/addressDetail";
    }

}
