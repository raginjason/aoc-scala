package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day2._
import raginjason.aoc2020.Day2Test._

class Day2Test extends Suites(
  new Part1Test, new Part2Test
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
        assert(input.validatePasswordsPart1 == expected)
      }
    }

    test(s"validPasswordsPart1 against sample") {
      val input = Seq(
        Password(1, 3, 'a', "abcde"),
        Password(1, 3, 'b', "cdefg"),
        Password(2, 9, 'c', "ccccccccc")
      )

      val expected = Seq(
        Password(1, 3, 'a', "abcde"),
        Password(2, 9, 'c', "ccccccccc")
      )
      assert(validPasswordsPart1(input).length == 2)
      assert(validPasswordsPart1(input) == expected)
    }

    test(s"validPasswordsPart1 count against input") {
      assert(validPasswordsPart1(parsePasswords(input)).length == 418)
    }
  }

  class Part2Test extends AnyFunSuite with ScalaCheckPropertyChecks {
    test(s"validPasswordsPart2 against sample") {
      val input = Seq(
        Password(1, 3, 'a', "abcde"),
        Password(1, 3, 'b', "cdefg"),
        Password(2, 9, 'c', "ccccccccc")
      )

      val expected = Seq(
        Password(1, 3, 'a', "abcde")
      )
      assert(validPasswordsPart2(input).length == 1)
      assert(validPasswordsPart2(input) == expected)
    }

    test(s"validPasswordsPart2 count against input") {
      assert(validPasswordsPart2(parsePasswords(input)).length == 616)
    }

  }

}


