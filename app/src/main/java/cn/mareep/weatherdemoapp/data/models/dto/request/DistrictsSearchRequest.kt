package cn.mareep.weatherdemoapp.data.models.dto.request

data class DistrictsSearchRequest(
    val keywords: String,
    val subdistrict: Int = 0
)