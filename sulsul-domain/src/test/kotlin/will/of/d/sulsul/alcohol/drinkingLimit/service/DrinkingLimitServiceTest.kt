package will.of.d.sulsul.alcohol.drinkingLimit.service

import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import will.of.d.sulsul.SharedContext
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository
import will.of.d.sulsul.drink.domain.Drink

class DrinkingLimitServiceTest(
    private val drinkingLimitService: DrinkingLimitService,
    private val drinkingLimitRepository: DrinkingLimitRepository
) : SharedContext() {

    @AfterEach
    fun deleteAll() {
        drinkingLimitRepository.deleteAll()
    }

    @Test
    @DisplayName("잘못된 Request (drinkType)에 InvalidRequestException 발생하는지 확인")
    fun badRequestTestByDrinkType() {
        // given
        val randomKakaoUserId = 2015392L
        val badRequest = DrinkingLimit(
            kakaoUserId = randomKakaoUserId,
            drinkType = "물",
            drinkBottle = 10,
            alcoholAmount = 0.0
        )

        // then
        Assertions.assertThrows(ConstraintViolationException::class.java) {
            // when
            drinkingLimitService.save(badRequest)
        }
    }

    @Test
    @DisplayName("잘못된 Request (drinkBottle)에 MethodArgumentNotValidException 발생하는지 확인")
    fun badRequestTestByDrinkBottle() {
        // given
        val randomKakaoUserId = 2015392L
        val badRequest = DrinkingLimit(
            kakaoUserId = randomKakaoUserId,
            drinkType = "소주",
            drinkBottle = -10,
            alcoholAmount = 0.0
        )

        // then
        Assertions.assertThrows(ConstraintViolationException::class.java) {
            // when
            drinkingLimitService.save(badRequest)
        }
    }

    @Test
    @DisplayName("정상적인 Request에 정상 작동(save)되는지 확인")
    fun success() {
        // given
        val randomKakaoUserId = 2015392L
        val goodRequest = DrinkingLimit(
            kakaoUserId = randomKakaoUserId,
            drinkType = "소주",
            drinkBottle = 10
        )

        // when
        val saveDocument = drinkingLimitService.save(goodRequest)

        // then
        assertThat(saveDocument).isNotNull
        assertThat(saveDocument.drinkType).isEqualTo(Drink.SOJU.drinkType)
    }
}
