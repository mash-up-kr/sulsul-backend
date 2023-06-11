package will.of.d.sulsul.auth

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import will.of.d.sulsul.SharedContext
import will.of.d.sulsul.exception.Unauthorized

class KakaoAuthServiceTest(
    private val kakaoAuthService: KakaoAuthService
) : SharedContext() {

    @Test
    fun `invliad token으로 요청하면 Unauthorized 예외가 발생한다`() {
        assertThrows<Unauthorized> {
            kakaoAuthService.getTokenInfo("invalid token")
        }
    }

    @Disabled("20230610 기준으로 성공 확인. 시간이 지나면 token expired 되어서 반드시 실패하므로 비활성화")
    @Test
    fun `valid token`() {
        assertDoesNotThrow {
            kakaoAuthService.getTokenInfo("Bearer WhQA7pRrbjhxhAgJDpSiPauEneaZzTLbph1faAtMCj11nAAAAYijDDwR")
        }
    }
}
