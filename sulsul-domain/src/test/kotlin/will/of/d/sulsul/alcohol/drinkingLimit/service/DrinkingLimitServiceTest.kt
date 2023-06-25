package will.of.d.sulsul.alcohol.drinkingLimit.service

import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import will.of.d.sulsul.SharedContext
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository
import will.of.d.sulsul.drink.domain.Drink
import java.time.format.DateTimeFormatter

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
            glass = 10,
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
            glass = -10,
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
    fun saveSuccess() {
        // given
        val randomKakaoUserId = 2015392L
        val goodRequest = DrinkingLimit(
            kakaoUserId = randomKakaoUserId,
            drinkType = "소주",
            glass = 10
        )

        // when
        val saveDocument = drinkingLimitService.save(goodRequest)

        // then
        assertThat(saveDocument).isNotNull
        assertThat(saveDocument.drinkType).isEqualTo(Drink.SOJU.type)
    }

    @Test
    @DisplayName("가장 최근에 등록된 주량이 조회되는지 확인")
    fun findSuccess() {
        // given
        val randomKakaoUserId = 2015392L
        val goodRequest = DrinkingLimit(
            kakaoUserId = randomKakaoUserId,
            drinkType = "소주",
            glass = 10
        )

        val saveDocument1 = drinkingLimitService.save(goodRequest)
        val saveDocument2 = drinkingLimitService.save(goodRequest)

        Thread.sleep(1000)
        val saveDocument3 = drinkingLimitService.save(goodRequest)

        // when
        val findDocument = drinkingLimitService.findByUserId(randomKakaoUserId)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val latestDate = maxOf(saveDocument1.createdAt, saveDocument2.createdAt, saveDocument3.createdAt)

        // then
        assertThat(findDocument.createdAt.format(formatter)).isEqualTo(latestDate.format(formatter))
    }

    @Test
    @DisplayName("등록된 주량 없을 때에는 에러 발생시키는지 확인")
    fun findNotFound() {
        val randomKakaoUserId = 2015392L

        assertThrows<NotFoundException> {
            drinkingLimitService.findByUserId(randomKakaoUserId)
        }
    }
}
