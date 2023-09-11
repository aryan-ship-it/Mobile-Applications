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


}