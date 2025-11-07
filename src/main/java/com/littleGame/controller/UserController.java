package com.littleGame.controller;

import com.littleGame.pojo.User;
import com.littleGame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    /**
     * 用户登录
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * @param request 包含用户名和密码的请求体
     * @return 包含成功状态、消息和用户信息的响应实体
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        User user = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("user", user);
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 用户注册接口
     * @param request 包含用户名、密码和昵称的请求体
     * @return 包含成功状态和消息的响应实体
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String nickname = request.get("nickname");
        boolean success = userService.register(username, password, nickname);
        Map<String, Object> response = new HashMap<>();

        if (success) {
            response.put("success", true);
            response.put("message", "注册成功");
        } else {
            response.put("success", false);
            response.put("message", "用户名已存在");
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 获取用户信息接口
     * @param userId 用户ID
     * @return 用户信息实体
     */
    @GetMapping("/info/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable Integer userId) {
        User user = userService.getUserInfo(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}