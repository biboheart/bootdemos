package com.biboheart.dmultipledatabase.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.dmultipledatabase.entity.User;
import com.biboheart.dmultipledatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/demo/user/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String username) {
        User user = userService.load(username);
        return new BhResponseResult<>(0, "success", user);
    }
}
