package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day4 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day4.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(validPassports(splitPassports(input)).length)
  }

  def splitPassports(input: String): Seq[String] = {
    (input: StringOps).split("(?m)^\\s*$").map(_.replaceAll("\n", " ").trim)
  }

  def validPassports(input: Seq[String]): Seq[Passport] = {
    input.map(Passport).filter(_.isValid)
  }

  case class Passport(input: String) {
    val length: Int = input.split(' ').length
    val hasCountryId: Boolean = input.split(' ').count(_.startsWith("cid:")) match {
      case x if x > 0 => true
      case _ => false
    }
    val isValid: Boolean = {
      length == 8 | length == 7 & !hasCountryId
    }
  }

}
