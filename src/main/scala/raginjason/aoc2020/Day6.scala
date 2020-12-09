package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day6 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day6.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(AnswerGroups(input).totalAnswers)
    println(AnswerGroups(input).totalIntersectingAnswers)
  }

  case class AnswerGroups(rawInput: String) {
    val personAnswers: Seq[PersonAnswers] = (rawInput: StringOps).split("(?m)^\\s*$").map(PersonAnswers)
    val uniqueAnswers: Seq[Set[Char]] = personAnswers.map(x => x.answers.flatten.toSet)
    val totalAnswers: Int = uniqueAnswers.map(_.size).sum
    val intersectingAnswers: Seq[Set[Char]] = personAnswers.map(_.answers.reduceLeft(_.intersect(_)))
    val totalIntersectingAnswers: Int = intersectingAnswers.map(_.size).sum
  }

  case class PersonAnswers(rawInput: String) {
    val answers: Seq[Set[Char]] = rawInput.trim.split('\n').map(_.toSet).toSeq
  }

}
