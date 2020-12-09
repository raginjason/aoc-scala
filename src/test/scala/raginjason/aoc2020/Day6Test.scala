package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
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

    test(s"AnswerGroups().personAnswers answers against sample") {
      val a = AnswerGroups(sampleCustomsForms).personAnswers.map(_.answers)
      assert(a == Seq(
        Seq(Set('a', 'b', 'c')),
        Seq(Set('a'), Set('b'), Set('c')),
        Seq(Set('a', 'b'), Set('a', 'c')),
        Seq(Set('a'), Set('a'), Set('a'), Set('a')),
        Seq(Set('b'))
      )
      )
    }


    test(s"AnswerGroups().uniqueAnswers against sample") {
      val a = AnswerGroups(sampleCustomsForms).uniqueAnswers
      assert(a == Seq(Set('a', 'b', 'c'), Set('a', 'b', 'c'), Set('a', 'b', 'c'), Set('a'), Set('b')))
    }

    test(s"AnswerGroups().totalAnswers against sample") {
      val a = AnswerGroups(sampleCustomsForms).totalAnswers
      assert(a == 11)
    }

    test(s"AnswerGroups().totalAnswers against input") {
      val a = AnswerGroups(input).totalAnswers
      assert(a == 6542)
    }

    test(s"AnswerGroups().intersectingAnswers against sample") {
      val a = AnswerGroups(sampleCustomsForms).intersectingAnswers
      assert(a == Seq(Set('a', 'b', 'c'), Set(), Set('a'), Set('a'), Set('b')))
    }

    test(s"AnswerGroups().totalIntersectingAnswers against sample") {
      val a = AnswerGroups(sampleCustomsForms).totalIntersectingAnswers
      assert(a == 6)
    }

    test(s"AnswerGroups().totalIntersectingAnswers against input") {
      val a = AnswerGroups(input).totalIntersectingAnswers
      assert(a == 3299)
    }

  }

}
