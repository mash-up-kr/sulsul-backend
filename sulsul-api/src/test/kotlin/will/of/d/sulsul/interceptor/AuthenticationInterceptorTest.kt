package will.of.d.sulsul.interceptor

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import will.of.d.sulsul.user.UserApplicationService
import java.util.Base64

class AuthenticationInterceptorTest {

    @Test
    fun `Header에 Authorization 이 없을 경우 401`() {
        // given
        val userApplicationService = mock(UserApplicationService::class.java)
        val interceptor = AuthenticationInterceptor(userApplicationService)

        val request = MockHttpServletRequest()
        val response = MockHttpServletResponse()

        // when
        val actual = interceptor.preHandle(request, response, Any())

        // then
        assertThat(actual, `is`(false))
        assertThat(response.status, `is`(401))
        assertThat(response.errorMessage, `is`("token is not exists or not bearer type"))
    }

    @Test
    fun `Token이 Bearer 타입이 아닌 경우 401`() {
        // given
        val userApplicationService = mock(UserApplicationService::class.java)
        val interceptor = AuthenticationInterceptor(userApplicationService)

        val request = MockHttpServletRequest().apply {
            val credential = Base64.getEncoder().encode("mashup:seongchan".toByteArray())
            addHeader("Authorization", "Basic $credential")
        }
        val response = MockHttpServletResponse()

        // when
        val actual = interceptor.preHandle(request, response, Any())

        // then
        assertThat(actual, `is`(false))
        assertThat(response.status, `is`(401))
        assertThat(response.errorMessage, `is`("token is not exists or not bearer type"))
    }
}
