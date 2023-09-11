package edu.noctrl.fall23_330.mulch_shahA

class CubicYardMulchPricer : MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        var price : Double = 0.0
        if(cubicYards <= 3){
            price = cubicYards* 33.50
        }
        else if(cubicYards > 3  && cubicYards < 10){
            price = cubicYards * 31.50
        }
        else if(cubicYards >= 10){
            price = cubicYards * 29.50
        }
        return price
    }

}