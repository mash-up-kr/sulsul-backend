package will.of.d.sulsul.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "sulsul API",
        description = "sulsul API Restdocs",
        version = "v0"
    )
)
class SwaggerConfig {
    @Bean
    fun groupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("sulsul-api")
            .packagesToScan("sulsul")
            .pathsToMatch("/api/**")
            .build()
    }
}
