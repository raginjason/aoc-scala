package raginjason.aoc2019

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2019.Day1._
import raginjason.aoc2019.Day1Test._

class Day1Test extends Suites(
  new Part1Test
)

object Day1Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {
    val massExpectedFuels =
      Table(
        ("mass", "expectedFuel"),
        (12, 2),
        (14, 2),
        (1969, 654),
        (100756, 33583)
      )

    forAll(massExpectedFuels) { (mass, expectedFuel) =>
      test(s"computeFuel($mass)") {
        assert(Part1.computeFuel(mass) == expectedFuel)
      }
    }

  }

}