package raginjason.aoc2021

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2021.Day1._
import raginjason.aoc2021.Day1Test._

class Day1Test extends Suites(
  new Part1Test
)

object Day1Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleMeasurements: String =
      """199
        |200
        |208
        |210
        |200
        |207
        |240
        |269
        |260
        |263""".stripMargin

    test(s"parseMeasurements() against sample") {
      assert(parseMeasurements(sampleMeasurements) == Seq(199, 200, 208, 210, 200, 207, 240, 269, 260, 263))
    }

    test(s"filterIncreasingMeasurements() against sample") {
      assert(filterIncreasingMeasurements(parseMeasurements(sampleMeasurements)) == Seq(200, 208, 210, 207, 240, 269, 263))
    }
  }

}
