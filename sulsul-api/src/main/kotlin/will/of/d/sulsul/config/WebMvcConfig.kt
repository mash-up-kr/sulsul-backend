package will.of.d.sulsul.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import will.of.d.sulsul.interceptor.AuthenticationInterceptor
import will.of.d.sulsul.resolver.UserArgumentResolver

@Configuration
class WebMvcConfig(
    private val authenticationInterceptor: AuthenticationInterceptor,
    private val userArgumentResolver: UserArgumentResolver
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
            .excludePathPatterns("/health")
            .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**")
            .excludePathPatterns("/api/v1/drinkingLimit/share")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userArgumentResolver)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
    }
}
