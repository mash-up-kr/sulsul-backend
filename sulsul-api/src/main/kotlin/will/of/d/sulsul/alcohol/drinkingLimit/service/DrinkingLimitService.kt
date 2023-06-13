package will.of.d.sulsul.alcohol.drinkingLimit.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.common.AlcoholCalculator
import will.of.d.sulsul.alcohol.drinkingLimit.dto.request.PostDrinkingLimitReq
import will.of.d.sulsul.alcohol.drinkingLimit.dto.response.DrinkingLimitRes
import will.of.d.sulsul.alcohol.drinkingLimit.repository.DrinkingLimitRepository

@Service
class DrinkingLimitService(
    private val drinkingLimitRepository: DrinkingLimitRepository
) {
    fun save(saveDto: PostDrinkingLimitReq): DrinkingLimitRes {
        val alcoholAmount = AlcoholCalculator.calculateAlcohol(sojuCount = saveDto.sojuCount, beerCount = saveDto.beerCount)
        var document = drinkingLimitRepository.save(saveDto.toDocument(alcoholAmount = alcoholAmount))

        document = drinkingLimitRepository.save(document)
        return DrinkingLimitRes.of(document)
    }
}
