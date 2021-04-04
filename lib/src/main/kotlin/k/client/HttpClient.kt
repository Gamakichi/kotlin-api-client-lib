package k.client

import k.client.ktor.IKtorHttpClientInternal
import k.client.ktor.send
import kotlinx.serialization.decodeFromString

interface HttpClient : IKtorHttpClientInternal

suspend inline fun <reified T : Any> HttpClient.get(
  url: String,
  params: Map<String, String> = mapOf(),
  headers: Map<String, String> = mapOf()
): T = format.decodeFromString(client.send(getRequest(url, params, headers)))

suspend inline fun <reified T : Any> HttpClient.put(
  url: String,
  params: Map<String, String> = mapOf(),
  headers: Map<String, String> = mapOf(),
  body: Any? = null
): T = format.decodeFromString(client.send(putRequest(url, params, headers, body)))

suspend inline fun <reified T : Any> HttpClient.post(
  url: String,
  params: Map<String, String> = mapOf(),
  headers: Map<String, String> = mapOf(),
  body: Any? = null
): Map<String, Any> = format.decodeFromString(client.send(postRequest(url, params, headers, body)))

