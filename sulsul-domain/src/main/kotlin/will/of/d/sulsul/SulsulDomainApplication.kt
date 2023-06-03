package will.of.d.sulsul

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

// @SpringBootApplication
@ComponentScan(basePackageClasses = [SulsulDomainApplication::class])
@Configuration
class SulsulDomainApplication
