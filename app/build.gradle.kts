import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}
android {
    namespace = "cn.mareep.weatherdemoapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "cn.mareep.weatherdemoapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ========== 读取 local.properties ==========
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(FileInputStream(localPropertiesFile))
        }
        // ========== 注入到 BuildConfig ==========
        buildConfigField("String", "AMAP_API_KEY",
            "\"${localProperties.getProperty("AMAP_API_KEY") ?: ""}\"")
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    // Activity KTX（提供 by viewModels()）
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    // OKHttp - HTTP请求
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // Gson - JSON解析
    implementation("com.google.code.gson:gson:2.10.1")
    // Coroutines - 异步处理
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    // Lifecycle with Coroutines
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    // RecyclerView - 显示搜索结果列表
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.basement)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}