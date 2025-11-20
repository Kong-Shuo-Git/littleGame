package com.littleGame.controller;

import com.littleGame.pojo.GameRecord;
import com.littleGame.pojo.User;
import com.littleGame.service.GameRecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameRecordController {
    /**
     * 保存游戏记录
     */
    @Autowired
    private GameRecordService gameRecordService;

    /**
     * 保存游戏记录
     * @param record 游戏记录
     * @param session HTTP会话
     * @return 包含成功状态和消息的响应实体
     */
    @PostMapping("/record")
    public ResponseEntity<Map<String, Object>> saveRecord(@RequestBody GameRecord record, HttpSession session) {
        // 从session中获取当前登录用户信息
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 设置用户ID到游戏记录中
            record.setUserId(user.getUserId());
            // 保存游戏记录
            gameRecordService.saveRecord(record);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "游戏记录保存成功");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "请先登录再保存游戏记录");
            return ResponseEntity.status(401).body(response);
        }
    }

    /**
     * 获取用户游戏记录
     * @param userId 用户ID
     * @return 用户游戏记录列表
     */
    @GetMapping("/records/{userId}")
    public ResponseEntity<List<GameRecord>> getUserRecords(@PathVariable Integer userId) {
        List<GameRecord> records = gameRecordService.getUserRecords(userId);
        return ResponseEntity.ok(records);
    }

    /**
     * 获取用户游戏最高记录
     * @param userId 用户ID
     * @param gameType 游戏类型
     * @return 用户游戏最高记录
     */
    @GetMapping("/highest/{userId}/{gameType}")
    public ResponseEntity<GameRecord> getHighestScore(
            @PathVariable Integer userId,
            @PathVariable String gameType) {
        GameRecord record = gameRecordService.getUserHighestScore(userId, gameType);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    /**
     * 获取游戏排行榜
     * @param gameType 游戏类型
     * @param limit 排行榜限制数量（默认10）
     * @return 游戏排行榜列表
     */
    @GetMapping("/ranking/{gameType}")
    public ResponseEntity<List<GameRecord>> getGameRanking(
            @PathVariable String gameType,
            @RequestParam(defaultValue = "10") int limit) {
        List<GameRecord> rankings = gameRecordService.getGameRanking(gameType, limit);
        return ResponseEntity.ok(rankings);
    }
}