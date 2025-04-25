package com.grepp.nbe561team01.app.controller.web.admin;

import com.grepp.nbe561team01.app.controller.web.user.form.SignupRequest;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin")
public class AdminController {

    private final UserService adminService;

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

        model.addAttribute("admin", admin);
        return "admin/mypage";
    }

    @GetMapping("itemManagement")
    public String itemManagement(SignupRequest form){
        return "admin/itemManagement";
    }

}
