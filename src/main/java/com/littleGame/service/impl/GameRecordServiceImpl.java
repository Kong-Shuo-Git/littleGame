package com.littleGame.service.impl;

import com.littleGame.dao.GameRecordDao;
import com.littleGame.pojo.GameRecord;
import com.littleGame.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 游戏记录服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GameRecordServiceImpl implements GameRecordService {

    /**
     * 游戏记录数据访问层
     */
    @Autowired
    private GameRecordDao gameRecordDao;

    /**
     * 保存游戏记录
     * @param record 游戏记录对象
     */
    @Override
    public void saveRecord(GameRecord record) {
        record.setCreateTime(new Date());
        gameRecordDao.insert(record);
    }

    /**
     * 查询用户游戏记录
     * @param userId 用户ID
     * @return 游戏记录列表
     */
    @Override
    public List<GameRecord> getUserRecords(Integer userId) {
        return gameRecordDao.findByUserId(userId);
    }

    /**
     * 查询用户游戏最高分数
     * @param userId 用户ID
     * @param gameType 游戏类型
     * @return 游戏记录对象
     */
    @Override
    public GameRecord getUserHighestScore(Integer userId, String gameType) {
        return gameRecordDao.findHighestScoreByGameType(userId, gameType);
    }

    /**
     * 查询游戏类型的排行榜
     * @param gameType 游戏类型
     * @param limit 排行榜限制数量
     * @return 游戏记录列表
     */
    @Override
    public List<GameRecord> getGameRanking(String gameType, int limit) {
        return gameRecordDao.findTopScoresByGameType(gameType, limit);
    }
}