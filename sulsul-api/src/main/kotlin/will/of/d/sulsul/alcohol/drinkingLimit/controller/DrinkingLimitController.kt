package will.of.d.sulsul.alcohol.drinkingLimit.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.exception.UserNotFoundException
import will.of.d.sulsul.user.User
import will.of.d.sulsul.user.UserService

@Tag(name = "주량등록 컨트롤러")
@RestController
@RequestMapping("/api/drinkingLimit")
class DrinkingLimitController(
    private val drinkingLimitService: DrinkingLimitService,
    private val userService: UserService
) {

    @Operation(summary = "주량 등록 API", description = "로그인 시, 주량을 등록할 때 호출하는 API")
    @PostMapping("")
    fun saveEnrollAlcohol(@RequestBody body: PostDrinkingLimitReq, user: User): ResponseEntity<Any> {
        userService.getUser(user.kakaoUserId)?.let {
            return ResponseEntity.ok(drinkingLimitService.save(it.kakaoUserId, body))
        }

        throw UserNotFoundException("해당 토큰에 대한 유저 정보 없음")
    }
}
