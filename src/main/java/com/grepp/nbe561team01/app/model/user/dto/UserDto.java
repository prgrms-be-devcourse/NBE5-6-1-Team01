package com.grepp.nbe561team01.app.model.user.dto;


import com.grepp.nbe561team01.app.model.user.code.Role;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

        private Integer userId;
        private String email;
        private String password;
        private Role role;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;

}
