package com.example.agriculturesystem.controller;

import com.example.agriculturesystem.dto.LoginResponse;
import com.example.agriculturesystem.model.LoginRequest;
import com.example.agriculturesystem.model.User;
import com.example.agriculturesystem.repository.UserRepository;
import com.example.agriculturesystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
        @RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
//    @PostMapping("/login")     //           user的账号密码↓
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//        // 进行登录验证
//        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//
//        if (user == null) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//
//        // 如果用户存在且验证通过，将角色存入 session
//        HttpSession session = request.getSession();
//        session.setAttribute("role", user.getRole()); // 存储角色信息到 session
//
//        return ResponseEntity.ok(user);  // 返回用户信息，包括角色等
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//        // 进行登录验证，获取 LoginResponse 对象
//        LoginResponse loginResponse = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//
//        // 如果登录失败
//        if (loginResponse == null) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//
//        // 如果用户存在且验证通过，存储角色信息到 session
//        User user = userRepository.findByUsername(loginRequest.getUsername()); // 获取用户对象
//        if (user != null) {
//            HttpSession session = request.getSession();
//            session.setAttribute("role", user.getRole());  // 存储角色信息到 session
//        }
//
//        // 返回包含用户名和 token 的 LoginResponse 对象
//        return ResponseEntity.ok(loginResponse);  // 返回包含用户名和 token 的响应
//    }
//
//




    //
    @PostMapping("/reg")
    public ResponseEntity<User> reg(@RequestBody LoginRequest loginRequest){
        User a=new User(loginRequest.getUsername(),loginRequest.getPassword(),"farmer");
        userService.reg(a);
        System.out.println(a);
        System.out.println(ResponseEntity.ok(a)+"2111321321312313注册");
        return ResponseEntity.ok(a);

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        // 进行登录验证，获取 LoginResponse 对象
        System.out.println("aaa0--------------------------------------------------------------------");
        LoginResponse loginResponse = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
   System.out.println("aaa");
        // 如果登录失败
        if (loginResponse == null) {
            System.out.println("vvv");     return ResponseEntity.status(401).body("Invalid credentials");
        }

        // 如果用户存在且验证通过，存储角色信息到 session
        User user = userRepository.findByUsername(loginRequest.getUsername()); // 获取用户对象
        if (user != null) {
            System.out.println("aaa");   HttpSession session = request.getSession();
            session.setAttribute("role", user.getRole());  // 存储角色信息到 session
        }
        System.out.println("aaa");

        // 返回包含用户名和 token 的 LoginResponse 对象
        return ResponseEntity.ok(loginResponse);  // 返回包含用户名和 token 的响应
    }



    // 获取当前用户adminid
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    // 更新当前admin信息
    @PutMapping("/{id}/change-password")
    public User updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }










    // 获取所有用户
    @GetMapping
    public List<User> getAllUsers(@RequestParam(value = "search", required = false) String search) {
        return userService.getAllFarmers(search);
    }

    // 创建用户
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // 更新用户信息
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // 更新逻辑
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            userRepository.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }




}


