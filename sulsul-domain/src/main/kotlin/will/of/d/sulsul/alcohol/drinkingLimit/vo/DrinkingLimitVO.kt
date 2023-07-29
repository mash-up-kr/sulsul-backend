package will.of.d.sulsul.alcohol.drinkingLimit.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Min
import will.of.d.sulsul.common.findBy
import will.of.d.sulsul.drink.domain.Drink

data class DrinkingLimitVO(
    val drink: Drink,
    @field:Min(value = 0)
    val glass: Int
) {

    @AssertTrue
    fun isValidDrinkType(): Boolean {
        return Drink::type findBy drink != null
    }

    companion object {
        fun from(drink: Drink, glass: Int): DrinkingLimitVO {
            return DrinkingLimitVO(
                drink = drink,
                glass = glass
            )
        }
    }

    @JsonIgnore
    fun getAlcoholAmount() = drink.alcoholAmountPerGlass * glass
}
