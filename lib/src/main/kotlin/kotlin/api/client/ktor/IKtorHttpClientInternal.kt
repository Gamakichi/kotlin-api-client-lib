package kotlin.api.client.ktor

import kotlin.api.client.http.client.KtorHttpClient
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


interface IKtorHttpClientInternal {

  val client: KtorHttpClient
  val format: Json

  fun getRequest(
    url: String,
    params: Map<String, String> = mapOf(),
    headers: Map<String, String> = mapOf()
  ): HttpRequestBuilder

  fun putRequest(
    url: String,
    params: Map<String, String> = mapOf(),
    headers: Map<String, String> = mapOf(),
    body: Any? = null
  ): HttpRequestBuilder

  fun postRequest(
    url: String,
    params: Map<String, String> = mapOf(),
    headers: Map<String, String> = mapOf(),
    body: Any? = null
  ): HttpRequestBuilder

  fun request(
    method: HttpMethod,
    url: String,
    params: Map<String, String> = mapOf(),
    body: Any? = null,
    headers: Map<String, String> = mapOf()
  ): HttpRequestBuilder
}
