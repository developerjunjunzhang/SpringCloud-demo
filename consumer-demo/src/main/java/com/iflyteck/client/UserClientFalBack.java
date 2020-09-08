package com.iflyteck.client;

import com.iflyteck.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFalBack implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setName("未知用户");
        return user;
    }
}
