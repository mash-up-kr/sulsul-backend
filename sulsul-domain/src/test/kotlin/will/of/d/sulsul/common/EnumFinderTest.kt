package will.of.d.sulsul.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import will.of.d.sulsul.alcohol.Drink

class EnumFinderTest {

    @Test
    fun findBy() {
        var drink = Drink::drinkType findBy "소주"

        assertThat(drink).isNotNull
        assertThat(drink!!.drinkType).isEqualTo("소주")
        assertThat(drink!!.alcoholContent).isEqualTo(16.9)
    }
}
