package br.com.jheimesilveira.instituicaofinanceira.data.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("content-type", "application/json")

        val token = getToken()

        if (!token.isNullOrEmpty()) {
            builder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(builder.build())
    }

    /**
     * Caso tenha um token
     */
    private fun getToken(): String? {
        return null
    }
}