package will.of.d.sulsul.alcohol.drinkingMeasurement.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement

interface DrinkingMeasurementRepository : MongoRepository<DrinkingMeasurement, ObjectId> {
    fun findAllByUserId(userId: Long): List<DrinkingMeasurement>
}
