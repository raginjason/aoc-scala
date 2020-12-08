package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableFor2
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day6._
import raginjason.aoc2020.Day6Test._

class Day6Test extends Suites(
  new Part1Test
)

object Day6Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleCustomsForms: String =
      """abc
        |
        |a
        |b
        |c
        |
        |ab
        |ac
        |
        |a
        |a
        |a
        |a
        |
        |b""".stripMargin

    val expectedAnswerGroups: TableFor2[String, Set[Char]] =
      Table(
        ("input", "expected"),
        ("abc", Set('a', 'b', 'c')),
        (
          """a
            |b
            |c""".stripMargin, Set('a', 'b', 'c')),
        (
          """ab
            |ac""".stripMargin, Set('a', 'b', 'c')),
        (
          """a
            |a
            |a
            |a""".stripMargin, Set('a')),
        ("""b""".stripMargin, Set('b')),
      )

    forAll(expectedAnswerGroups) { (input, expected) =>
      test(s"AnswerGroup($input).answers against sample") {
        val a = AnswerGroup(input)
        //println(a.answers)
        assert(a.answers == expected)
      }
    }

    test(s"splitAnswerGroups() against sample") {
      val a = splitAnswerGroups(sampleCustomsForms)
      assert(a == Seq("abc", "abc", "abac", "aaaa", "b"))
    }

    test(s"parseAnswerGroups() against sample") {
      val a = parseAnswerGroups(splitAnswerGroups(sampleCustomsForms)).map(_.answers)
      assert(a == Seq(Set('a', 'b', 'c'), Set('a', 'b', 'c'), Set('a', 'b', 'c'), Set('a'), Set('b')))
    }

    test(s"totalAnswers() against sample") {
      val a = totalAnswers(parseAnswerGroups(splitAnswerGroups(sampleCustomsForms)))
      assert(a == 11)
    }

    test(s"totalAnswers() against input") {
      val a = totalAnswers(parseAnswerGroups(splitAnswerGroups(input)))
      assert(a == 6542)
    }

  }

}
