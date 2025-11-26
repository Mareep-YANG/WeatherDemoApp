package cn.mareep.weatherdemoapp.data.models.dto

import com.google.gson.annotations.SerializedName

class DistrictsSearchDTO (
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
    val districts: List<DistrictsSearchDTO>? = null
)