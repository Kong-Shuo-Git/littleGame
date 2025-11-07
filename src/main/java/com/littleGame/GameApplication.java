package com.littleGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 游戏应用程序主类
 */
@SpringBootApplication
public class GameApplication {
    /**
     * 游戏应用程序主方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }
}