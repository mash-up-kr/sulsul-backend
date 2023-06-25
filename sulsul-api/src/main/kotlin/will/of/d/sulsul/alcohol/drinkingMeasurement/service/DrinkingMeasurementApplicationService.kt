package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.Drink
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkingMeasurementReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementRes
import will.of.d.sulsul.exception.ReportNotFoundException
import java.time.Duration

@Service
class DrinkingMeasurementApplicationService(
    private val drinkingMeasurementService: DrinkingMeasurementService
) {
    fun measurement(kakaoUserId: Long, drinkingMeasurementReq: DrinkingMeasurementReq): DrinkingMeasurementRes {
        val (drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses) = drinkingMeasurementReq
        val duration = Duration.between(drinkingStartTime, drinkingEndTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val drinkingDuration = "${hours}시간 ${minutes}분"
        var averageAlcoholContent = 0.0
        var totalCalorie = 0

        drinks.forEach() {
            when (it.drinkType) {
                Drink.SOJU.name -> {
                    averageAlcoholContent += Drink.SOJU.alcoholContent
                    totalCalorie += it.glasses * Drink.SOJU.calorie
                }
                Drink.BEER.name -> {
                    averageAlcoholContent += Drink.BEER.alcoholContent
                    totalCalorie += it.glasses * Drink.BEER.calorie
                }
                Drink.WINE.name -> {
                    averageAlcoholContent += Drink.WINE.alcoholContent
                    totalCalorie += it.glasses * Drink.WINE.calorie
                }
                Drink.WHISKY.name -> {
                    averageAlcoholContent += Drink.WHISKY.alcoholContent
                    totalCalorie += it.glasses * Drink.WHISKY.calorie
                }
                Drink.KAOLIANG.name -> {
                    averageAlcoholContent += Drink.KAOLIANG.alcoholContent
                    totalCalorie += it.glasses * Drink.KAOLIANG.calorie
                }
                else -> {
                    averageAlcoholContent += 0.0
                }
            }
        }
        averageAlcoholContent /= drinks.size

        val document = DrinkingMeasurement.from(kakaoUserId, drinkingDuration, totalCalorie, averageAlcoholContent, totalDrinkGlasses, drinks.map { Drinks.from(it.drinkType, it.glasses) })

        return DrinkingMeasurementRes.of(drinkingMeasurementService.save(document))
    }

    fun getMeasurementReport(reportId: String): DrinkingMeasurementRes {
        return drinkingMeasurementService.findById(reportId)?.let { DrinkingMeasurementRes.of(it) } ?: throw ReportNotFoundException("Report not found with id: $reportId")
    }
}
