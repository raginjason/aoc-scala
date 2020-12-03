package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day3._
import raginjason.aoc2020.Day3Test._

class Day3Test extends Suites(
  new Part1Test, new Part2Test
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

  class Part2Test extends AnyFunSuite with ScalaCheckPropertyChecks {
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

    test(s"countTrees(1,1) against sample") {
      assert(countTrees(parsePuzzleMap(samplePuzzleMap), 1, 1) == 2)
    }

    test(s"countTrees(5,1) against sample") {
      assert(countTrees(parsePuzzleMap(samplePuzzleMap), 5, 1) == 3)
    }

    test(s"countTrees(7,1) against sample") {
      assert(countTrees(parsePuzzleMap(samplePuzzleMap), 7, 1) == 4)
    }

    test(s"countTrees(1,2) against sample") {
      assert(countTrees(parsePuzzleMap(samplePuzzleMap), 1, 2) == 2)
    }

    test(s"countTreesAllSlopes() against sample") {
      assert(countTreesAllSlopes(parsePuzzleMap(samplePuzzleMap), slopes) == Seq(2, 7, 3, 4, 2))
    }

    test(s"countTreesAllSlopes() against input") {
      assert(countTreesAllSlopes(parsePuzzleMap(input), slopes) == Seq(65, 237, 59, 61, 38))
    }

    test(s"multiplyTreeCount() against sample") {
      assert(multiplyTreeCount(countTreesAllSlopes(parsePuzzleMap(samplePuzzleMap), slopes)) == 336)
    }

    test(s"multiplyTreeCount() against input") {
      assert(multiplyTreeCount(countTreesAllSlopes(parsePuzzleMap(input), slopes)) == 2106818610)
    }
  }

}
