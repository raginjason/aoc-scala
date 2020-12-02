package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day2 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day2.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(validPasswordsPart1(parsePasswords(input)).length)
  }

  def parsePasswords(input: String): Seq[Password] = (input: StringOps).lines.map(x => parsePassword(x)).toSeq

  def parsePassword(input: String): Password = {
    val initialParts = input.split(' ')
    val minMax = initialParts(0).split('-')

    Password(minMax(0).toInt, minMax(1).toInt, initialParts(1).charAt(0), initialParts(2))
  }

  def validPasswordsPart1(passwords: Seq[Password]) = passwords.filter(_.validatePasswordsPart1)

  case class Password(min: Int, max: Int, char: Char, password: String) {
    val validatePasswordsPart1: Boolean = password.count(_ == char) match {
      case x if min to max contains x => true
      case _ => false
    }
  }

}
