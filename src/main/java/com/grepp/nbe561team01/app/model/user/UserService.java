package com.grepp.nbe561team01.app.model.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean removeUser(String email) {
        // TODO: 삭제할 회원 정보로 수정하거나 로그 출력 삭제 필요
        log.info("삭제할 회원의 이메일: {}", email);

        userRepository.removeUser(email);
        return true;
    }
}

