package will.of.d.sulsul.discord

import org.springframework.stereotype.Service
import will.of.d.sulsul.log.Logger

@Service
class DiscordService(
    private val discordApiClient: DiscordApiService
) {

    companion object : Logger

    fun send(msg: String) = runCatching { discordApiClient.send(msg) }
        .onFailure { log.debug("Failed to send discord msg", it) }
}
