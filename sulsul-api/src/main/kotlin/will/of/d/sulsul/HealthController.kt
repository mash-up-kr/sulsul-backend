package will.of.d.sulsul

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.log.Logger

@RestController
class HealthController {

    companion object : Logger

    @GetMapping("/health")
    fun health(): ResponseEntity<Unit> {
        log.debug("Health Checked")
        return ResponseEntity.ok().build()
    }
}
