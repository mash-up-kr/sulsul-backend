package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingLimit.service.DrinkingLimitService
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkListReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementListRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementSummaryRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.exception.ReportNotFoundException
import will.of.d.sulsul.title.domain.Title

@Service
class DrinkingMeasurementApplicationService(
    private val drinkingMeasurementService: DrinkingMeasurementService,
    private val drinkingLimitService: DrinkingLimitService
) {
    fun measurement(drinkingMeasurementVO: DrinkingMeasurementVO): DrinkingMeasurementRes {
        val document = drinkingMeasurementService.measurement(drinkingMeasurementVO)

        return DrinkingMeasurementRes.of(document)
    }

    fun getMeasurementReport(reportId: String): DrinkingMeasurementRes {
        return drinkingMeasurementService.findById(reportId)?.let { DrinkingMeasurementRes.of(it) }
            ?: throw ReportNotFoundException("Report not found with id: $reportId")
    }

    fun getMeasurementReportList(userId: Long): DrinkingMeasurementListRes {
        val drinkingMeasurementList: List<DrinkingMeasurement> = drinkingMeasurementService.findAllByUserId(userId)
        return DrinkingMeasurementListRes(
            drinkingMeasurementList.map { DrinkingMeasurementSummaryRes.of(it) }.sortedByDescending { it.drankAt }
        )
    }

    fun calculateAlcoholAmount(body: DrinkListReq): Int {
        var totalAlcoholAmount = 0

        body.drinks.map {
            val drink = Drink::type findBy it.drinkType

            totalAlcoholAmount += drink!!.alcoholAmountPerGlass * it.glass
        }

        return totalAlcoholAmount
    }

    fun calculateTitle(totalAlcoholAmount: Int): Title {
        return Title.defineTitleByAlcoholAmount(totalAlcoholAmount)
    }

    fun calculateDrunkenFlag(totalAlcoholAmount: Int, userId: Long): Boolean {
        val alcoholAmountLimit = drinkingLimitService.getAlcoholAmount(userId)

        if (totalAlcoholAmount >= alcoholAmountLimit) return true
        return false
    }
}
