package will.of.d.sulsul.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import will.of.d.sulsul.user.UserApplicationService

@Component
class AuthenticationInterceptor(
    private val userApplicationService: UserApplicationService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val accessToken = request.getAccessToken()
        if (accessToken == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token is not exists or not bearer type")
            return false
        }

        return userApplicationService.getUserOrCreate(accessToken)
            ?.let { true }
            ?: run {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorize")
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
