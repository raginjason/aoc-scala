package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day3 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day3.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(countTrees(parsePuzzleMap(input), 3, 1))
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
      case (x, i) if i == 1 => x
      case (x, i) if (i + 1) % downDistance == 0 => x
    }.tail) {
      offset += rightDistance
      if (row.slice(offset, offset + 1).head) {
        treeCount += 1
      }
    }
    treeCount
  }

}
