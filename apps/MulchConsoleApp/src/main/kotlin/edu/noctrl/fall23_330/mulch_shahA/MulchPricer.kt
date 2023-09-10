package edu.noctrl.fall23_330.mulch_shahA

interface MulchPricer {
    fun calculatePrice(cubicYards: Int): Double
}

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

class CubicFootMulchPricer: MulchPricer{

    override fun calculatePrice(cubicYards: Int): Double {
        val cubicFeet = cubicYards * 3
        var price : Double = 0.0
        val noOfbags:Int = cubicFeet / 2
        if(noOfbags <= 5){
            price = noOfbags * 3.97
        }
        else if(noOfbags > 5 && noOfbags < 10){
            price = noOfbags * 3.47
        }
        else if(noOfbags >= 10){
            price = noOfbags * 2.97
        }
        return price

    }



}


