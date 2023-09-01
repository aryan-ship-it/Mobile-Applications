package example.myapp

open class Aquarium(var length:Int = 100,var width: Int = 20,var height: Int = 40) {

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

        println("Volume: $volume liters")
    }

}





