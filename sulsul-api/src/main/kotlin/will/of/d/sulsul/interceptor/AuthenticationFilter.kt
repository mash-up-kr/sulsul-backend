package will.of.d.sulsul.interceptor

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import will.of.d.sulsul.holder.UserContextHolder
import will.of.d.sulsul.user.UserApplicationService

@Component
class AuthenticationFilter(
    private val userApplicationService: UserApplicationService
) : OncePerRequestFilter() {

    private val excludePaths = listOf("/health", "/swagger-ui", "/v3/api-docs")
    private val sharePaths = listOf("/api/v1/drinkingLimit")

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if (!request.isExcludePath() && !request.isSharePath()) {
            val accessToken = request.getAccessToken()
            if (accessToken == null && !request.isExcludePath()) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "token is not exists or not bearer type")
                return
            }

            userApplicationService.getUserOrCreate(accessToken ?: "unvalid token")
                ?.also {
                    UserContextHolder.set(it)
                }
                ?: run {
                    if (!request.isExcludePath()) {
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized")
                        return
                    }
                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.isExcludePath(): Boolean {
        return excludePaths.any { this.requestURI.startsWith(it) }
    }

    private fun HttpServletRequest.isSharePath(): Boolean {
        return sharePaths.any { this.requestURI.startsWith(it) && this.method.startsWith("GET") }
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
