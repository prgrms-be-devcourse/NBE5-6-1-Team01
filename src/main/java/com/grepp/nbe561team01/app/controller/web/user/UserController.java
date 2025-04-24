package com.grepp.nbe561team01.app.controller.web.user;

import com.grepp.nbe561team01.app.controller.web.user.form.SigninRequest;
import com.grepp.nbe561team01.app.controller.web.user.form.SignupRequest;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("signup")
    public String signup(SignupRequest form){
      return "user/signup";
    }

    @PostMapping("signup")
    public String signup(
        @Valid SignupRequest form,
        BindingResult bindingResult,
        Model model
    ){
      if(bindingResult.hasErrors()){ return "user/signup"; }

      userService.signup(form.toDto(), Role.USER);

      return "redirect:/user/login";
    }


  @DeleteMapping("remove")
    public String removeUser(@RequestParam String email) {
        boolean result = userService.removeUser(email);

        return "redirect:/";
    }

    @GetMapping("signin")
    public String signin(SigninRequest form){
        return "user/signin";
    }


}
