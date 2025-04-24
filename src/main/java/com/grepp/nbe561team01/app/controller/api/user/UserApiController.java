package com.grepp.nbe561team01.app.controller.api.user;

import com.grepp.nbe561team01.app.controller.api.user.form.SignupForm;
import com.grepp.nbe561team01.app.model.user.code.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserApiController {


  @GetMapping("signup")
  public String signup(SignupForm form){
    return "user/signup";
  }

  @PostMapping("signup")
  public String signup(

  ){
//    if(bindingResult.hasErrors()){
//      return "user/signup";
//    }
//
//    userService.signup(form.toDto(), Role.USER);

    return "redirect:/user/mypage";
  }

}
