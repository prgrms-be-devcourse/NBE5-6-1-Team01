package com.grepp.nbe561team01.app.model.user;

import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void removeUser(){
        userService.removeUser("aaa@aaa.com");
    }

    @Test
    public void findUser(){
        log.info("user: {}", userService.findByEmail("test@test.com"));
    }

}