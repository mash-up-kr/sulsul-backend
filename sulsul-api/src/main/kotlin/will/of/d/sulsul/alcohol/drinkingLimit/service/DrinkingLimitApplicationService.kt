package will.of.d.sulsul.alcohol.drinkingLimit.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.vo.DrinkingLimitVO
import will.of.d.sulsul.drink.controller.AlcoholApplicationService
import will.of.d.sulsul.log.Logger
import will.of.d.sulsul.title.domain.Title
import will.of.d.sulsul.user.User
import will.of.d.sulsul.user.UserApplicationService

@Service
class DrinkingLimitApplicationService(
    private val userApplicationService: UserApplicationService,
    private val drinkingLimitService: DrinkingLimitService,
    private val alcoholApplicationService: AlcoholApplicationService
) {

    companion object : Logger

    fun saveIfLogin(user: User?, drinkingLimit: DrinkingLimitVO): List<DrinkingLimitVO> {
        val alcohol = alcoholApplicationService.calculateAlcohol(drinkingLimit)
        val drinkingLimits = alcoholApplicationService.calculateDrinkingLimits(alcohol)
        user?.let {
            drinkingLimitService.save(it.kakaoUserId, drinkingLimit)
            userApplicationService.registerDrinkingLimitAndTitle(user, drinkingLimit, Title.defineTitleByAlcoholAmount(alcohol))
        }?.also { log.debug("Save drinking limit. ${user.kakaoUserId}") }
        return drinkingLimits
    }

    fun get(user: User): List<DrinkingLimitVO> {
        val drinkingLimit = drinkingLimitService.findByUserId(user.kakaoUserId)
        return convertToOtherDrinks(drinkingLimit)
    }

    fun convertToOtherDrinks(drinkingLimit: DrinkingLimitVO): List<DrinkingLimitVO> {
        val alcohol = alcoholApplicationService.calculateAlcohol(drinkingLimit)
        return alcoholApplicationService.calculateDrinkingLimits(alcohol)
    }
}
