package com.biboheart.dmultipledatabase.service.impl;

import com.biboheart.dmultipledatabase.entity.User;
import com.biboheart.dmultipledatabase.repository.UserRepository;
import com.biboheart.dmultipledatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User load(String username) {
        return userRepository.findByUsername(username);
    }
}
