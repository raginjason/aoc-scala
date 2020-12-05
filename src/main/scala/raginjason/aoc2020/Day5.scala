package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day5 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day5.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(allSeatIds(parseBoardingPasses(input)).max)
    println(mySeatId(allSeatIds(parseBoardingPasses(input))))
  }

  def mySeatId(seatIds: Seq[Int]): Int = {
    val sortedSeatIds = seatIds.sorted
    val seatIdRange = sortedSeatIds.head to sortedSeatIds.last

    sortedSeatIds.zip(seatIdRange).collect {
      case (a, b) if a != b => b
    }.head
  }

  def allSeatIds(boardingPasses: Seq[BoardingPass]): Seq[Int] = {
    boardingPasses.map(_.seatId)
  }

  def parseBoardingPasses(input: String): Seq[BoardingPass] = {
    (input: StringOps).lines.map(BoardingPass).toSeq
  }

  case class BoardingPass(input: String) {
    val rawRow: String = input.take(7)
    val rawColumn: String = input.takeRight(3)
    val seatId: Int = row * 8 + column

    def row: Int = Integer.parseInt(rawRow.collect {
      case 'F' => 0
      case 'B' => 1
    }.mkString, 2)

    def column: Int = Integer.parseInt(rawColumn.collect {
      case 'L' => 0
      case 'R' => 1
    }.mkString, 2)
  }

}
