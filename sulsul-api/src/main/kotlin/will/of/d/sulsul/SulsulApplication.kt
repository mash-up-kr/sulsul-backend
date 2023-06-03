package will.of.d.sulsul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["sulsul.sulsul-api", "sulsul.sulsul-domain"])
class SulsulApplication

fun main(args: Array<String>) {
    runApplication<SulsulApplication>(*args)
}
