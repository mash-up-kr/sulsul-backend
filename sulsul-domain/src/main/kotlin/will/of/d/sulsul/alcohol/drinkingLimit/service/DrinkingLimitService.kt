package will.of.d.sulsul.alcohol.drinkingLimit.service

import jakarta.validation.Valid
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository

@Service
class DrinkingLimitService(
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    fun save(@Valid drinkingLimit: DrinkingLimit): DrinkingLimit {
        val document = drinkingLimitRepository.save(drinkingLimit)

        return document
    }

    fun findByUserId(kakaoUserId: Long): DrinkingLimit {
        return drinkingLimitRepository.findFirstByKakaoUserIdOrderByCreatedAtDesc(kakaoUserId) ?: throw NotFoundException()
    }

    fun getInShare(@Valid document: DrinkingLimit): DrinkingLimit {
        return document
    }
}
