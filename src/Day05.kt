typealias Coordinate = Pair<Int, Int>

fun main() {
    // my attempt at day 5 - doesn't work
    fun part1(input: List<String>): Int? {
        val streams: List<Pair<Coordinate, Coordinate>> = input.map {
            val (start, end) = it.split(" -> ").map { pair ->
                val (x, y) = pair.split(",").map { coordinate -> coordinate.toInt() }
                x to y
            }
            start to end
        }

        val coordinates = mutableSetOf<Coordinate>()
        (0..9).forEach { x ->
            (0..9).forEach { y -> coordinates += x to y }
        }

        return coordinates.groupBy {
            streams.count { line ->
                it.first in line.first.first..line.second.first && it.second in line.first.second..line.second.second
            }
        }[2]?.count()
    }

    fun part2(input: List<String>) = input

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
