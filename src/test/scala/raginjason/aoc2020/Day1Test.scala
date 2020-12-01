package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day1._
import raginjason.aoc2020.Day1Test._

class Day1Test extends Suites(
  new Part1Test
)

object Day1Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleExpenses: String =
      """1721
        |979
        |366
        |299
        |675
        |1456""".stripMargin

    test(s"filterExpenses() against sample") {
      assert(filterExpenses(parseExpenses(sampleExpenses)) == Seq(1721, 299))
    }

    test(s"computeExpenses() against sample") {
      assert(computeExpenses(parseExpenses(sampleExpenses)) == 514579)
    }

    test(s"computeExpenses() against input") {
      assert(computeExpenses(parseExpenses(input)) == 365619)
    }
  }

}
