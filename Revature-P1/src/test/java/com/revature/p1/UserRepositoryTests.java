package com.revature.p1;

import com.revature.p1.Models.User;
import com.revature.p1.Repositories.UserRepository;
import com.revature.p1.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserService userService;


    // FIXME: Not working
    @Test
    public void registerNewUserTest() {
        User user = new User();
        user.setFname("George");
        user.setLname("Washington");
        user.setEmail("gw@us.gov");
        user.setBio("1st Pres of US");
        user.setUsername("gwus");
        user.setPassword("1234");

        userService.createUser(user);

        Assertions.assertTrue(user.getUserId() > 0);
    }
}

