data class Measurement(val value: Int, val increased: Boolean?)
fun List<Int>.toMeasurements() = mapIndexed { index, measurement ->
    val previousMeasurement = getOrNull(index - 1)

    Measurement(measurement, if (previousMeasurement != null) measurement > previousMeasurement else null)
}

fun main() {
    fun part1(input: List<String>) = input
        .map { it.toInt() }
        .toMeasurements()
        .count { it.increased == true }

    fun part2(input: List<String>) = input
        .map { it.toInt() }
        .windowed(3)
        .map { it.sum() }.toMeasurements().count { it.increased == true }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
