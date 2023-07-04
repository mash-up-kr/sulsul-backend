package will.of.d.sulsul.alcohol.drinkingLimit.service

import jakarta.validation.Valid
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO

@Service
@Validated
class DrinkingLimitService(
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    fun save(@Valid drinkingLimitVO: DrinkingLimitVO): DrinkingLimit {
        val document = DrinkingLimit.from(drinkingLimitVO = drinkingLimitVO)
        val savedDocument = drinkingLimitRepository.save(document)

        return savedDocument
    }

    fun findByUserId(kakaoUserId: Long): DrinkingLimit {
        return drinkingLimitRepository.findFirstByKakaoUserIdOrderByCreatedAtDesc(kakaoUserId) ?: throw NotFoundException()
    }

    fun getInShare(@Valid drinkingLimitVO: DrinkingLimitVO): DrinkingLimit {
        return DrinkingLimit.from(drinkingLimitVO = drinkingLimitVO)
    }
}
