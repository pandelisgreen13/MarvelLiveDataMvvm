package gr.padpad.marvellivedata.network.client

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import gr.padpad.marvellivedata.commons.Definitions
import gr.padpad.marvellivedata.commons.DefinitionsApi
import gr.padpad.marvellivedata.model.response.marvel.comics.MarvelComicsResponse
import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse
import gr.padpad.marvellivedata.network.api.MarvelApi
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

class MarvelClient {

    private var marvelApi: MarvelApi

    init {
        marvelApi = getRetrofit().create(MarvelApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(DefinitionsApi.DOMAIN)
                .client(getOkHttpClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpClient() = OkHttpClient().newBuilder()
            .addInterceptor { chain ->

                val currentTimestamp = System.currentTimeMillis()

                val newUrl = chain.request().url()
                        .newBuilder()
                        .addQueryParameter("ts", currentTimestamp.toString())
                        .addQueryParameter("apikey", Definitions.MARVEL_PUBLIC_KEY)
                        .addQueryParameter(
                                "hash",
                                toMD5Hash(currentTimestamp.toString() + Definitions.MARVEL_PRIVATE_KEY + Definitions.MARVEL_PUBLIC_KEY)
                        )
                        .build()

                val newRequest = chain.request()
                        .newBuilder()
                        .url(newUrl)
                        .build()

                chain.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()

    private fun toMD5Hash(toBeEncrypt: String): String {
        var pass = toBeEncrypt
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }

        return encryptedString ?: ""
    }

    fun getMarvelHeroesAsync(limit: Int, offset: Int): Deferred<MarvelHeroResponse> {
        return marvelApi.getMarvelHeroesAsync(limit, offset)
    }

    fun getMarvelComicsAsync(heroId: Int): Deferred<MarvelComicsResponse> {
        return marvelApi.getMarvelComicsAsync(heroId)
    }

}