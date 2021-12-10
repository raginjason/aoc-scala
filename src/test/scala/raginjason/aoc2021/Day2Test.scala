package raginjason.aoc2021

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2021.Day2._
import raginjason.aoc2021.Day2Test._

class Day2Test extends Suites(
  new Part1Test
)

object Day2Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleDirections: String =
      """forward 5
        |down 5
        |forward 8
        |up 3
        |down 8
        |forward 2""".stripMargin

    test(s"parseDirections() against sample") {
      assert(parseDirections(sampleDirections) == Seq(Horizontal(5), Depth(5), Horizontal(8), Depth(-3), Depth(8), Horizontal(2)))
    }

    test(s"separateDirections() against sample") {
      val (depthDirections, horizontalDirections) = separateDirections(parseDirections(sampleDirections))
      assert(depthDirections == Seq(Depth(5), Depth(-3), Depth(8)))
      assert(horizontalDirections == Seq(Horizontal(5), Horizontal(8), Horizontal(2)))
    }

  }

}
