package will.of.d.sulsul.discord

import org.junit.jupiter.api.Test
import will.of.d.sulsul.SharedContext

class DiscordApiServiceTest(
    private val discordApiService: DiscordApiService
) : SharedContext() {
    @Test
    fun asd() {
        val result = discordApiService.send("테스트입니다")
        print(result)
    }
}
