package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day6 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day6.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(totalAnswers(parseAnswerGroups(splitAnswerGroups(input))))
  }

  def splitAnswerGroups(input: String): Seq[String] = {
    (input: StringOps).split("(?m)^\\s*$").map(_.replaceAll("\n", "").trim)
  }

  def parseAnswerGroups(input: Seq[String]): Seq[AnswerGroup] = {
    input.map(AnswerGroup)
  }

  def totalAnswers(input: Seq[AnswerGroup]): Int = {
    input.map(_.answers.size).sum
  }

  case class AnswerGroup(input: String) {
    val answers: Set[Char] = input.filterNot((x: Char) => x.isWhitespace).toSet
  }

}
