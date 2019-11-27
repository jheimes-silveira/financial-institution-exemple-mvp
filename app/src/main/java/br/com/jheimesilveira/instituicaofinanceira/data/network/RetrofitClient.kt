package br.com.jheimesilveira.instituicaofinanceira.data.network

import br.com.jheimesilveira.instituicaofinanceira.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getFinancialInstitution(): FinancialInstitutionApi = getInstance()
        .create(FinancialInstitutionApi::class.java)

    private fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = createInstance(BuildConfig.BASE_URL)
        }

        return retrofit!!
    }

    private fun createInstance(url: String, timeout: Long = 30): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(getHttpClient(timeout))
            .addConverterFactory(getConverterFactory())
            .build()
    }

    private fun getHttpClient(timeout: Long = 30): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(timeout, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(timeout, TimeUnit.SECONDS)

        httpClientBuilder.addInterceptor(TokenInterceptor())

        return httpClientBuilder.build()
    }

    private fun getConverterFactory(): Converter.Factory {
        val gson = GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create()

        return GsonConverterFactory.create(gson)
    }
}