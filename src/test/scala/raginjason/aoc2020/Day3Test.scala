package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day3._
import raginjason.aoc2020.Day3Test._

class Day3Test extends Suites(
  new Part1Test
)

object Day3Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val samplePuzzleMapRow: String = "..##......."
    test(s"parsePuzzleMapRow() against sample") {
      assert(parsePuzzleMapRow(samplePuzzleMapRow).take(5).toSeq == Seq(false, false, true, true, false))
      assert(parsePuzzleMapRow(samplePuzzleMapRow).drop(11).take(5).toSeq == Seq(false, false, true, true, false))
    }

    val samplePuzzleMap: String =
      """..##.......
        |#...#...#..
        |.#....#..#.
        |..#.#...#.#
        |.#...##..#.
        |..#.##.....
        |.#.#.#....#
        |.#........#
        |#.##...#...
        |#...##....#
        |.#..#...#.#""".stripMargin

    test(s"parsePuzzleMap() against sample") {
      assert(parsePuzzleMap(samplePuzzleMap).head.take(5) == Seq(false, false, true, true, false))
    }

    test(s"countTrees(3,1) against sample") {
      assert(countTrees(parsePuzzleMap(samplePuzzleMap), 3, 1) == 7)
    }

    test(s"countTrees() against input") {
      assert(countTrees(parsePuzzleMap(input), 3, 1) == 237)
    }
  }
}
