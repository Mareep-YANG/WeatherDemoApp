package cn.mareep.weatherdemoapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T> (
    @SerializedName("status")
    val status: String,
    @SerializedName("info")
    val info: String,
    @SerializedName("infocode")
    val infocode: String,
    @SerializedName("count")
    val count: String? = null,
    @SerializedName("districts")     // 高德 API 返回的区域数据
    val districts: T? = null,
    @SerializedName("lives")         // 高德 API 返回的天气数据
    val lives: T? = null
) {
    // 接口调用是否成功
    fun isSuccess(): Boolean = status == "1"
    // 获取数据
    fun getData(): T? = districts ?: lives

    fun getDataOrThrow(): T {
        return if (isSuccess()) {
            getData() ?: throw ApiException(infocode, "数据为空")
        } else {
            throw ApiException(infocode, info)
        }
    }
}
class ApiException(
    val code: String,
    override val message: String
) : Exception(message)