package com.grepp.nbe561team01.app.controller.web.user.form;

import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    public UserDto toDto(){
        UserDto user = new UserDto();
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }
}
