package cn.mareep.weatherdemoapp.data.remote.interceptor
import okhttp3.Interceptor
import okhttp3.Response
class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        // 添加 key 参数
        val url = originalUrl.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        // 重新构建请求
        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}