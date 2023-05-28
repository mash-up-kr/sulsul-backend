package will.of.d.sulsul.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun groupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("sulsul")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun openApiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("sulsul API")
                    .description("sulsul application's api documentation")
                    .version("0.0.1")
            )
    }
}
