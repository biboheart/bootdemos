package com.biboheart.dmultipledatabase.service;

import com.biboheart.dmultipledatabase.entity.User;

public interface UserService {
    User load(String username);
}
