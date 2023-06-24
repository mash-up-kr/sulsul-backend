package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.PostDrinkingMeasurementReq
import java.time.Duration

@Service
class DrinkingMeasurementApplicationService(
    private val drinkingMeasurementService: DrinkingMeasurementService
) {
    fun measurement(kakaoUserId: Long, postDrinkingMeasurementReq: PostDrinkingMeasurementReq) {
        val (drinks, drinkingStartTime, drinkingEndTime) = postDrinkingMeasurementReq
        val totalGlasses = drinks.sumOf { it.glasses }
        val duration = Duration.between(drinkingStartTime, drinkingEndTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val drinkingDuration = "${hours}시간 ${minutes}분"
    }
}
