package com.grepp.nbe561team01.app.controller.web.user.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRequest {


        @NotBlank
        @Size(min = 4, max = 20)
        private String password;

        @NotBlank
        @Size(min = 4, max = 20)
        private String newPassword;
}

