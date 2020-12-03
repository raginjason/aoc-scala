package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day3 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day3.txt")).mkString.trim
  val slopes: Seq[Slope] = Seq(
    Slope(1, 1),
    Slope(3, 1),
    Slope(5, 1),
    Slope(7, 1),
    Slope(1, 2),
  )

  def main(args: Array[String]): Unit = {
    println(countTrees(parsePuzzleMap(input), 3, 1))
    println(multiplyTreeCount(countTreesAllSlopes(parsePuzzleMap(input), slopes)))
  }

  def parsePuzzleMap(input: String): Seq[Stream[Boolean]] = (input: StringOps).lines.map(x => parsePuzzleMapRow(x)).toSeq

  def parsePuzzleMapRow(input: String): Stream[Boolean] = {
    val r: Seq[Boolean] = input.collect {
      case ch if ch == '#' => true
      case ch if ch == '.' => false
      case _ => false
    }
    lazy val row: Stream[Boolean] = r.toStream #::: row
    row
  }

  def countTrees(puzzleMap: Seq[Stream[Boolean]], rightDistance: Int, downDistance: Int): Int = {
    var offset: Int = 0
    var treeCount: Int = 0
    for (row <- puzzleMap.zipWithIndex.collect {
      case (x, i) if i % downDistance == 0 => x
    }.tail) {
      offset += rightDistance
      if (row.slice(offset, offset + 1).head) {
        treeCount += 1
      }
    }
    treeCount
  }

  def countTreesAllSlopes(puzzleMap: Seq[Stream[Boolean]], slopes: Seq[Slope]): Seq[Int] = {
    slopes.map(x => countTrees(puzzleMap, x.rightDistance, x.downDistance))
  }

  def multiplyTreeCount(treeCounts: Seq[Int]): BigInt = treeCounts.map(BigInt(_)).product

  case class Slope(rightDistance: Int, downDistance: Int)

}
