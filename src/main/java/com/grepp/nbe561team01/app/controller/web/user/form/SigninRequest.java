package com.grepp.nbe561team01.app.controller.web.user.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SigninRequest {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 4, max = 10)
    private String password;

}
