package com.littleGame.dao;

import com.littleGame.pojo.GameRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 游戏记录数据访问接口
 */
@Mapper
public interface GameRecordDao {
    /**
     * 插入游戏记录
     * @param record 游戏记录对象
     * @return 受影响的行数
     */
    int insert(GameRecord record);

    /**
     * 根据用户ID查询游戏记录
     * @param userId 用户ID
     * @return 游戏记录列表
     */
    List<GameRecord> findByUserId(Integer userId);

    /**
     * 根据游戏类型和用户ID查询最高分数
     * @param userId 用户ID
     * @param gameType 游戏类型
     * @return 游戏记录对象
     */
    GameRecord findHighestScoreByGameType(Integer userId, String gameType);

    /**
     * 查询游戏类型的排行榜
     * @param gameType 游戏类型
     * @param limit 排行榜限制数量
     * @return 游戏记录列表
     */
    List<GameRecord> findTopScoresByGameType(String gameType, int limit);
}