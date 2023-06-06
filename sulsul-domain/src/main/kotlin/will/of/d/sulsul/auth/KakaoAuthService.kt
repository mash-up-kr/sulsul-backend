package will.of.d.sulsul.auth

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import will.of.d.sulsul.exception.Unauthorized
import will.of.d.sulsul.log.Logger

@Service
class KakaoAuthService(
    @Qualifier("kakaoAuthWebClient")
    private val kakaoAuthWebClient: WebClient,
) {

    companion object : Logger

    fun getTokenInfo(accessToken: String): AccessTokenInfo {
        return kakaoAuthWebClient.get().uri("/v1/user/access_token_info")
            .header("Authorization", accessToken)
            .retrieve()
            .onStatus({ status -> (status.is5xxServerError || status.is4xxClientError) }) { response ->
                val statusCode = response.statusCode()

                val exeption = if (statusCode == HttpStatusCode.valueOf(401)) {
                    Unauthorized("refresh token")
                } else {
                    Unauthorized("bad token")
                }

                log.debug("failed to decode token {}", response)
                Mono.error(exeption)
            }
            .bodyToMono(AccessTokenInfo::class.java)
            .block()!!
    }
}

data class AccessTokenInfo(
    val id : Long,
)