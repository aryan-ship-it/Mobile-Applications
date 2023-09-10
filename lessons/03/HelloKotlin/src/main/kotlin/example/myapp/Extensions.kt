package example.myapp

fun String.hasSpaces(): Boolean{
    val found = this.indexOf(' ')
    return found != -1
}

