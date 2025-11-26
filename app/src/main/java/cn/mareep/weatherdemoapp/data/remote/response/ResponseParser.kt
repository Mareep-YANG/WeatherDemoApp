package cn.mareep.weatherdemoapp.data.remote.response

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ResponseParser(private val gson: Gson) {

    fun <T> parse(json: String, dataClass: Class<T>): ApiResponse<T> {
        val type = TypeToken.getParameterized(
            ApiResponse::class.java,
            dataClass
        ).type
        return gson.fromJson(json, type)
    }

    fun <T> parseList(json: String, itemClass: Class<T>): ApiResponse<List<T>> {
        val listType = TypeToken.getParameterized(List::class.java, itemClass).type
        val responseType = TypeToken.getParameterized(ApiResponse::class.java, listType).type
        return gson.fromJson(json, responseType)
    }

    fun <T> parseWithType(json: String, type: Type): ApiResponse<T> {
        return gson.fromJson(json, type)
    }
}
