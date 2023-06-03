package will.of.d.sulsul.alcohol.enroll.dto.response

import will.of.d.sulsul.alcohol.enroll.domain.EnrollAlcohol

data class EnrollAlcoholResponseDto(
    val userId: String,
    val sojuCount: Int,
    val beerCount: Int,
    val alcoholAmount: Double
) {
    companion object {
        fun of(enrollAlcohol: EnrollAlcohol): EnrollAlcoholResponseDto {
            return EnrollAlcoholResponseDto(
                userId = enrollAlcohol.userId.toString(),
                sojuCount = enrollAlcohol.sojuCount,
                beerCount = enrollAlcohol.beerCount,
                alcoholAmount = enrollAlcohol.alcoholAmount
            )
        }
    }
}
