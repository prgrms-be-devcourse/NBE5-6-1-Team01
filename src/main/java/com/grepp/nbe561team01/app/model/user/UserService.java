package com.grepp.nbe561team01.app.model.user;

import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.CommonException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Boolean isDuplicatedId(String id) {
        return userRepository.existsUser(id);
    }

    @Transactional
    public void signup(UserDto dto, Role role){
    if(userRepository.existsUser(dto.getEmail())){
        log.info("signup test: {}", ResponseCode.BAD_REQUEST);
    }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        dto.setRole(role);
        userRepository.insert(dto);
    }


    @Transactional
    public boolean removeUser(String email) {
        // TODO: 삭제할 회원 정보로 수정하거나 로그 출력 삭제 필요
        log.info("삭제할 회원의 이메일: {}", email);

        userRepository.removeUser(email);
        return true;
    }

    public Optional<UserDto> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}

