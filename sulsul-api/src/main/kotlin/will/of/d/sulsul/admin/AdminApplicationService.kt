package will.of.d.sulsul.admin

import org.springframework.stereotype.Service
import will.of.d.sulsul.user.User
import will.of.d.sulsul.user.UserService

@Service
class AdminApplicationService(
    private val userService: UserService
) {
    fun removeMe(user: User) {
        userService.upsert(user.copy(drinkingLimit = null, title = null))
    }
}
