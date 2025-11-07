### 小游戏集合平台接口文档


# 小游戏集合平台接口文档

## 1. 用户认证接口

### 1.1 用户登录

- **请求方法**：POST
- **请求路径**：`/api/user/login`
- **请求体**：
  ```json
  {
    "username": "string",  // 用户名
    "password": "string"   // 密码
  }
  ```

- **响应示例**：
  ```json
  {
    "success": true,
    "message": "登录成功",
    "user": {
      "userId": 1,
      "username": "test",
      "nickname": "测试用户"
    }
  }
  ```

### 1.2 用户注册

- **请求方法**：POST
- **请求路径**：`/api/user/register`
- **请求体**：
  ```json
  {
    "username": "string",  // 用户名
    "password": "string",  // 密码
    "nickname": "string"   // 昵称
  }
  ```
- **响应示例**：
  ```json
  {
    "success": true,
    "message": "注册成功"
  }
  ```

### 1.3 注册页面跳转

- **请求方法**：GET
- **请求路径**：`/register`
- **响应**：返回注册页面视图（`register`）

## 2. 用户信息接口

### 2.1 获取用户信息

- **请求方法**：GET
- **请求路径**：`/api/user/info/{userId}`
- **路径参数**：`userId`（用户ID）
- **响应示例**：
  ```json
  {
    "userId": 1,
    "username": "test",
    "nickname": "测试用户",
    "lastLoginTime": "2025-11-03 12:00:00"
  }
  ```

## 3. 游戏记录接口

### 3.1 保存游戏记录

- **请求方法**：POST
- **请求路径**：`/api/record/save`（推测路径，需根据实际Controller调整）
- **请求体**：
  ```json
  {
    "userId": 1,          // 用户ID
    "gameType": "minesweeper",  // 游戏类型（如扫雷、贪吃蛇等）
    "score": 100,         // 分数
    "playTime": 60        // 游戏时长（秒）
  }
  ```
- **响应示例**：
  ```json
  {
    "success": true,
    "message": "记录保存成功"
  }
  ```

### 3.2 获取用户游戏记录

- **请求方法**：GET
- **请求路径**：`/api/record/user/{userId}`（推测路径）
- **路径参数**：`userId`（用户ID）
- **响应示例**：
  ```json
  [
    {
      "recordId": 1,
      "gameType": "minesweeper",
      "score": 100,
      "playTime": 60,
      "createTime": "2025-11-03 12:30:00"
    }
  ]
  ```

### 3.3 获取游戏排行榜

- **请求方法**：GET
- **请求路径**：`/api/record/ranking/{gameType}`（推测路径）
- **路径参数**：`gameType`（游戏类型）
- **查询参数**：`limit`（排行榜数量限制，默认10）
- **响应示例**：
  ```json
  [
    {
      "userId": 1,
      "nickname": "测试用户",
      "score": 100,
      "playTime": 60
    }
  ]
  ```

## 4. 游戏接口（需根据GameController补充）

- 注：游戏相关接口（如游戏页面跳转、游戏状态同步等）需根据`GameController`实际实现补充，例如：
    - 获取游戏页面：`GET /game/{gameType}`
    - 同步游戏状态：`POST /api/game/sync`