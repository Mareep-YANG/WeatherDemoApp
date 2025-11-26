package cn.mareep.weatherdemoapp.data.remote.client

import cn.mareep.weatherdemoapp.data.models.dto.response.DistrictsSearchResponse
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
    ): List<DistrictsSearchResponse> {
        val params = mapOf(
            "keywords" to keywords,
            "subdistrict" to subdistrict.toString()
        )

        // ✅ 返回纯 DTO
        return doGetList(
            path = "/v3/config/district",
            params = params,
            itemClass = DistrictsSearchResponse::class.java
        )
    }
}
