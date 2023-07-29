package will.of.d.sulsul.alcohol.drinkingMeasurement.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.drinkingMeasurement.domain.DrinkingMeasurement
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.request.DrinkingMeasurementReq
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementListRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.dto.response.DrinkingMeasurementSummaryRes
import will.of.d.sulsul.alcohol.drinkingMeasurement.vo.DrinkingMeasurementVO
import will.of.d.sulsul.exception.ReportNotFoundException

@Service
class DrinkingMeasurementApplicationService(
    private val drinkingMeasurementService: DrinkingMeasurementService
) {
    fun measurement(userId: Long, drinkingMeasurementReq: DrinkingMeasurementReq): DrinkingMeasurementRes {
        val (drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses) = drinkingMeasurementReq
        val drinkingMeasurementVO = DrinkingMeasurementVO.from(userId, drinks, drinkingStartTime, drinkingEndTime, totalDrinkGlasses)

        val document = drinkingMeasurementService.measurement(drinkingMeasurementVO)

        return DrinkingMeasurementRes.of(document)
    }

    fun getMeasurementReport(reportId: String): DrinkingMeasurementRes {
        return drinkingMeasurementService.findById(reportId)?.let { DrinkingMeasurementRes.of(it) } ?: throw ReportNotFoundException("Report not found with id: $reportId")
    }

    fun getMeasurementReportList(userId: Long): DrinkingMeasurementListRes {
        val drinkingMeasurementList: List<DrinkingMeasurement> = drinkingMeasurementService.findAllByUserId(userId)
        return DrinkingMeasurementListRes(
            drinkingMeasurementList.map { DrinkingMeasurementSummaryRes.of(it) }
        )
    }
}
