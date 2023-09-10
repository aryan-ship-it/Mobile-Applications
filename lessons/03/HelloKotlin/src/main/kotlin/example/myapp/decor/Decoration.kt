package example.myapp.decor

data class Decoration (val rocks : String){

}

data class Decoration2(val rocks: String, val wood: String, val diver: String){

}

fun makeDecorations(){
    val decoration1 = Decoration("granite")
    println(decoration1)

    val decoration2 = Decoration("slate")
    println(decoration2)

    val decoration3 = Decoration("slate")
    println(decoration3)

    val d5 = Decoration2("crystal", "wood", "diver")
    println(d5)

    val(rock, _, diver) = d5
    println(rock)
    println(diver)

    println(decoration1.equals(decoration2))
    println(decoration3.equals(decoration2))
}

enum class Color(val rgb:Int){
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF)
}

enum class Direction(val degrees: Int){
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}

class Choice{
    companion object {
        var name : String = "lyric"
        fun showDescription(name:String) = println("My favorite $name")
    }
}

fun main() {
    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")
}

object Constants{
    const val CONSTANT2 = "object constant"
}
val foo = Constants.CONSTANT2

class MyClass{
    companion object{
        const val Constant3 = "constant in companion"
    }
}

