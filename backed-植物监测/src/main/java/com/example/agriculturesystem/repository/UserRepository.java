package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 根据用户名查找用户
    User findByUsername(String username);

    User findById(long id);
    // 根据用户名进行模糊查询
    List<User> findByUsernameContainingIgnoreCase(String username);


}
