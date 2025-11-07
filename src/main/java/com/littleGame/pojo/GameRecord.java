package com.littleGame.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 游戏记录实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRecord {
    private Integer recordId;
    private Integer userId;
    /**
     * 游戏类型：minesweeper, snake, gomoku, puzzle, cellular
     */
    private String gameType;
    private Integer score;
    /**
     * 难度级别
     */
    private String level;
    /**
     * 游戏时长（秒）
     */
    private Integer duration;
    /**
     * 创建时间
     */
    private Date createTime;
}