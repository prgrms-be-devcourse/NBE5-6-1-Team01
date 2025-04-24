package com.grepp.nbe561team01.app.controller.api.user;

import com.grepp.nbe561team01.app.model.user.UserService;
import com.grepp.nbe561team01.infra.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("exists/{id}")
    public ResponseEntity<ApiResponse<Boolean>> existsId(@PathVariable String id){
        return ResponseEntity.ok(ApiResponse.success(
                userService.isDuplicatedId(id)
        ));
    }
}
