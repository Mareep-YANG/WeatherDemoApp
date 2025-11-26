package cn.mareep.weatherdemoapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.mareep.weatherdemoapp.BuildConfig
import cn.mareep.weatherdemoapp.R
import cn.mareep.weatherdemoapp.data.local.preferences.CityPreference
import cn.mareep.weatherdemoapp.data.remote.client.WeatherApiClient
import cn.mareep.weatherdemoapp.data.remote.interceptor.ApiKeyInterceptor
import cn.mareep.weatherdemoapp.data.remote.response.ResponseParser
import com.google.gson.Gson
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var searchTextWatcher: TextWatcher

    // UI 组件
    private lateinit var locateTextView: TextView
    private lateinit var searchEditText: EditText
    private lateinit var searchResultsRecyclerView: RecyclerView

    // 搜索结果列表
    private val locationTipsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 初始化 ViewModel
        initializeViewModel()

        // 初始化 UI 组件
        setupUI()

        // 设置 ViewModel 观察者
        setupViewModelObservers()

        // 设置搜索框文本监听
        setupSearchTextWatcher()

        // 设置 Edge-to-Edge
        setupEdgeToEdge()
    }

    /**
     * 初始化 ViewModel 及其依赖
     */
    private fun initializeViewModel() {
        // 初始化 API 客户端相关依赖
        val gson = Gson()
        val responseParser = ResponseParser(gson)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.AMAP_API_KEY))
            .build()

        val weatherApiClient = WeatherApiClient(
            okHttpClient = okHttpClient,
            baseUrl = "https://restapi.amap.com",
            responseParser = responseParser
        )

        // 初始化 Preference
        val cityPreference = CityPreference(this)

        // 使用 Factory 创建 ViewModel
        val factory = MainViewModelFactory(weatherApiClient, cityPreference)
        viewModel =
            androidx.lifecycle.ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    /**
     * 初始化 UI 组件
     */
    private fun setupUI() {
        locateTextView = findViewById(R.id.locationText)
        searchEditText = findViewById(R.id.searchEditText)
        searchResultsRecyclerView = findViewById(R.id.searchResultsRecyclerView)

        // 配置 RecyclerView
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * 设置 ViewModel 观察者
     */
    private fun setupViewModelObservers() {
        // 观察城市名称变化
        observeCityName()

        // 观察搜索结果
        observeLocationTips()
    }

    /**
     * 观察城市名称变化
     */
    private fun observeCityName() {
        viewModel.cityName.observe(this) { city ->
            locateTextView.text = city
        }
    }

    /**
     * 观察搜索结果变化
     */
    private fun observeLocationTips() {
        viewModel.locationTips.observe(this) { tips ->
            locationTipsList.clear()
            locationTipsList.addAll(tips)

            if (tips.isNotEmpty()) {
                // 创建新的 Adapter 并显示结果
                searchResultsRecyclerView.adapter = SearchResultsAdapter(tips) { selectedCity ->
                    onSearchResultSelected(selectedCity)
                }
                searchResultsRecyclerView.visibility = android.view.View.VISIBLE
            } else {
                searchResultsRecyclerView.visibility = android.view.View.GONE
            }
        }
    }

    /**
     * 处理搜索结果选中事件
     */
    private fun onSearchResultSelected(selectedCity: String) {
        // 移除 TextWatcher 防止触发搜索
        searchEditText.removeTextChangedListener(searchTextWatcher)
        // 触发Toast
        Toast.makeText(this, "已切换城市为$selectedCity", Toast.LENGTH_SHORT).show()
        // 修改文本和更新 ViewModel
        searchEditText.setText(null)
        viewModel.updateCity(selectedCity)
        viewModel.clearLocationTips()

        // 隐藏搜索结果列表
        searchResultsRecyclerView.visibility = android.view.View.GONE

        // 重新添加 TextWatcher
        searchEditText.addTextChangedListener(searchTextWatcher)
    }

    /**
     * 设置搜索框文本监听
     */
    private fun setupSearchTextWatcher() {
        searchTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s != null) {
                    viewModel.getLocationTips(s.toString())
                }
            }
        }
        searchEditText.addTextChangedListener(searchTextWatcher)
    }

    /**
     * 设置 Edge-to-Edge 显示
     */
    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}