package will.of.d.sulsul.auth

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import will.of.d.sulsul.exception.Unauthorized
import will.of.d.sulsul.log.Logger

@Service
class KakaoAuthService(
    @Qualifier("kakaoAuthWebClient")
    private val kakaoAuthWebClient: WebClient
) {

    companion object : Logger

    fun getTokenInfo(accessToken: String): AccessTokenInfo {
        return kakaoAuthWebClient.mutate().build()
            .get().uri("/v1/user/access_token_info")
            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
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

    fun getUserProfile(accessToken: String): KakaoAccountDto {
        return kakaoAuthWebClient.mutate().build()
            .get()
            .uri {
                it.path("/v2/user/me")
                    .queryParam("property_keys", "[\"kakao_account.profile\"]")
                    .build()
            }
            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
            .header(HttpHeaders.CONTENT_TYPE, "${MediaType.APPLICATION_FORM_URLENCODED_VALUE};charset=utf-8")
            .retrieve()
            .bodyToMono(KakaoAccountDto::class.java)
            .block()!!
    }
}

data class AccessTokenInfo(
    val id: Long
)

data class KakaoAccountDto(
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount
)

data class KakaoAccount(
    val profile: KakaoProfile
)

data class KakaoProfile(
    val nickname: String
)
