package will.of.d.sulsul.discord

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Repository
class DiscordApiService(
    @Value("\${discord.token:token}") val token: String,
    @Value("\${discord.channel-id:channel}") private val channelId: String,
    @Qualifier("discordWebClient") private val webClient: WebClient
) {

    fun send(msg: String): String {
        return webClient.post()
            .body(BodyInserters.fromValue(SendReqDto(message = msg, token = token, channelId = channelId)))
            .retrieve()
            .bodyToMono(String::class.java)
            .block()!!
    }
}

data class SendReqDto(
    val message: String,
    val token: String,
    @JsonProperty("channel_id")
    val channelId: String
)
