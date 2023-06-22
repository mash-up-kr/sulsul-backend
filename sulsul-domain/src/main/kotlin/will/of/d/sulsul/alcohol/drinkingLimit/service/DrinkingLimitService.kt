package will.of.d.sulsul.alcohol.drinkingLimit.service

import jakarta.validation.Valid
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.domain.DrinkingLimit
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository

@Service
class DrinkingLimitService(
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    fun save(@Valid drinkingLimit: DrinkingLimit): DrinkingLimit {
        // TODO : 알코올 계산기 구현하고 추가하기

//        val alcoholAmount = AlcoholCalculator.calculateAlcohol(sojuCount = body.sojuCount, beerCount = body.beerCount)
//        var document = drinkingLimitRepository.save(body.toDocument(alcoholAmount = alcoholAmount))

        var document = drinkingLimitRepository.save(drinkingLimit)

        return document
    }
}
