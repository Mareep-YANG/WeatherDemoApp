package cn.mareep.weatherdemoapp.data.models.dto.response

import com.google.gson.annotations.SerializedName

class DistrictsSearchResponse (
    @SerializedName("citycode")
    val citycode: String,

    @SerializedName("adcode")
    val adcode: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("center")
    val center: String,

    @SerializedName("level")
    val level: String,

    @SerializedName("districts")
    val districts: List<DistrictsSearchResponse>? = null
)