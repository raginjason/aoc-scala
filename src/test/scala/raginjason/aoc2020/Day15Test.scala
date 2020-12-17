package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day15._
import raginjason.aoc2020.Day15Test._

class Day15Test extends Suites(
  new Part1Test
)

object Day15Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleSeedMoves: String = """0,3,6""".stripMargin

    test(s"parseSeedMoves() against sample") {
      val a = parseSeedMoves(sampleSeedMoves)
      assert(a == Seq(0, 3, 6))
    }

    test("calculateNextMove() against sample, till 10 moves" ) {
      val a = parseSeedMoves(sampleSeedMoves)
      val g = Game()
      g.makeSeedMoves(a)
      assert(g.lastMove == 6)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 0)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 3)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 3)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 1)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 0)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 4)

      (g.makeMove _).tupled(g.calculateNextMove())
      assert(g.lastMove == 0)
    }

    test("playUntil(10) on sample" ) {
      val a = parseSeedMoves(sampleSeedMoves)
      val g = Game()
      g.makeSeedMoves(a)

      val finalPlay = g.playUntil(10)
      assert(finalPlay == 0)
    }

    test("playUntil(2020) == 436 on sample" ) {
      val a = parseSeedMoves(sampleSeedMoves)
      val g = Game()
      g.makeSeedMoves(a)

      val finalPlay = g.playUntil(2020)
      assert(finalPlay == 436)
    }

    test("playUntil(2020) == 496 on input" ) {
      val a = parseSeedMoves(input)
      val g = Game()
      g.makeSeedMoves(a)

      val finalPlay = g.playUntil(2020)
      assert(finalPlay == 496)
    }

  }

}
