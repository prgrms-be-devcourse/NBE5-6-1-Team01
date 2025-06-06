package com.grepp.nbe561team01.app.controller.web.user;

import com.grepp.nbe561team01.app.controller.web.user.form.SigninRequest;
import com.grepp.nbe561team01.app.controller.web.user.form.SignupRequest;
import com.grepp.nbe561team01.app.controller.web.user.form.UpdateRequest;
import com.grepp.nbe561team01.app.model.auth.dto.Principal;
import com.grepp.nbe561team01.app.model.order.OrderService;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.error.exceptions.PasswordDuplicatedException;
import com.grepp.nbe561team01.infra.error.exceptions.PasswordNotMatchedException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("signup")
    public String signup(SignupRequest form, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 로그인 되어 있으면
        if (authentication != null && authentication.isAuthenticated() &&
            !(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("message", "접근 권한이 없습니다.");
            return "error/redirect";
        }
      return "user/signup";
    }

    @PostMapping("signup")
    public String signup(
        @Valid SignupRequest form,
        BindingResult bindingResult
    ){
      if(bindingResult.hasErrors()){ return "user/signup"; }

      userService.signup(form.toDto(), Role.ROLE_USER);

      return "redirect:/user/signin";
    }

    @PostMapping("remove")
    public String removeUser(@RequestParam("userId") Integer userId, HttpServletRequest request) {
        userService.removeUser(userId);
        // 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, null, null);
        // 로그아웃 후 리다이렉트
        return "redirect:/user/signin";
    }


    @GetMapping("mypage")
    public String mypage(Authentication authentication, Model model){
        log.info("authentication : {}", authentication);

        String email = authentication.getName();
        UserDto user = userService.findByEmail(email)
            .orElseThrow(() -> new CommonException(ResponseCode.UNAUTHORIZED));
        model.addAttribute("user", user);

        orderService.updateOrderStatusByUser(user.getEmail());

        Map<String, List<OrderDto>> orderList = orderService.findOrderAllByEmail(user.getUserId());
        model.addAttribute("orderList", orderList);

        Map<Integer, List<OrderItemDto>> orderItemList = orderService.findOrderItemByOrder(orderList);
        model.addAttribute("orderItemList", orderItemList);

        return "user/mypage";
    }

    @PostMapping("mypage")
    public String mypage(Authentication authentication, @RequestParam("postcode") String postcode){

        String email = authentication.getName();
        UserDto user = userService.findByEmail(email)
                .orElseThrow(() -> new CommonException(ResponseCode.UNAUTHORIZED));
        orderService.removeOrder(user.getEmail(), postcode);

        return "redirect:/user/mypage";
    }

    @GetMapping("signin")
    public String signin(SigninRequest form, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 로그인 되어 있으면
        if (authentication != null && authentication.isAuthenticated() &&
            !(authentication instanceof AnonymousAuthenticationToken)) {
                model.addAttribute("message", "접근 권한이 없습니다.");
            return "error/redirect";
        }
        return "user/signin";
    }


    @GetMapping("update")
    public String update(UpdateRequest form){
        return "user/update";
    }

    @PostMapping("update")
    public String update(
        @Valid
        UpdateRequest form,
        BindingResult bindingResult,
        Authentication authentication,
        Model model
    ){
        //UpdateForm에 맞지 않으면 반환
        if(bindingResult.hasErrors()){
            return "user/update";
        }
        Principal principal = (Principal) authentication.getPrincipal();
        try{
            userService.update(principal.getEmail(), form.getPassword(), form.getNewPassword());
        }catch (PasswordNotMatchedException | PasswordDuplicatedException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/update";
        }

        // update 성공 시 mypage로 redirect
        return "redirect:/user/mypage";
    }

}


