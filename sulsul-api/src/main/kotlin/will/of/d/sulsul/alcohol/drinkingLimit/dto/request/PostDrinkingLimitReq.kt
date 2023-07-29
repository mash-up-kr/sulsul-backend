package will.of.d.sulsul.alcohol.drinkingLimit.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.AssertTrue
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink

@Schema(description = "주량등록 시, 보내야하는 Request Body")
data class PostDrinkingLimitReq(
    @Schema(description = "술의 종류", allowableValues = ["소주", "와인", "맥주", "위스키", "고량주"])
    val drinkType: String,

    @Schema(description = "해당 주종을 몇 잔 마실 수 있는지 나타내는 필드")
    val glass: Int
) {
    @JsonIgnore
    @AssertTrue
    fun isValidDrinkType() = (Drink::type findBy this.drinkType)
        ?.let { true }
        ?: false
}
