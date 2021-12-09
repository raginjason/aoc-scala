package raginjason.aoc2021

import scala.collection.immutable.StringOps

object Day1 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day1.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(filterIncreasingMeasurements(parseMeasurements(input)).length)
  }

  def parseMeasurements(input: String): Seq[Int] = (input: StringOps).lines.map(_.toInt).toSeq

  def filterIncreasingMeasurements(measurements: Seq[Int]): Seq[Int] = {
    // Assuming no negative values, so use Int.MinValue as a sentinel value
    val a = Int.MinValue +: measurements

    val categorizedMeasurements = a.sliding(2).collect {
      case Seq(x, y) if x == Int.MinValue => Tuple2(y, "N/A")
      case Seq(x, y) if x < y => Tuple2(y, "increased")
      case Seq(x, y) if x > y => Tuple2(y, "decreased")
    }.toList

    categorizedMeasurements.filter(_._2 == "increased").map(_._1)
  }
}
