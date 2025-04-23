package com.grepp.nbe561team01.app.model.uesr.dto;


import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

        private String email;
        private String password;
        private String role;
        private LocalDateTime created_at;
        private LocalDateTime deletedAt;
}
