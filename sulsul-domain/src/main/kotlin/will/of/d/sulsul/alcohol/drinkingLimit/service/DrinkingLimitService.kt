package will.of.d.sulsul.alcohol.drinkingLimit.service

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimitEntity
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO

@Service
@Validated
class DrinkingLimitService(
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    fun save(kakaoUserId: Long, drinkingLimitVO: DrinkingLimitVO): DrinkingLimitVO {
        val document = DrinkingLimitEntity.from(kakaoUserId = kakaoUserId, drinkingLimitVO = drinkingLimitVO)
        return drinkingLimitRepository.save(document).toVO()
    }

    fun findByUserId(kakaoUserId: Long): DrinkingLimitVO {
        return drinkingLimitRepository.findFirstByKakaoUserIdOrderByCreatedAtDesc(kakaoUserId)?.toVO()
            ?: throw NotFoundException()
    }

    fun getAlcoholAmount(kakaoUserId: Long): Int {
        val document = drinkingLimitRepository.findFirstByKakaoUserIdOrderByCreatedAtDesc(kakaoUserId)
        return document!!.alcoholAmount
    }
}
