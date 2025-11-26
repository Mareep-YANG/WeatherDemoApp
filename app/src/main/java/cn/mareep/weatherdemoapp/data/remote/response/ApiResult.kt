package cn.mareep.weatherdemoapp.data.remote.response

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val code: String, val message: String) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

// 将 ApiResponse 转换为 ApiResult
fun <T> ApiResponse<T>.toResult(): ApiResult<T> {
    return try {
        if (isSuccess()) {
            ApiResult.Success(getDataOrThrow())
        } else {
            ApiResult.Error(infocode, info)
        }
    } catch (e: ApiException) {
        ApiResult.Error(e.code, e.message)
    } catch (e: Exception) {
        ApiResult.Error("-1", e.message ?: "未知错误")
    }
}

