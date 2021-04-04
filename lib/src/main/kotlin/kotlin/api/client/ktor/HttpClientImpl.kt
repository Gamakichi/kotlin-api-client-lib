package kotlin.api.client.ktor

import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlin.api.client.HttpClient
import io.ktor.client.HttpClient as ktorHttpClient


val format = Json {
    ignoreUnknownKeys = true
}

fun client(jsonFormat: Json = format, requestTimeout: Long = 60000, socketTimeout: Long = 60000) = ktorHttpClient {
    install(HttpTimeout) {
        requestTimeoutMillis = requestTimeout
        socketTimeoutMillis = socketTimeout
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(jsonFormat)
        accept(ContentType.Application.Json)
    }
}


class HttpClientImpl(client: ktorHttpClient = client(format)) :
    HttpClient by KtorHttpClientInternal(client, format)
