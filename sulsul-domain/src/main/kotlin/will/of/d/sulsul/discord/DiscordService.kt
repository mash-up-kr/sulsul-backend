package will.of.d.sulsul.discord

import org.springframework.stereotype.Service

@Service
class DiscordService(
    private val discordApiClient: DiscordApiService
) {

    fun send(msg: String) {
        discordApiClient.send(msg)
    }
}
