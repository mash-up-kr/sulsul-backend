package will.of.d.sulsul.drink.service

import org.springframework.stereotype.Service
import will.of.d.sulsul.drink.domain.Drink
import will.of.d.sulsul.log.Logger

@Service
class DrinkService {

    companion object : Logger

    fun getDrinks(): Array<Drink> {
        return Drink.values().also { log.debug("Return drinks.") }
    }
}
