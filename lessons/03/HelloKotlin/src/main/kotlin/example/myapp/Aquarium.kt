package example.myapp

import kotlin.math.PI

open class Aquarium(var length:Int = 100,var width: Int = 20,open var height: Int = 40) {

    init{
        println("aquarium initialized")
    }

//    init {
//        println("Volume : ${width * length * height/1000} liters ")
//    }

    constructor(numberOfFish:Int): this() {
        val tank = numberOfFish * 2000 * 1.1
        height = (tank/(length* width)).toInt()

    }

    open var volume: Int
        get() = width * height * length / 1000

        set(value) {
            height = (value * 1000)/(width *length)
        }

    open val shape = "rectangle"

    open var water : Double = 0.0
        get() = volume * 0.9



    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")

        println("Volume: $volume liters Water : $water liters (${water /volume * 100.0}% full)")
    }


}


class TowerTank (override var height: Int, var diameter: Int) :Aquarium(height = height, width = diameter, length = diameter){
    override var volume: Int
        get() = (width/2 * length/2 * height/1000 * PI).toInt()
        set(value){
            height = ((value*1000/ PI)/(width/2 * length/2)).toInt()
        }

    override var water = volume * 0.8
    override val shape = "cylinder"


}





abstract class AquariumFish : FishAction {
    abstract val color: String
    override fun eat() = println("yum")
}

interface FishColor{
    val color: String
}


//class Shark: AquariumFish(){
//    override val color = "grey"
//}

//class Plecostomus: AquariumFish(){
//    override val color = "gold"
//
//}

interface FishAction{
    fun eat()
}



class Shark : FishAction, FishColor {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus :FishAction, FishColor by GoldColor {
    override val color = "gold"
    override fun eat(){
        println("eat algae")
    }
}

object GoldColor : FishColor {
    override val color = "gold"
}
