package cn.mareep.weatherdemoapp.data.remote.client

import cn.mareep.weatherdemoapp.data.remote.response.ResponseParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

// API 客户端基类

abstract class BaseApiClient(
    protected val okHttpClient: OkHttpClient,
    protected val baseUrl: String,
    protected val responseParser: ResponseParser
) {

    protected suspend fun <T> doGet(
        path: String,
        params: Map<String, String>? = null,
        dataClass: Class<T>
    ): T = withContext(Dispatchers.IO) {
        val url = buildUrl(path, params)
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val responseBody = executeRequest(request)
        val apiResponse = responseParser.parse(responseBody, dataClass)
        apiResponse.getDataOrThrow()
    }

    protected suspend fun <T> doGetList(
        path: String,
        params: Map<String, String>? = null,
        itemClass: Class<T>
    ): List<T> = withContext(Dispatchers.IO) {
        val url = buildUrl(path, params)
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val responseBody = executeRequest(request)
        val apiResponse = responseParser.parseList(responseBody, itemClass)
        apiResponse.getDataOrThrow()
    }

    protected suspend fun <T> doPost(
        path: String,
        jsonBody: String,
        dataClass: Class<T>
    ): T = withContext(Dispatchers.IO) {
        val url = baseUrl + path
        val requestBody = jsonBody
            .toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val responseBody = executeRequest(request)
        val apiResponse = responseParser.parse(responseBody, dataClass)
        apiResponse.getDataOrThrow()
    }

    protected suspend fun <T> doPostForm(
        path: String,
        formData: Map<String, String>,
        dataClass: Class<T>
    ): T = withContext(Dispatchers.IO) {
        val url = baseUrl + path
        val formBody = FormBody.Builder().apply {
            formData.forEach { (key, value) ->
                add(key, value)
            }
        }.build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val responseBody = executeRequest(request)
        val apiResponse = responseParser.parse(responseBody, dataClass)
        apiResponse.getDataOrThrow()
    }

    private suspend fun executeRequest(request: Request): String {
        return suspendCancellableCoroutine { continuation ->
            val call = okHttpClient.newCall(request)

            // 注册取消监听
            continuation.invokeOnCancellation {
                call.cancel()
            }

            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        if (!response.isSuccessful) {
                            continuation.resumeWithException(
                                IOException("HTTP错误: ${response.code}")
                            )
                            return
                        }

                        val body = response.body?.string()
                        if (body == null) {
                            continuation.resumeWithException(
                                IOException("响应体为空")
                            )
                            return
                        }

                        continuation.resume(body)

                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }
            })
        }
    }

    private fun buildUrl(path: String, params: Map<String, String>?): String {
        val urlBuilder = (baseUrl + path).toHttpUrl().newBuilder()
        params?.forEach { (key, value) ->
            urlBuilder.addQueryParameter(key, value)
        }
        return urlBuilder.build().toString()
    }
}
