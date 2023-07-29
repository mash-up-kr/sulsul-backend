package will.of.d.sulsul.admin

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.user.User

@RequestMapping("/api/v1/admin")
@RestController
class AdminController(
    private val adminApplicationService: AdminApplicationService
) {

    @DeleteMapping("/me")
    fun removeDrinkingLimit(@Parameter(hidden = true) user: User) {
        adminApplicationService.removeMe(user)
    }
}
