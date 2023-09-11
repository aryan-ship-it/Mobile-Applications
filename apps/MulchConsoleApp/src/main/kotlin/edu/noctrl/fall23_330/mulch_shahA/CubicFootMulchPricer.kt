package edu.noctrl.fall23_330.mulch_shahA

import kotlin.math.ceil

class CubicFootMulchPricer: MulchPricer {

    override fun calculatePrice(cubicYards: Int): Double {
        val cubicFeet:Double = cubicYards * 27.0
        var price : Double = 0.0
        val noOfbags = ceil(cubicFeet / 2)

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