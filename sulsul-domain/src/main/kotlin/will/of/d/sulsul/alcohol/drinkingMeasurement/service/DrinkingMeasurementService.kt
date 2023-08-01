package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.drink.service.AlcoholService
import will.of.d.sulsul.title.domain.Title
import java.time.Duration
import java.time.LocalDateTime

@Service
class DrinkingMeasurementService(
    private val drinkingMeasurementRepository: DrinkingMeasurementRepository,
    private val drinkingLimitService: DrinkingLimitService,
    private val alcoholService: AlcoholService
) {
    fun save(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurement {
        return drinkingMeasurementRepository.save(drinkingMeasurement)
    }

    fun findById(id: String): DrinkingMeasurement? {
        return drinkingMeasurementRepository.findByIdOrNull(ObjectId(id))
    }

    fun measurement(drinkingMeasurementVO: DrinkingMeasurementVO): DrinkingMeasurement {
        val (userId, drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses) = drinkingMeasurementVO

        val drinkingDuration = calculateDurationTime(drinkingStartTime, drinkingEndTime)

        val drinkingMeasurementInfo = alcoholService.calculateDrinkingMeasurementInfo(drinks)
        val extraGlasses = calculateExtraGlasses(
            myAlcoholAmount = drinkingLimitService.getAlcoholAmount(kakaoUserId = userId),
            todayAlcoholAmout = drinkingMeasurementInfo.totalAlcoholAmount
        )

        val alcohol = alcoholService.calculateAlcohol(drinkingMeasurementVO.drinks)
        val subTitle = Title.defineTitleByAlcoholAmount(alcohol).subText

        val drinkCardImageUrl = alcoholService.defineDrinkCardImageUrl(drinks)

        val document = DrinkingMeasurement.from(
            userId = userId,
            drinkingDuration = drinkingDuration,
            alcoholCalorie = drinkingMeasurementInfo.totalCalorie,
            alcoholAmount = drinkingMeasurementInfo.totalAlcoholAmount,
            extraGlasses = extraGlasses,
            drinkCardImageUrl = drinkCardImageUrl,
            averageAlcoholContent = drinkingMeasurementInfo.averageAlcoholPercent,
            totalDrinkGlasses = totalDrinkGlasses,
            drinks = drinks.map { Drinks.from(it.drinkType, it.glasses) },
            drankAt = drinkingStartTime,
            subTitle = subTitle
        )

        return drinkingMeasurementRepository.save(document)
    }

    private fun calculateDurationTime(drinkingStartTime: LocalDateTime, drinkingEndTime: LocalDateTime): String {
        val duration = Duration.between(drinkingStartTime, drinkingEndTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60

        return "${hours}시간 ${minutes}분"
    }

    fun findAllByUserId(userId: Long): List<DrinkingMeasurement> {
        return drinkingMeasurementRepository.findAllByUserId(userId)
    }

    fun calculateExtraGlasses(myAlcoholAmount: Int, todayAlcoholAmout: Int): Int {
        val diffAlcoholAmount = todayAlcoholAmout - myAlcoholAmount
        if (diffAlcoholAmount < 0) return 0

        // 소주 기준으로 카운팅
        return diffAlcoholAmount / Drink.SOJU.alcoholAmountPerGlass
    }
}

data class DrinkingMeasurementInfo(
    val averageAlcoholPercent: Double,
    val totalCalorie: Int,
    val totalAlcoholAmount: Int
)
