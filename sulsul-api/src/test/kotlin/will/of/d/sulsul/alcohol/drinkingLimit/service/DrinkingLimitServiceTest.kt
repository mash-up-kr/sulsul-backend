package will.of.d.sulsul.alcohol.drinkingLimit.service

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import will.of.d.sulsul.SulsulApplicationTests
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.exception.InvalidRequestException

class DrinkingLimitServiceTest(
    private val drinkingLimitService: DrinkingLimitService
) : SulsulApplicationTests() {

    @Test
    @DisplayName("잘못된 Request (drinkType)에 InvalidRequestException 발생하는지 확인")
    fun badRequestTestByDrinkType() {
        // given
        val randomKakaoUserId = 2015392L
        val badRequest = PostDrinkingLimitReq(drinkType = "water", drinkBottle = 10)

        // then
        assertThrows(InvalidRequestException::class.java) {
            // when
            drinkingLimitService.save(randomKakaoUserId, badRequest)
        }
    }

    @Test
    @DisplayName("잘못된 Request (drinkBottle)에 InvalidRequestException 발생하는지 확인")
    fun badRequestTestByDrinkBottle() {
        // given
        val randomKakaoUserId = 2015392L
        val badRequest = PostDrinkingLimitReq(drinkType = Drink.WHISKY.name, drinkBottle = -1)

        // then
        assertThrows(InvalidRequestException::class.java) {
            // when
            drinkingLimitService.save(randomKakaoUserId, badRequest)
        }
    }
}
