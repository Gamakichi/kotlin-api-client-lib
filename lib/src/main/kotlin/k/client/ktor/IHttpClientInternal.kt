package k.client.ktor

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import k.client.HttpClient
import k.client.KtorHttpClient


suspend inline fun <reified T> KtorHttpClient.send(request: HttpRequestBuilder): T {
    return this.request(request)
}

internal class KtorHttpClientInternal(override val client: KtorHttpClient, override val format: Json) : HttpClient {

    override fun getRequest(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>
    ): HttpRequestBuilder {
        return request(HttpMethod.Get, url = url, params = params, headers = headers)
    }

    override fun putRequest(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: Any?
    ): HttpRequestBuilder {
        return request(HttpMethod.Put, url = url, params = params, headers = headers, body = body)
    }

    override fun postRequest(
        url: String,
        params: Map<String, String>,
        headers: Map<String, String>,
        body: Any?
    ): HttpRequestBuilder {
        return request(HttpMethod.Post, url = url, params = params, headers = headers, body = body)
    }

    override fun request(
        method: HttpMethod,
        url: String,
        params: Map<String, String>,
        body: Any?,
        headers: Map<String, String>
    ): HttpRequestBuilder {
        val rb = HttpRequestBuilder()
        rb.url(url)
        rb.accept(ContentType.Application.Json)
        rb.method = method
        if (body != null) {
            rb.body = body
        }

        params.map { (k, v) -> rb.parameter(k, v) }
        headers.map { (k, v) -> rb.header(k, v) }
        return rb
    }

}
