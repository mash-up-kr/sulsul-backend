package will.of.d.sulsul.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import will.of.d.sulsul.constant.MOCK_API
import will.of.d.sulsul.constant.ROOT_PACKAGE

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "sulsul API",
        description = "sulsul API Restdocs",
        version = "v0"
    )
)
class SwaggerConfig(
    @Value("\${swagger.host.url:http://localhost:8080}")
    val serverHostUrl: String
) {

    @Bean
    fun openApi(): OpenAPI {
        val schemeName = "Authorization"
        val securityRequirement = SecurityRequirement()
            .addList(schemeName)
        val components = Components()
            .addSecuritySchemes(
                schemeName,
                SecurityScheme()
                    .name(schemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .`in`(SecurityScheme.In.HEADER)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )

        return OpenAPI()
            .addServersItem(Server().url(serverHostUrl))
            .addSecurityItem(securityRequirement)
            .components(components)
    }

    @Bean
    fun server(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("sulsul-api")
            .packagesToScan(ROOT_PACKAGE)
            .build()
    }

    @Bean
    fun mockAPI(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("mock-api")
            .packagesToScan(MOCK_API)
            .build()
    }
}
