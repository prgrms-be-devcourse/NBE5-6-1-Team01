package com.grepp.nbe561team01.app.controller.web.user;

import com.grepp.nbe561team01.app.controller.api.user.form.SignupForm;
import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import jakarta.validation.Valid;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("signup")
    public String signup(SignupForm form){
      return "user/signup";
    }

    @PostMapping("signup")
    public String signup(
        @Valid SignupForm form,
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

    @GetMapping("mypage")
    public String mypage(Authentication authentication, Model model){
        log.info("authentication : {}", authentication);

        String email = authentication.getName();
        UserDto user = userService.findByEmail(email)
            .orElseThrow(() -> new CommonException(ResponseCode.UNAUTHORIZED));

        model.addAttribute("user", user);
        return "user/mypage";
    }
}
