package will.of.d.sulsul.user

import org.springframework.stereotype.Service
import will.of.d.sulsul.auth.KakaoAccount
import will.of.d.sulsul.auth.KakaoAuthService
import will.of.d.sulsul.log.Logger

@Service
class UserApplicationService(
    private val kakaoAuthService: KakaoAuthService,
    private val userService: UserService
) {

    companion object : Logger

    fun getUserOrCreate(accessToken: String): User? {
        return try {
            val tokenInfo = kakaoAuthService.getTokenInfo(accessToken)
            return userService.getUser(tokenInfo.id) ?: run {
                val kakaoAccount = getKakaoAccount(accessToken)
                val user = User(kakaoUserId = tokenInfo.id, kakaoAccount.profile.nickname)
                userService.signup(user)
            }
        } catch (e: Exception) {
            log.debug("Failed to authorize user. exception: {}", e)
            null
        }
    }

    private fun getKakaoAccount(accessToken: String): KakaoAccount {
        return kakaoAuthService.getUserProfile(accessToken).kakaoAccount
    }
}