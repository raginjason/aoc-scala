package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day1 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day1.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(computeExpenses(parseExpenses(input)))
  }

  def parseExpenses(input: String): Seq[Int] = (input: StringOps).lines.map(_.toInt).toSeq

  def computeExpenses(expenses: Seq[Int]): Int = {
    filterExpenses(expenses).product
  }

  def filterExpenses(expenses: Seq[Int]): Seq[Int] = {
    expenses.combinations(2).filter(_.sum == 2020).flatten.toSeq
  }
}
