package com.littleGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    /**
     * 游戏首页
     *
     * @return 游戏首页视图名称
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 细胞自动机游戏页面
     *
     * @return 细胞自动机游戏视图名称
     */
    @GetMapping("/cellular")
    public String cellular() {
        return "cellular";
    }

    /**
     * 扫雷游戏页面
     *
     * @return 扫雷游戏视图名称
     */
    @GetMapping("/minesweeper")
    public String minesweeper() {
        return "minesweeper";
    }

    /**
     * 贪吃蛇游戏页面
     *
     * @return 贪吃蛇游戏视图名称
     */
    @GetMapping("/snake")
    public String snake() {
        return "snake";
    }

    /**
     * 五子棋游戏页面
     *
     * @return 五子棋游戏视图名称
     */
    @GetMapping("/gobang")
    public String gobang() {
        return "gobang";
    }

    /**
     * 拼图游戏页面
     *
     * @return 拼图游戏视图名称
     */
    @GetMapping("/puzzle")
    public String puzzle() {
        return "puzzle";
    }
}