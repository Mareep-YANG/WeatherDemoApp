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
| 2025-11-25 | MVVM架构原理和应用 | 详细讲解了MVVM设计模式的三层架构（Model/View/ViewModel）和各层的职责，说明了MVVM为什么是Android官方推荐的最佳实践（可测试性、易维护、生命周期管理），提供了在天气应用中的具体迁移步骤（添加依赖→创建ViewModel→使用LiveData→创建Repository），指出了当前项目的架构问题（业务逻辑混在Activity中）并给出了分阶段的迁移方案 |
| 2025-11-25 | 实时搜索城市功能实现 | 讲解了实现城市搜索下拉框的六大步骤：UI组件选择（EditText+RecyclerView或AutoCompleteTextView）、城市数据模型创建、网络接口定义（Retrofit）、ViewModel搜索逻辑（使用switchMap/distinctUntilChanged）、Activity绑定观察、加载状态处理，强调了防抖处理的重要性（避免高频接口调用），推荐使用Kotlin Flow的debounce()方案，给出了分阶段实现建议（基础功能→防抖优化→UI简化） |
| 2025-11-25 | 城市搜索功能具体代码实现 | 根据现有MVVM架构，提供了完整的代码实现指导：（1）添加Retrofit、Coroutines、RecyclerView依赖；（2）创建City数据模型；（3）定义AMapApiService接口调用高德API；（4）创建CityRepository处理数据层逻辑；（5）更新MainViewModel添加searchResults/isSearching LiveData和防抖搜索逻辑（使用delay(500)实现防抖）；（6）创建CitySearchAdapter展示搜索结果；（7）修改activity_main.xml添加搜索框和RecyclerView；（8）更新MainActivity绑定搜索框输入和结果列表，强调了防抖、Job管理、Repository模式的重要性 |
| 2025-11-25 | ViewModel中直接调用API客户端 | 讲解了在MainViewModel中调用WeatherApiClient获取数据的完整方案：（1）确保有lifecycle-viewmodel-ktx依赖；（2）导入viewModelScope和coroutines库；（3）添加加载状态和错误信息的LiveData；（4）创建调用API的函数使用viewModelScope.launch启动协程；（5）在try-catch中调用suspend函数searchDistricts并更新LiveData；（6）在init块中调用函数替换硬编码值；（7）在MainActivity中观察新增的LiveData处理加载/错误状态，强调了viewModelScope自动生命周期管理的重要性 |
| 2025-11-25 | 实现MainViewModel API调用完整方案 | 修复了App闪退问题，完整实现了API调用功能：（1）修改MainViewModel添加isLoading和errorMessage LiveData，创建loadWeatherData()函数用viewModelScope.launch调用apiClient.searchDistricts()；（2）创建MainViewModelFactory工厂类处理ViewModel依赖注入；（3）更新MainActivity初始化OkHttpClient、ResponseParser、WeatherApiClient，使用ApiKeyInterceptor自动注入BuildConfig中的API Key；（4）使用ViewModelProvider和Factory创建ViewModel实例，解决了原有的RuntimeException: Cannot create an instance错误，现在App能正常启动并自动加载城市数据 |
| 2025-11-25 | 添加网络权限修复 | 在AndroidManifest.xml中添加了\<uses-permission android:name="android.permission.INTERNET" /\>权限声明，修复了SecurityException: Permission denied错误，现在App能正常进行网络请求 |
| 2025-11-25 | Android权限控制讲解 | 详细讲解了Android权限系统：（1）权限分类：正常权限（低风险，安装时自动授予如INTERNET）和危险权限（高风险，需运行时请求如CAMERA/位置）；（2）权限申请流程：manifest声明→运行时检查→动态请求→处理用户响应；（3）权限分组机制：同组权限申请一次可获得全部；（4）最佳实践：最小权限原则、渐进式请求、处理拒绝情况、提供替代方案；（5）在天气应用中的应用：INTERNET为正常权限、定位权限需要运行时申请 |
| 2025-11-26 | 指导输入栏搜索功能 | 分析了MainActivity中TextWatcher的onTextChanged和MainViewModel中getLocationTips的未完成功能，提供了四步实现指导：（1）在ViewModel中添加LiveData暴露搜索结果，修改getLocationTips使用viewModelScope.launch调用API；（2）在MainActivity的onTextChanged中调用ViewModel方法并动态更新ArrayAdapter；（3）添加边界情况处理（空输入检查、防抖延迟、错误处理）；（4）移除硬编码cities列表，改为动态更新 |
| 2025-11-26 | 搜索点击后下拉栏显示问题 | 分析了问题根本原因：searchEditText.setText(selectedCity)会触发TextWatcher.onTextChanged()再次调用viewModel.getLocationTips()，由于API是异步的，clearLocationTips()执行太晚导致搜索结果仍被显示；提供了解决方案：在修改EditText文本时临时移除TextWatcher防止触发回调，修改完毕后重新添加TextWatcher，避免不必要的搜索请求 |
| 2025-11-26 | MainActivity代码拆分方案 | 分析了代码耦合问题：依赖初始化、UI绑定、观察者设置混在onCreate中，提供了五个拆分方向：（1）提取依赖初始化到工厂类/配置类；（2）使用Hilt依赖注入框架；（3）拆分UI初始化到setupUI()方法；（4）拆分观察者逻辑到observeCityName()、observeLocationTips()等独立方法；（5）考虑迁移到Jetpack Compose；推荐最快改进方案是提取四个独立方法将onCreate从98行简化到约10行 |
| 2025-11-26 | 依赖注入概念讲解 | 详细讲解了依赖注入（DI）的核心思想：不在类内部创建依赖对象，而是从外部注入；通过对比介绍了耦合与松耦合的区别，说明了为什么需要DI（解耦、易测试、代码复用、易维护）；讲解了三种注入方式（构造函数注入、属性注入、接口注入）；介绍了Android中的两个主流框架：Hilt（官方推荐）和Koin（轻量级），提供了具体代码示例；指出项目当前用的工厂模式虽然有帮助但还不是完整DI，后续可引入Hilt框架实现真正的自动化依赖注入 |
| 2025-11-26 | activity_main.xml布局修改 | 指导用户在activity_main.xml中进行三处修改：（1）在搜索容器下方、NestedScrollView上方添加手动同步按钮（新建ConstraintLayout/LinearLayout工具栏，放置Button组件，id=refreshWeatherButton）；（2）删除GridLayout中的三个详情项（体感温度第168-194行、气压第255-281行、紫外线指数第284-310行），并修改GridLayout属性为columnCount="2" rowCount="2"）；（3）在天气描述文本下方添加数据更新时间显示（添加TextView，id=lastUpdateTimeText，使用Material3.BodySmall字体样式和colorOnSurfaceVariant颜色） |
| 2025-11-26 | 直接实现activity_main.xml修改 | 用户授权直接修改代码，完整实现了所有三处修改：（1）添加refreshButtonContainer LinearLayout和refreshWeatherButton（id=@+id/refreshWeatherButton，文字=@string/refresh_weather）；（2）删除体感温度、气压、紫外线指数三个GridLayout子项和占位符，修改GridLayout属性为columnCount="2" rowCount="1"；（3）在天气描述下方添加lastUpdateTimeText TextView；（4）在strings.xml中添加refresh_weather和last_update_time两个字符串资源；（5）修复了湿度和风速的layout_column位置（0和1）确保正确显示 |
| 2025-11-26 | Activity生命周期刷新时机和定期自动刷新 | 讲解了初始加载天气数据的最佳时机：onCreate最优（仅调用一次），onStart/onResume不推荐（每次返回前台都触发）；指出了用户当前实现的正确性；详细分析了三种定期刷新方案：（1）ViewModel+Coroutine（推荐用于前台实时更新，使用while循环+delay实现，在onResume/onPause控制）；（2）WorkManager（适合后台定期同步，遵循Android官方规范，支持APP被杀死后继续运行）；（3）Handler+Runnable（不推荐，易内存泄漏）；建议实务场景中结合使用Coroutine和WorkManager |
