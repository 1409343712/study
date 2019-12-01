package com.scream.study.mybatis;

import com.scream.study.mybatis.bean.User;
import com.scream.study.mybatis.server.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/mybatis")
public class Controller {
    @Autowired
    private UserService userService;

    @RequestMapping("/insertUser")
    public void insertUser() {
        User user = new User();
        user.setId(uuid());
        user.setName("男");
        userService.insert(user);
    }

    private String uuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}
