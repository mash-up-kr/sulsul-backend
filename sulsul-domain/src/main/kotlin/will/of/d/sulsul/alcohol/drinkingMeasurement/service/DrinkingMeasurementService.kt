package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
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

    fun findById(id: String): DrinkingMeasurement? {
        return drinkingMeasurementRepository.findByIdOrNull(ObjectId(id))
    }
}
