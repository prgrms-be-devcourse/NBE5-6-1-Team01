package com.grepp.nbe561team01.app.model.auth;

import com.grepp.nbe561team01.app.model.auth.dto.Principal;
import com.grepp.nbe561team01.app.model.user.UserRepository;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        UserDto user = userRepository.selectByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return Principal.createPrincipal(user, authorities);


    }
    
}
