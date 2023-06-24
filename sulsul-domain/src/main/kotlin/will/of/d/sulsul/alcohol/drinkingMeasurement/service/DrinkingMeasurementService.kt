package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository

@Service
class DrinkingMeasurementService(
    private val drinkingMeasurementRepository: DrinkingMeasurementRepository
) {
    fun save(drinkingMeasurement: DrinkingMeasurement): DrinkingMeasurement {
        return drinkingMeasurementRepository.save(drinkingMeasurement)
    }
}
