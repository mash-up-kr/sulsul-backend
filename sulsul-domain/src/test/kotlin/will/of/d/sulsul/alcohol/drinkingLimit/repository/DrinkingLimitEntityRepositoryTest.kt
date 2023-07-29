package will.of.d.sulsul.alcohol.drinkingLimit.repository

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import will.of.d.sulsul.SharedContext
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimitEntity
import will.of.d.sulsul.drink.domain.Drink

class DrinkingLimitEntityRepositoryTest(
    private val drinkingLimitRepository: DrinkingLimitRepository
) : SharedContext() {

    @Test
    @DisplayName("주량 등록 잘 되는지 확인")
    fun drinkLimitSuccessTest() {
        // given
        val randomKakaoUserId = 2015392L
        val document = DrinkingLimitEntity(id = null, kakaoUserId = randomKakaoUserId, Drink.SOJU.name, glass = 10, alcoholAmount = 0.0)

        // when
        val findDocument = drinkingLimitRepository.save(document)

        // then
        Assertions.assertThat(findDocument.id).isNotNull
        Assertions.assertThat(drinkingLimitRepository.findById(findDocument.id!!)).isNotNull
    }
}
