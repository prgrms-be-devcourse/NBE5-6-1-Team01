package com.grepp.nbe561team01.app.controller.web.user;

import com.grepp.nbe561team01.app.model.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @DeleteMapping("remove")
    public String removeUser(@RequestParam String email) {
        boolean result = userService.removeUser(email);

        return "redirect:/";
    }

}
