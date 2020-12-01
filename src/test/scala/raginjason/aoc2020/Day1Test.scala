package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day1._
import raginjason.aoc2020.Day1Test._

class Day1Test extends Suites(
  new Part1Test, new Part2Test
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

    test(s"filterExpenses(2) against sample") {
      assert(filterExpenses(parseExpenses(sampleExpenses), 2) == Seq(1721, 299))
    }

    test(s"computeExpenses(2) against sample") {
      assert(computeExpenses(parseExpenses(sampleExpenses), 2) == 514579)
    }

    test(s"computeExpenses(2) against input") {
      assert(computeExpenses(parseExpenses(input), 2) == 365619)
    }
  }

  class Part2Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleExpenses: String =
      """1721
        |979
        |366
        |299
        |675
        |1456""".stripMargin

    test(s"filterExpenses(3) against sample") {
      assert(filterExpenses(parseExpenses(sampleExpenses), 3) == Seq(979, 366, 675))
    }

    test(s"computeExpenses(3) against sample") {
      assert(computeExpenses(parseExpenses(sampleExpenses), 3) == 241861950)
    }

    test(s"computeExpenses(3) against input") {
      assert(computeExpenses(parseExpenses(input), 3) == 236873508)
    }
  }
}
