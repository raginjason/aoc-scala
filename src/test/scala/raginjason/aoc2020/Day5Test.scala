package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableFor4
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day5._
import raginjason.aoc2020.Day5Test._

class Day5Test extends Suites(
  new Part1Test, new Part2Test
)

object Day5Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleBoardingPass: String = "FBFBBFFRLR"

    test(s"BoardingPass().rawRow against sample") {
      assert(BoardingPass(sampleBoardingPass).rawRow == "FBFBBFF")
    }
    test(s"BoardingPass().rawColumn against sample") {
      assert(BoardingPass(sampleBoardingPass).rawColumn == "RLR")
    }
    test(s"BoardingPass().row against sample") {
      assert(BoardingPass(sampleBoardingPass).row == 44)
    }
    test(s"BoardingPass().column against sample") {
      assert(BoardingPass(sampleBoardingPass).column == 5)
    }

    test(s"BoardingPass().seatId against sample") {
      assert(BoardingPass(sampleBoardingPass).seatId == 357)
    }

    val expectedBoardingPasses: TableFor4[String, Int, Int, Int] =
      Table(
        ("input", "expectedRow", "expectedColumn", "expectedSeatId"),
        ("BFFFBBFRRR", 70, 7, 567),
        ("FFFBBBFRRR", 14, 7, 119),
        ("BBFFBBFRLL", 102, 4, 820),
      )

    forAll(expectedBoardingPasses) { (input, expectedRow, expectedColumn, expectedSeatId) =>
      val bp = BoardingPass(input)
      test(s"BoardingPass($input)") {
        assert(bp.row == expectedRow)
        assert(bp.column == expectedColumn)
        assert(bp.seatId == expectedSeatId)
      }
    }

    test(s"allSeatIds() against input") {
      assert(allSeatIds(parseBoardingPasses(input)).max == 850)
    }

  }

  class Part2Test extends AnyFunSuite with ScalaCheckPropertyChecks {
    test(s"allSeatIds(ParseBoardingPasses)) against input") {
      assert(mySeatId(allSeatIds(parseBoardingPasses(input))) == 599)
    }
  }

}
