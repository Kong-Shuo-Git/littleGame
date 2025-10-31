# 小游戏集合平台

## 项目简介

这是一个基于Spring Boot开发的小游戏集合Web应用，集成了多种经典小游戏，支持用户注册登录、游戏记录保存、排行榜等功能。用户可以在一个平台上体验多种休闲游戏，并与其他用户比较成绩。

## 功能特性

- 🔐 **用户认证系统**：完整的注册、登录、退出功能
- 🎮 **多样化游戏**：包含扫雷、贪吃蛇、五子棋、数字华容道、元胞自动机等多种经典游戏
- 📊 **游戏数据管理**：实时保存游戏记录，支持查询历史成绩
- 🏆 **排行榜功能**：每个游戏都有独立的排行榜，展示用户最佳成绩
- 📱 **响应式设计**：适配不同屏幕尺寸，提供良好的移动端体验
- 💾 **数据持久化**：使用MySQL数据库存储用户信息和游戏记录

## 技术栈

- **后端框架**：Spring Boot 2.7.x
- **ORM框架**：MyBatis
- **数据库**：MySQL 8.0
- **前端技术**：HTML5 + CSS3 + JavaScript + Tailwind CSS
- **模板引擎**：Thymeleaf
- **依赖管理**：Maven
- **构建工具**：Spring Boot Maven Plugin

## 环境要求

- JDK 1.8 或更高版本
- Maven 3.6 或更高版本
- MySQL 5.7 或更高版本
- 现代浏览器（Chrome、Firefox、Edge等）

## 快速开始

### 1. 数据库准备

确保MySQL服务已启动，执行以下步骤：

```bash
# 登录MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE littleGame CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行初始化脚本
source src/main/resources/init.sql
```

### 2. 配置修改

根据您的环境修改 `src/main/resources/application.properties` 文件：

```properties
# 数据库连接信息
spring.datasource.url=jdbc:mysql://localhost:3306/littleGame?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=yourpassword

# 服务器配置
server.port=8080
server.servlet.context-path=/game

# MyBatis配置
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=org.example.game.pojo
```

### 3. 构建并运行项目

```bash
# 克隆项目（如果尚未克隆）
git clone https://github.com/yourusername/game.git
cd game

# 构建项目
mvn clean package

# 运行项目
java -jar target/game-0.0.1-SNAPSHOT.jar
```

项目启动后，可通过以下地址访问：
- 登录页面：http://localhost:9090/game/auth/login
- 游戏首页：http://localhost:9090/game/index

## 游戏列表及说明

1. **扫雷**：经典的扫雷游戏，支持初级、中级和高级三种难度
2. **贪吃蛇**：控制蛇头方向，吃掉食物使蛇变长，避免撞墙或自己
3. **五子棋**：黑白双方轮流下棋，先形成五子连线者获胜，支持双人对战
4. **数字华容道**：通过移动数字方块，将其按照顺序排列，挑战思维能力
5. **元胞自动机**：展示细胞自动机的演化过程，支持自定义规则参数

## 项目结构

```
game/
├── src/
│   ├── main/
│   │   ├── java/org/example/game/
│   │   │   ├── controller/      # 控制器层，处理HTTP请求
│   │   │   │   ├── AuthController.java     # 认证相关控制器
│   │   │   │   ├── GameController.java     # 游戏相关控制器
│   │   │   │   ├── UserController.java     # 用户相关控制器
│   │   │   │   └── GameRecordController.java # 游戏记录控制器
│   │   │   ├── dao/             # 数据访问层，定义数据库操作接口
│   │   │   │   ├── UserDao.java
│   │   │   │   └── GameRecordDao.java
│   │   │   ├── pojo/            # 实体类，映射数据库表
│   │   │   │   ├── User.java
│   │   │   │   └── GameRecord.java
│   │   │   ├── service/         # 业务逻辑层
│   │   │   │   ├── UserService.java
│   │   │   │   ├── GameRecordService.java
│   │   │   │   └── impl/        # 业务逻辑实现
│   │   │   └── GameApplication.java  # 应用主入口
│   │   └── resources/
│   │       ├── mapper/          # MyBatis映射文件
│   │       ├── static/          # 静态资源文件
│   │       ├── templates/       # Thymeleaf模板文件
│   │       │   ├── auth/        # 认证相关页面
│   │       │   ├── game/        # 游戏页面
│   │       │   └── fragments/   # 页面片段
│   │       ├── application.properties  # 应用配置
│   │       └── init.sql         # 数据库初始化脚本
│   └── test/                    # 测试代码
└── pom.xml                      # Maven配置
```

## 数据库设计

### user表（用户表）

| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| user_id | INT | 用户ID（主键） |
| username | VARCHAR(50) | 用户名（唯一） |
| password | VARCHAR(50) | 密码 |
| nickname | VARCHAR(50) | 昵称 |
| create_time | DATETIME | 创建时间 |
| last_login_time | DATETIME | 最后登录时间 |

### game_record表（游戏记录表）

| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| record_id | INT | 记录ID（主键） |
| user_id | INT | 用户ID（外键） |
| game_type | VARCHAR(50) | 游戏类型 |
| score | INT | 得分 |
| level | VARCHAR(20) | 难度等级 |
| duration | INT | 游戏时长（秒） |
| create_time | DATETIME | 创建时间 |

## 开发指南

### 添加新游戏

1. 在`src/main/resources/static/game/`下创建游戏文件夹
2. 编写游戏的HTML、CSS和JavaScript文件
3. 在`src/main/resources/templates/game/`下创建对应的Thymeleaf模板
4. 在`GameController`中添加游戏路由方法
5. 更新导航栏(`fragments/nav.html`)，添加新游戏链接
6. 根据需要，为新游戏添加记录保存功能

### 安全注意事项

- 目前密码以明文形式存储，建议在生产环境中使用加密存储
- 可考虑添加CSRF保护和更严格的输入验证
- 生产环境中应关闭调试日志

## 常见问题

### Q: 登录失败怎么办？
A: 请检查用户名和密码是否正确，确保已完成注册。

### Q: 游戏记录不保存怎么办？
A: 请确保您已登录系统，只有登录状态下才能保存游戏记录。

### Q: 数据库连接失败如何解决？
A: 检查application.properties中的数据库配置是否正确，确保MySQL服务正在运行。

## 贡献指南

欢迎贡献代码或提出建议！请遵循以下步骤：

1. Fork本仓库
2. 创建您的特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交您的更改 (`git commit -m 'Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 打开一个Pull Request

## 许可证

本项目采用MIT许可证 - 详情请参阅 [LICENSE](LICENSE) 文件

© 2025 小游戏集合平台 - 享受休闲游戏时光