package will.of.d.sulsul.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun kakaoAuthWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://kapi.kakao.com")
            .build()
    }
}
