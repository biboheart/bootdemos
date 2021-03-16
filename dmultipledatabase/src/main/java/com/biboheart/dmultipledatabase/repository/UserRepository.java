package com.biboheart.dmultipledatabase.repository;

import com.biboheart.dmultipledatabase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
