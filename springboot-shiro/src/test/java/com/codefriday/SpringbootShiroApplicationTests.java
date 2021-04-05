package com.codefriday;

import com.codefriday.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {
    @Autowired
    UserService userServiceImpl;
    @Test
    void contextLoads() {
        System.out.println(userServiceImpl.getUserByName("CodeFriday"));
    }

}
