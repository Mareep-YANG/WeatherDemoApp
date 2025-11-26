package cn.mareep.weatherdemoapp.data.models.dto.response

import com.google.gson.annotations.SerializedName
class WeatherInfoDTO (
    @SerializedName("province")
    val province: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("adcode")
    val adcode: String,
    @SerializedName("weather")
    val weather: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("winddirection")
    val winddirection: String,
    @SerializedName("windpower")
    val windpower: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("reporttime")
    val reporttime: String
)