package will.of.d.sulsul.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import will.of.d.sulsul.drink.domain.Drink

class EnumFinderTest {

    @Test
    fun findBySuccess() {
        val drink = Drink::type findBy "소주"

        assertThat(drink).isNotNull
        assertThat(drink!!.type).isEqualTo("소주")
        assertThat(drink!!.alcoholPercentage).isEqualTo(16.9)
    }

    @Test
    fun findByFail() {
        val drink = Drink::type findBy "강아지"

        assertThat(drink).isNull()
    }
}
