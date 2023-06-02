package will.of.d.sulsul.alcohol.enroll.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.alcohol.common.AlcoholCalculator
import will.of.d.sulsul.alcohol.enroll.dto.request.EnrollAlcoholSaveDto
import will.of.d.sulsul.alcohol.enroll.dto.response.EnrollAlcoholResponseDto
import will.of.d.sulsul.alcohol.enroll.repository.EnrollAlcoholRepository

@Service
class EnrollAlcoholService(
    private val enrollAlcoholRepository: EnrollAlcoholRepository
) {
    fun save(saveDto: EnrollAlcoholSaveDto): EnrollAlcoholResponseDto {
        val alcoholAmount = AlcoholCalculator.calculateAlcohol(sojuCount = saveDto.sojuCount, beerCount = saveDto.beerCount)
        var document = enrollAlcoholRepository.save(saveDto.toDocument(alcoholAmount = alcoholAmount))

        document = enrollAlcoholRepository.save(document)
        return EnrollAlcoholResponseDto.of(document)
    }
}
