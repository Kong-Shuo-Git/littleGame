-- 先删除数据库 littleGame(如果存在)
drop database if exists littleGame;

-- 创建数据库
CREATE DATABASE IF NOT EXISTS littleGame CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用 littleGame 数据库
USE littleGame;

-- 创建用户表
CREATE TABLE IF NOT EXISTS user
(
    user_id         INT PRIMARY KEY AUTO_INCREMENT,
    username        VARCHAR(50) NOT NULL UNIQUE,
    password        VARCHAR(50) NOT NULL,
    nickname        VARCHAR(50) NOT NULL,
    create_time     DATETIME    NOT NULL,
    last_login_time DATETIME    NOT NULL
);

-- 创建游戏记录表
CREATE TABLE IF NOT EXISTS game_record
(
    record_id   INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT         NOT NULL,
    game_type   VARCHAR(50) NOT NULL,
    score       INT         NOT NULL,
    level       VARCHAR(20) NOT NULL,
    duration    INT         NOT NULL,
    create_time DATETIME    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

-- 创建索引优化查询性能
CREATE INDEX idx_game_record_user_id ON game_record (user_id);
CREATE INDEX idx_game_record_game_type ON game_record (game_type);
CREATE INDEX idx_game_record_score ON game_record (score);

-- 插入测试数据
INSERT INTO user(username, password, nickname, create_time, last_login_time)
VALUES ('admin', 'admin123', '管理员', NOW(), NOW())
ON DUPLICATE KEY UPDATE nickname = '管理员';