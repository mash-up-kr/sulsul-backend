package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import will.of.d.sulsul.SharedContext
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.Drinks
import will.of.d.sulsul.alcohol.drinkingMeasurement.repository.DrinkingMeasurementRepository
import java.time.LocalDateTime

class DrinkingMeasurementServiceTest(
    private val drinkingMeasurementService: DrinkingMeasurementService,
    private val drinkingMeasurementRepository: DrinkingMeasurementRepository
) : SharedContext() {

    @BeforeEach
    fun createReport() {
        val drinks: List<Drinks> = listOf(Drinks(drinkType = "소주", glasses = 5))

        for (i in 1..5) {
            drinkingMeasurementService.save(
                DrinkingMeasurement(
                    userId = 130L,
                    drinkingDuration = "3시간 20분",
                    alcoholCalorie = 100,
                    alcoholAmount = 1600,
                    averageAlcoholContent = 20.0,
                    drinks = drinks,
                    totalDrinkGlasses = 15,
                    drankAt = LocalDateTime.now(),
                    extraGlasses = 4,
                    drinkCardImageUrl =
                    "",
                    subTitle = ""
                )
            )
        }
    }

    @AfterEach
    fun deleteReport() {
        drinkingMeasurementRepository.deleteAll()
    }

    @Test
    fun getReportList() {
        val drinkingMeasurementList: List<DrinkingMeasurement> = drinkingMeasurementService.findAllByUserId(130L)
        Assertions.assertThat(drinkingMeasurementList.size).isEqualTo(5)
    }

    @Test
    fun getEmptyReportList() {
        val drinkingMeasurementList: List<DrinkingMeasurement> = drinkingMeasurementService.findAllByUserId(1L)
        Assertions.assertThat(drinkingMeasurementList.size).isEqualTo(0)
    }
}
