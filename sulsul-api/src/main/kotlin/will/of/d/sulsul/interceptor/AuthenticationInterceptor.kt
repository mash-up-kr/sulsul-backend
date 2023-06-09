package will.of.d.sulsul.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import will.of.d.sulsul.auth.KakaoAuthService
import will.of.d.sulsul.user.UserService

@Component
class AuthenticationInterceptor(
    private val kakaoAuthService: KakaoAuthService,
    private val userService: UserService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val accessToken = request.getAccessToken()
        if (accessToken == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token is not exists or not bearer type")
            return false
        }

        return try {
            val tokenInfo = kakaoAuthService.getTokenInfo(accessToken)
            userService.getUser(tokenInfo.id) ?: userService.signup(tokenInfo.id)
            true
        } catch (e: Exception) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.message)
            false
        }
    }

    private fun HttpServletRequest.getAccessToken(): String? {
        return this.getHeader("Authorization")?.let { token ->
            if (token.startsWith("Bearer ")) {
                token.replaceFirst("Bearer ", "")
            } else {
                null
            }
        }
    }
}
