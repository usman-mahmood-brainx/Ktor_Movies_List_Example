package com.example.ktor_example.data.network

import android.util.Log
import com.example.ktor_example.secret.MovieApiKeys.apiKey
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MovieHttpClient @Inject constructor() {

    fun getHttpClient()  =  HttpClient(Android){
        install(JsonFeature){
            serializer = KotlinxSerializer(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging){
            logger = object : io.ktor.client.features.logging.Logger {
                override fun log(message: String) {
                    println(message)
                    Log.i(TAG_KTOR_LOGGER, message)
                }
            }
        }

        install(ResponseObserver){
            onResponse { response ->
                Log.i(TAG_HTTP_STATUS_LOGGER, "Response status: ${response.status.value}")
            }
        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType,ContentType.Application.Json)
            parameter("api_key",apiKey)
        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    companion object{
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status_logger"
    }
}