package cn.mareep.weatherdemoapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.mareep.weatherdemoapp.data.local.preferences.CityPreference
import cn.mareep.weatherdemoapp.data.models.dto.DistrictsSearchDTO
import cn.mareep.weatherdemoapp.data.models.dto.response.WeatherInfoDTO
import cn.mareep.weatherdemoapp.data.remote.client.WeatherApiClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(
    private val apiClient: WeatherApiClient,
    private val cityPreference: CityPreference
) : ViewModel() {

    // 城市名称
    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    // 搜索提示结果
    private val _locationTips = MutableLiveData<List<String>>()
    val locationTips: LiveData<List<String>> = _locationTips

    // 保存搜索结果的完整信息
    private var searchResultsMap = mapOf<String, String>()

    // 搜索行政区 Job
    private var searchJob: Job? = null
    // 刷新天气数据 job
    private var liveWeatherJob: Job? = null
    // 天气数据
    private val _liveWeatherData = MutableLiveData<List<WeatherInfoDTO>>()
    val liveWeatherData: LiveData<List<WeatherInfoDTO>> = _liveWeatherData

    fun clearLocationTips() {
        _locationTips.value = emptyList()
    }
    /**
     * 刷新实况天气信息
     */
    fun getLiveWeatherInfo(){
        liveWeatherJob?.cancel() // 取消之前的获取天气信息的任务

        liveWeatherJob = viewModelScope.launch {
            try {
                delay(300)
                _liveWeatherData.value = apiClient.liveWeatherInfo(cityPreference.selectedCode)
            } catch (e: Exception) {
                Log.e("MainViewModel","获取天气信息失败：${e.message}", e)
            }
        }

    }
    /**
     * 获取下拉栏提示
     */
    fun getLocationTips(input: String) {
        // 如果输入为空，不进行搜索
        if (input.isBlank()) {
            _locationTips.value = emptyList()
            Log.d("MainViewModel", "搜索输入为空")
            return
        }

        // 取消之前的搜索任务（防抖处理）
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            try {
                Log.d("MainViewModel", "开始搜索: $input")

                // 延迟 300ms 进行搜索，避免频繁调用 API
                delay(300)

                val tipsList: List<DistrictsSearchDTO> = apiClient.searchDistricts(input, 0)
                Log.d("MainViewModel", "搜索成功，返回 ${tipsList.size} 条结果")

                // 提取name字段作为搜索提示，同时保存 name->adcode 的映射
                val tips = tipsList.map { it.name }
                searchResultsMap = tipsList.associateBy { it.name }.mapValues { it.value.adcode }

                Log.d("MainViewModel", "搜索建议: $tips")
                _locationTips.value = tips

            } catch (e: Exception) {
                Log.e("MainViewModel", "搜索失败: ${e.message}", e)
                _locationTips.value = emptyList()
            }
        }
    }

    /**
     * 更新城市名称和adcode
     */
    fun updateCity(cityName: String) {
        _cityName.value = cityName

        // 从映射表中获取 adcode 并保存到 Preference
        val adcode = searchResultsMap[cityName]
        if (adcode != null) {
            cityPreference.selectedCode = adcode
            Log.d("MainViewModel", "城市已更新: $cityName, adcode: $adcode")
        } else {
            Log.w("MainViewModel", "未找到城市 $cityName 的 adcode")
        }
    }
}

