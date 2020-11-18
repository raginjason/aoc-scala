package raginjason.aoc2019

object Day1 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day1.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(Part1.totalComputedFuel(parseMasses(input)))
  }

  def parseMasses(input: String): Seq[Int] = input.split("\n").map(_.toInt).toSeq

  trait Part {
    def computeFuel(mass: Int): Int

    def totalComputedFuel(masses: Seq[Int]): Int = masses.map(computeFuel).sum
  }

  object Part1 extends Part {
    override def computeFuel(mass: Int): Int = mass / 3 - 2
  }
}
