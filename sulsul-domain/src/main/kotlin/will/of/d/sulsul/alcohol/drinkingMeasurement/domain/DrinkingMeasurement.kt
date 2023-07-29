package will.of.d.sulsul.alcohol.drinkingMeasurement.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

class Drinks(
    val drinkType: String,
    val glasses: Int
) {
    companion object {
        fun from(drinkType: String, glasses: Int): Drinks {
            return Drinks(
                drinkType = drinkType,
                glasses = glasses
            )
        }
    }
}

@Document(collection = "drinking_measurement")
data class DrinkingMeasurement(
    @Id
    val id: ObjectId? = null,
    val userId: Long,
    val drinkingDuration: String,
    val alcoholCalorie: Int,
    val alcoholAmount: Int,
    val drinkCardImageUrl: String,
    val extraGlasses: Int,
    val averageAlcoholContent: Double,
    val drinks: List<Drinks>,
    val totalDrinkGlasses: Int,
    val drankAt: LocalDateTime
) {
    companion object {
        fun from(
            userId: Long,
            drinkingDuration: String,
            alcoholCalorie: Int,
            alcoholAmount: Int,
            extraGlasses: Int,
            drinkCardImageUrl: String,
            averageAlcoholContent: Double,
            totalDrinkGlasses: Int,
            drinks: List<Drinks>,
            drankAt: LocalDateTime
        ): DrinkingMeasurement {
            return DrinkingMeasurement(
                userId = userId,
                drinkingDuration = drinkingDuration,
                alcoholCalorie = alcoholCalorie,
                extraGlasses = extraGlasses,
                drinkCardImageUrl = drinkCardImageUrl,
                averageAlcoholContent = averageAlcoholContent,
                totalDrinkGlasses = totalDrinkGlasses,
                drinks = drinks,
                alcoholAmount = alcoholAmount,
                drankAt = drankAt
            )
        }
    }
}
