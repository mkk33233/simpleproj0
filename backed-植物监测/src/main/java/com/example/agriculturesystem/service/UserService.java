package com.example.agriculturesystem.service;

import com.example.agriculturesystem.dto.LoginResponse;
import com.example.agriculturesystem.model.User;
import com.example.agriculturesystem.repository.UserRepository;
import com.example.agriculturesystem.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 根据当前用户名查找用户
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    // 更新用户信息
    public User updateUser(long id, User updatedUser) {
        User existingUser = userRepository.findById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }








    /**
     * 用户登录验证
     */
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // 登录成功，返回用户信息
        }
        return null;  // 登录失败
    }

    // 获取所有农民用户
    public List<User> getAllFarmers(String search) {
        if (search != null && !search.isEmpty()) {
            return userRepository.findByUsernameContainingIgnoreCase(search);
        }
        return userRepository.findAll();
    }

    // 创建新用户
    public User createUser(User user) {
        return userRepository.save(user);
    }


    // 删除用户
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
    return userRepository.findByUsername(username); }

    // 模拟数据库查询（你应该替换成实际的数据库查询）
//    public User authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username);  // 查询数据库中是否有该用户名
//
//        // 如果用户存在，并且密码匹配，则返回该用户对象
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//
//        // 如果用户名不存在，或密码不匹配，返回 null
//        return null;
//    }


    public LoginResponse authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);  // 查询数据库中是否有该用户名

        // 如果用户存在，并且密码匹配，则生成 token 并返回用户信息和 token
        if (user != null && user.getPassword().equals(password)) {
            String token = JwtUtil.generateToken(user.getUsername());
            System.out.println("tototo"+token+"tototo");// 生成 JWT token
            return new LoginResponse(user.getUsername(), user.getRole(), token);  // 返回包含用户名和 token 的响应
        }

        // 如果用户名不存在，或密码不匹配，返回 null
        return null;
    }


    public User reg(User a) {
      return  userRepository.save(a);
    }
}




