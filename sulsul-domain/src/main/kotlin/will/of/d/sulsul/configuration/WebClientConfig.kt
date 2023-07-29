package will.of.d.sulsul.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${discord.base-url:http://www.naver.com}") private val discordBaseUrl: String
) {
    @Bean
    fun kakaoAuthWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://kapi.kakao.com")
            .build()
    }

    @Bean
    fun discordWebClient(): WebClient {
        return WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl(discordBaseUrl)
            .build()
    }
}
