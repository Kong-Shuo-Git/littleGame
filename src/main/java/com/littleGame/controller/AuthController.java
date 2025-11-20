package com.littleGame.controller;

import com.littleGame.pojo.User;
import com.littleGame.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * 认证控制器
 * 处理用户登录、注册等认证相关操作
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * 用户服务 - 用于处理用户相关操作
     */
    @Autowired
    private UserService userService;

    /**
     * 登录页面 - 设置为默认页面
     *
     * @return 登录页面视图名称
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 默认路径重定向到登录页
     *
     * @return 重定向到登录页
     */
    @GetMapping("/")
    public String defaultRedirect() {
        return "redirect:/auth/login";
    }

    /**
     * 登录处理接口
     *
     * @param username           用户名
     * @param password           密码
     * @param rememberMe         是否记住登录状态
     * @param session            HTTP会话
     * @param redirectAttributes 重定向属性
     * @return 重定向到游戏首页或登录页
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        @RequestParam(required = false) boolean rememberMe,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        // 使用UserService进行登录验证
        User user = userService.login(username, password);

        if (user != null) {
            // 登录成功，将用户信息存储到session中
            session.setAttribute("user", user);
            session.setAttribute("username", user.getNickname());

            // 更新最后登录时间
            userService.updateLastLoginTime(user.getUserId());

            // 重定向到游戏首页
            return "redirect:/index";
        } else {
            // 登录失败，重定向到登录页面并附带错误参数
            return "redirect:/auth/login?error=login";
        }
    }

    /**
     * 注册页面接口
     *
     * @return 注册页面视图名称
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册处理接口
     *
     * @param username           用户名
     * @param password           密码
     * @param confirmPassword    确认密码
     * @param redirectAttributes 重定向属性
     * @return 重定向到登录页或注册页
     */
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttributes) {
        // 注册验证
        if (username == null || username.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "用户名不能为空");
            return "redirect:/auth/register";
        }

        if (password == null || password.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "密码至少需要6个字符");
            return "redirect:/auth/register";
        }

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "两次输入的密码不一致");
            return "redirect:/auth/register";
        }

        // 使用UserService进行注册
        boolean success = userService.register(username, password, username);

        if (success) {
            // 注册成功后跳转到登录页
            redirectAttributes.addFlashAttribute("success", "注册成功，请登录");
            return "redirect:/auth/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名已存在");
            return "redirect:/auth/register";
        }
    }

    /**
     * 退出登录接口
     *
     * @param session HTTP会话
     * @return 重定向到登录页
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}