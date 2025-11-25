# WeatherDemoApp 天气应用

这是一个Android天气应用，集成了高德地图的天气API，可以获取城市当前天气和预测天气数据。

## 功能特性

- 获取城市当前天气信息
- 获取城市天气预测信息
- 集成高德天气API
- 支持Edge-to-Edge显示
- Material Design 3 UI

## 项目结构

```
WeatherDemoApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/cn/mareep/weatherdemoapp/
│   │   │   │   └── MainActivity.kt          # 主活动
│   │   │   ├── res/                          # 资源文件
│   │   │   └── AndroidManifest.xml          # 应用清单
│   │   └── ...
│   ├── build.gradle.kts                     # 模块构建配置
│   └── ...
├── build.gradle.kts                         # 项目构建配置
├── settings.gradle.kts                      # 项目设置
├── gradle.properties                        # Gradle属性
└── README.md                                 # 本文件
```

## 主要依赖

- androidx.core:core-ktx
- androidx.appcompat:appcompat
- com.google.android.material:material
- androidx.activity:activity
- androidx.constraintlayout:constraintlayout
- junit:junit (测试)
- androidx.test.ext:junit (测试)
- androidx.test.espresso:espresso-core (测试)

## 快速开始

### 前置条件

- Android Studio 最新版本
- JDK 11 或更高版本
- Android SDK (API 36)

### 构建步骤

1. 克隆或下载项目
2. 在Android Studio中打开项目
3. 等待Gradle同步完成
4. 构建项目：`./gradlew build`
5. 在模拟器或真机上运行应用

### 获取高德天气API Key

1. 访问[高德开放平台](https://lbs.amap.com/)
2. 注册并登录开发者账号
3. 创建应用并获取API Key
4. 配置API Key到项目中

## 开发说明

### 技术栈

- **语言**: Kotlin
- **架构**: Android原生开发
- **UI框架**: AndroidX + Material Design
- **API调用**: 高德天气API

### 核心组件

- **MainActivity**: 应用主活动，处理UI展示和用户交互

## 建筑和运行

```bash
# 构建应用
./gradlew build

# 运行测试
./gradlew test

# 在连接的设备上运行
./gradlew installDebug
```

## 许可证

本项目为示例项目，可自由使用和修改。


## AI使用说明

| 时间 | 提问内容 | 回答内容 |
|------|---------|---------|
| 2025-11-25 | 初始化README.md文件 | 创建了项目基础文档，包含项目概述、功能特性、项目结构、依赖信息、快速开始指南和开发说明 |
| 2025-11-25 | Material Design主页layout设计 | 详细讲解了Material Design 3主页layout的结构设计，包括：TopAppBar、当前天气卡片、逐小时预报、7天预报、其他信息卡片的实现方法，推荐了具体使用的Material组件（Card、Button、Chip等），提供了颜色主题和设计要点的建议 |
| 2025-11-25 | 解答岗位职责问题| 解答了在公司开发流程中layout设计的岗位职责问题（由Android开发工程师基于设计师稿件实现） |
| 2025-11-25 | Android resource linking错误 | 修复了activity_main.xml第302行的`android:space`属性错误，该属性不存在，改为使用有效的`android:paddingStart`和`android:paddingEnd`属性 |
| 2025-11-25 | API Key管理最佳实践 | 讲解了Demo项目中API Key的几种存储方案：local.properties（推荐）、strings.xml、.env文件、gradle.properties，说明了高德API注册时为什么需要调试版和发布版的安全码，以及真实场景下的API转发架构 |
| 2025-11-25 | 安全码概念解析 | 详细解释了Android应用签名中的SHA1安全码概念，区分了调试版和发布版安全码的用途，提供了获取安全码的多种方法（keytool命令行、Android Studio UI），说明了高德为什么需要验证安全码以及如何妥善管理签名证书 |
| 2025-11-25 | BuildConfig注入方法 | 讲解了在build.gradle.kts中通过buildConfigField注入值到BuildConfig的方法，推荐使用从local.properties读取的方案，提供了具体的代码示例，说明了如何在Kotlin代码中使用BuildConfig字段以及注意事项 |
| 2025-11-25 | CLAUDE.md工作流程优化 | 优化了CLAUDE.md文件，添加了明确的工作流程步骤（理解提问→提供答案→更新README），详细的操作检查清单，常见场景的正误对比，以及如何填写README表格的具体指南，确保每次都能主动更新AI使用说明表格 |
