package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day2._
import raginjason.aoc2020.Day2Test._

class Day2Test extends Suites(
  new Part1Test
)

object Day2Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val expectedPasswords =
      Table(
        ("input", "expected"),
        ("1-3 a: abcde", Password(1, 3, 'a', "abcde")),
        ("1-3 b: cdefg", Password(1, 3, 'b', "cdefg")),
        ("2-9 c: ccccccccc", Password(2, 9, 'c', "ccccccccc"))
      )

    forAll(expectedPasswords) { (input, expected) =>
      test(s"parsePassword($input)") {
        assert(parsePassword(input) == expected)
      }
    }

    val samplePasswords: String =
      """1-3 a: abcde
        |1-3 b: cdefg
        |2-9 c: ccccccccc""".stripMargin

    val sampleParsedPasswords = Seq(
      Password(1, 3, 'a', "abcde"),
      Password(1, 3, 'b', "cdefg"),
      Password(2, 9, 'c', "ccccccccc")
    )

    test(s"parsePasswords() against sample") {
      assert(parsePasswords(samplePasswords) == sampleParsedPasswords)
    }

    test(s"parsePasswords() against input") {
      assert(parsePasswords(input).length == 1000)
    }

    val samplePasswordValidation = Table(
      ("input", "expected"),
      (Password(1, 3, 'a', "abcde"), true),
      (Password(1, 3, 'b', "cdefg"), false),
      (Password(2, 9, 'c', "ccccccccc"), true)
    )

    forAll(samplePasswordValidation) { (input, expected) =>
      test(s"Password validation($input)") {
        assert(input.validate == expected)
      }
    }

    test(s"validPasswords against sample") {
      val input = Seq(
        Password(1, 3, 'a', "abcde"),
        Password(1, 3, 'b', "cdefg"),
        Password(2, 9, 'c', "ccccccccc")
      )

      val expected = Seq(
        Password(1, 3, 'a', "abcde"),
        Password(2, 9, 'c', "ccccccccc")
      )
      assert(validPasswords(input).length == 2)
      assert(validPasswords(input) == expected)
    }

    test(s"validPasswords count against input") {
      assert(validPasswords(parsePasswords(input)).length == 418)
    }
  }

}


