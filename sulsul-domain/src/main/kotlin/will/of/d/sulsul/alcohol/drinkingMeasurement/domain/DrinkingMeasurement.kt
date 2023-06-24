package will.of.d.sulsul.alcohol.drinkingMeasurement.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

enum class Drinks(
    val drinkType: String,
    val glasses: Int
)

@Document(collation = "drinking_measurement")
class DrinkingMeasurement(
    @Id
    val id: ObjectId? = null,
    val userId: ObjectId,
    val drinkingDuration: String,
    val alcoholCalorie: Int,
    val averageAlcoholContent: Double,
    val drinks: Drinks,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun from(
            userId: ObjectId,
            drinkingDuration: String,
            alcoholCalorie: Int,
            averageAlcoholContent: Double,
            drinks: Drinks
        ): DrinkingMeasurement {
            return DrinkingMeasurement(
                userId = userId,
                drinkingDuration = drinkingDuration,
                alcoholCalorie = alcoholCalorie,
                averageAlcoholContent = averageAlcoholContent,
                drinks = drinks
            )
        }
    }
}
