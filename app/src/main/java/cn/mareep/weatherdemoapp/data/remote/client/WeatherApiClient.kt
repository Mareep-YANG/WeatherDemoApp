package cn.mareep.weatherdemoapp.data.remote.client

import cn.mareep.weatherdemoapp.data.models.dto.DistrictsSearchDTO
import cn.mareep.weatherdemoapp.data.models.dto.response.WeatherInfoDTO
import cn.mareep.weatherdemoapp.data.remote.response.ResponseParser
import okhttp3.OkHttpClient

class WeatherApiClient(
    okHttpClient: OkHttpClient,
    baseUrl: String,
    responseParser: ResponseParser
) : BaseApiClient(okHttpClient, baseUrl, responseParser) {

    /**
     * 搜索区域
     * 高德 API: /v3/config/district
     */
    suspend fun searchDistricts(
        keywords: String,
        subdistrict: Int = 1
    ): List<DistrictsSearchDTO> {
        val params = mapOf(
            "keywords" to keywords,
            "subdistrict" to subdistrict.toString()
        )

        // ✅ 返回纯 DTO
        return doGetList(
            path = "/v3/config/district",
            params = params,
            itemClass = DistrictsSearchDTO::class.java
        )
    }

    /**
     * 获取实况天气信息
     * 高德API: v3/weather/weatherInfo
     */
    suspend fun liveWeatherInfo(
        adcode: String
    ): List<WeatherInfoDTO> {
        val params = mapOf(
            "city" to adcode,
            "extensions" to "base"
        )

        return doGetList(
            path = "/v3/weather/weatherInfo",
            params = params,
            itemClass = WeatherInfoDTO::class.java
        )
    }
}
