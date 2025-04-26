package com.grepp.nbe561team01.app.model.user;

import com.grepp.nbe561team01.app.model.user.code.Role;
import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import com.grepp.nbe561team01.infra.error.exceptions.PasswordDuplicatedException;
import com.grepp.nbe561team01.infra.error.exceptions.PasswordNotMatchedException;
import com.grepp.nbe561team01.infra.response.ResponseCode;
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

    public Boolean isDuplicatedEmail(String id) {
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
    public void update(String email, String password, String newPassword){
        // email의 password와 입력한 password가 다른 경우의 예외
        if (!passwordEncoder.matches(password, userRepository.selectPasswordByeMail(email))){
            throw new PasswordNotMatchedException("기존 비밀번호가 일치하지 않습니다.");
        }
        // email의 비밀번호를 newPassword로 최신화, 비밀번호가 바뀌지 않는다면(기존과 같다면) 예외
        if(newPassword.equals(password)){
            throw new PasswordDuplicatedException("기존과 동일한 비밀번호입니다.");
        }
        // 예외가 없으면 update
        String encodedPassword = passwordEncoder.encode(newPassword);
        userRepository.update(email,encodedPassword);
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

    @Transactional
    public boolean cancelOrder(int orderId, String status) {

        userRepository.cancelOrder(orderId, status);
        return false;
    }
}

