package com.grepp.nbe561team01.app.model.auth.dto;

import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Principal extends User {


    public Principal(String email, String password,
        Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    public static Principal createPrincipal(UserDto user,
        List<SimpleGrantedAuthority> authorities){
        return new Principal(user.getEmail(), user.getPassword(), authorities);
    }
}
