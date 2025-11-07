package com.littleGame.service;

import com.littleGame.pojo.GameRecord;

import java.util.List;

public interface GameRecordService {
    /**
     * 保存游戏记录
     *
     * @param record 游戏记录对象
     */
    void saveRecord(GameRecord record);

    /**
     * 获取用户的游戏记录
     *
     * @param userId 用户ID
     * @return 游戏记录列表
     */
    List<GameRecord> getUserRecords(Integer userId);

    /**
     * 获取用户游戏最高分
     *
     * @param userId   用户ID
     * @param gameType 游戏类型
     * @return 游戏记录对象
     */
    GameRecord getUserHighestScore(Integer userId, String gameType);

    /**
     * 获取游戏排行榜
     *
     * @param gameType 游戏类型
     * @param limit    排行榜限制数量
     * @return 游戏记录列表
     */
    List<GameRecord> getGameRanking(String gameType, int limit);
}