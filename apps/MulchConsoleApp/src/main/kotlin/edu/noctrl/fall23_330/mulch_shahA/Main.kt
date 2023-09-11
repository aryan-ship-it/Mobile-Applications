import edu.noctrl.fall23_330.mulch_shahA.CubicFootMulchPricer
import edu.noctrl.fall23_330.mulch_shahA.CubicYardMulchPricer
import edu.noctrl.fall23_330.mulch_shahA.MulchPricer
import edu.noctrl.fall23_330.mulch_shahA.PlantingBedDimensions
import kotlin.math.ceil

class MulchOrder ( val plantingBedDimensions: PlantingBedDimensions){
    private val plantingBedDimensionsList:MutableList<PlantingBedDimensions>  = mutableListOf()

    var mulchPricer: MulchPricer? = null
        set(value){
            field = value
        }
    fun addPlantingBedDimension(plantingBedDimensions: PlantingBedDimensions){
            plantingBedDimensionsList.add(plantingBedDimensions)
    }

    fun cubicYards(): Int {
        var cubicYards:Double = 0.0
        for(bed in plantingBedDimensionsList){
            cubicYards  += (bed.length * bed.width * (bed.depth/12.0))/27.0
        }
        return ceil(cubicYards).toInt()
    }
    fun cubicFeet(): Int {
//        var cubicFeet = cubicYards() * 27
//        return cubicFeet
        var cubicFeet:Double = 0.0
        for(bed in plantingBedDimensionsList){
            cubicFeet  += bed.length * bed.width * (bed.depth/12.0)
        }
        return ceil(cubicFeet).toInt()
    }

    fun printOrderDetails(){
        for(details in plantingBedDimensionsList){
            println("Planting Bed Dimensions : ${details.length.toInt()} x ${details.width.toInt()} x ${details.depth.toInt()}")
        }
        println("Total Cubic Yards: ${cubicYards()}")
        println("Total Cubic Feet : ${cubicFeet()}")
        println("Total Price: $ ${mulchPricer?.calculatePrice(cubicYards())} ")

    }


}



fun main(){
    val cubicYardPricer = CubicYardMulchPricer()
    val cubicFootPricer = CubicFootMulchPricer()

/*
* mulchorder1 : cubicFootPricer Test code in the case where Total Cubic Yards >= 10
* mulchorder2 : cubicYardPricer Test code in the case where Total no of bags >=3
*
*/

    val mulchOrder1 =  MulchOrder(PlantingBedDimensions(30.0,10.0,5.0))
    mulchOrder1.addPlantingBedDimension(PlantingBedDimensions(30.0,10.0,5.0))
    mulchOrder1.addPlantingBedDimension(PlantingBedDimensions(43.0,14.0,4.0))
    mulchOrder1.mulchPricer =  cubicYardPricer
    mulchOrder1.printOrderDetails()

    val mulchOrder2 =  MulchOrder(PlantingBedDimensions(30.0,10.0,5.0))
    mulchOrder2.addPlantingBedDimension(PlantingBedDimensions(30.0,10.0,5.0))
    mulchOrder2.addPlantingBedDimension(PlantingBedDimensions(43.0,14.0,4.0))
    mulchOrder2.mulchPricer =  cubicFootPricer
    mulchOrder2.printOrderDetails()

/*
* mulchorder3 : cubicFootPricer Test code in the case where No of bags <=5
* mulchorder4 : cubicYardPricer Test code in the case where Total cubic Yards <=3
*
*/

//    val mulchOrder3 =  MulchOrder(PlantingBedDimensions(5.0,2.0,2.0))
//    mulchOrder3.addPlantingBedDimension(PlantingBedDimensions(5.0,2.0,2.0))
//    mulchOrder3.addPlantingBedDimension(PlantingBedDimensions(7.0,5.0,1.0))
//    mulchOrder3.mulchPricer =  cubicFootPricer
//    mulchOrder3.printOrderDetails()


//    val mulchOrder4 =  MulchOrder(PlantingBedDimensions(10.0,10.0,5.0))
//    mulchOrder4.addPlantingBedDimension(PlantingBedDimensions(10.0,10.0,5.0))
//    mulchOrder4.addPlantingBedDimension(PlantingBedDimensions(15.0,5.0,3.0))
//    mulchOrder4.mulchPricer =  cubicYardPricer
//    mulchOrder4.printOrderDetails()


/*
* mulchorder5 :  cubicYardPricer Test code in the case where Total cubic Yards > 3 and <10
* mulchorder6 : cubicFootPricer Test code in the case where No of bags > 5 and no of bags <10
*  */


//    val mulchOrder5 =  MulchOrder(PlantingBedDimensions(20.0,15.0,4.0))
//    mulchOrder5.addPlantingBedDimension(PlantingBedDimensions(20.0,15.0,4.0))
//    mulchOrder5.addPlantingBedDimension(PlantingBedDimensions(15.0,7.0,4.0))
//    mulchOrder5.mulchPricer =  cubicYardPricer
//    mulchOrder5.printOrderDetails()

//    val mulchOrder6 =  MulchOrder(PlantingBedDimensions(6.0,5.0,3.0))
//    mulchOrder6.addPlantingBedDimension(PlantingBedDimensions(6.0,5.0,3.0))
//    mulchOrder6.addPlantingBedDimension(PlantingBedDimensions(8.0,7.0,2.0))
//    mulchOrder6.mulchPricer =  cubicFootPricer
//    mulchOrder6.printOrderDetails()




}