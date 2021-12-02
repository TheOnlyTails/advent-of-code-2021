enum class Direction {
    FORWARD, UP, DOWN
}

data class Position(val horizontal: Int, val depth: Int, val aim: Int) {
    fun sink(amount: Int) = Position(horizontal, depth + amount, aim)
    fun rise(amount: Int) = Position(horizontal, depth - amount, aim)
    fun swim(amount: Int) = Position(horizontal + amount, depth, aim)
    fun aimUp(amount: Int) = Position(horizontal, depth, aim + amount)
    fun aimDown(amount: Int) = Position(horizontal, depth, aim - amount)

    companion object {
        val initial = Position(0, 0, 0)
    }
}

data class Vector(val direction: Direction, val distance: Int)

fun String.asDirection() = when (this) {
    "up" -> Direction.UP
    "down" -> Direction.DOWN
    "forward" -> Direction.FORWARD
    else -> throw IllegalArgumentException("Unknown direction: $this")
}

fun String.parseVector(): Vector {
    val regexMatches = """(forward|up|down) (\d)""".toRegex(RegexOption.IGNORE_CASE).findAll(this)
    val (direction, distance) = regexMatches.toList()[0].destructured

    return Vector(direction.asDirection(), distance.toInt())
}

fun List<Vector>.calculateCoordinates() = fold(Position.initial) { pos, vector ->
    when (vector.direction) {
        Direction.FORWARD -> pos.swim(vector.distance)
        Direction.UP -> pos.rise(vector.distance)
        Direction.DOWN -> pos.sink(vector.distance)
    }
}

fun List<Vector>.calculateCoordinates2() = fold(Position.initial) { pos, (direction, distance) ->
    when (direction) {
        Direction.FORWARD -> pos.swim(distance).sink(pos.aim * distance)
        Direction.UP -> pos.aimDown(distance)
        Direction.DOWN -> pos.aimUp(distance)
    }
}

fun main() {
    fun part1(input: List<String>) = input
        .map { it.parseVector() }
        .calculateCoordinates()
        .run { depth * horizontal }

    fun part2(input: List<String>) = input
        .map { it.parseVector() }
        .calculateCoordinates2()
        .run { depth * horizontal }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
