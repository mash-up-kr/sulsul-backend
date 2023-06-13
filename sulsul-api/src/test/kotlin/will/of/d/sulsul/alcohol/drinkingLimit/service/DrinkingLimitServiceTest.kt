package will.of.d.sulsul.alcohol.drinkingLimit.service

import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository

@SpringBootTest
class DrinkingLimitServiceTest(
    @Autowired
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    @Test
    @DisplayName("주량 등록 잘 되는지 확인")
    fun enrollAlcoholTest() {
        // given
        val randomUserId = "507f191e810c19729de860ea"
        val document = DrinkingLimit(id = null, ObjectId(randomUserId), 1, 1)

        // when
        val findDocument = drinkingLimitRepository.save(document)

        // then
        assertThat(findDocument.id).isNotNull
        assertThat(drinkingLimitRepository.findById(findDocument.id!!)).isNotNull
    }
}
