package will.of.d.sulsul.alcohol.enroll.service

import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import will.of.d.sulsul.alcohol.enroll.domain.EnrollAlcohol
import will.of.d.sulsul.alcohol.enroll.repository.EnrollAlcoholRepository

@SpringBootTest
class EnrollAlcoholServiceTest(
    @Autowired
    private val enrollAlcoholRepository: EnrollAlcoholRepository
) {
    @Test
    @DisplayName("주량 등록 잘 되는지 확인")
    fun enrollAlcoholTest() {
        // given
        val randomUserId = "507f191e810c19729de860ea"
        val document = EnrollAlcohol(id = null, ObjectId(randomUserId), 1, 1)

        // when
        val findDocument = enrollAlcoholRepository.save(document)

        // then
        assertThat(findDocument.id).isNotNull
        assertThat(enrollAlcoholRepository.findById(findDocument.id!!)).isNotNull
    }
}
