package will.of.d.sulsul.alcohol.common

class AlcoholCalculator {
    companion object {
        // TODO : 주종 추가되면 로직 추가
        // TODO : 알코올 계산 로직 변경하기

        fun calculateAlcohol(sojuCount: Int, beerCount: Int): Double {
            var alcoholAmount: Double = 0.0

            alcoholAmount += sojuCount * 15
            alcoholAmount += beerCount * 5

            return alcoholAmount
        }
    }
}
