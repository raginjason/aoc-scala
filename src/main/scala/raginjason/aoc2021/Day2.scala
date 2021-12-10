package raginjason.aoc2021

import scala.collection.immutable.StringOps

object Day2 {
  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day2.txt")).mkString.trim
  val emptyDirections: (List[Depth], List[Horizontal]) = (Nil, Nil)

  def main(args: Array[String]): Unit = {
    val (depthDirections, horizontalDirections) = separateDirections(parseDirections(input))
    println(depthDirections.map(_.value).sum * horizontalDirections.map(_.value).sum)
  }

  def parseDirections(input: String): Seq[Direction] = (input: StringOps).lines.map(matchDirection).toSeq

  def matchDirection(x: String): Direction = x match {
    case up if up.split(" ").head == "up" => Depth(-up.split(" ").last.toInt)
    case down if down.split(" ").head == "down" => Depth(down.split(" ").last.toInt)
    case forward if forward.split(" ").head == "forward" => Horizontal(forward.split(" ").last.toInt)
  }

  def separateDirections(direction: Seq[Direction]): (List[Depth], List[Horizontal]) = {
    direction.foldRight(emptyDirections) { case (f, (ds, hs)) =>
      f match {
        case d@Depth(_) => (d :: ds, hs)
        case h@Horizontal(_) => (ds, h :: hs)
      }
    }
  }

  abstract class Direction

  case class Horizontal(value: Int) extends Direction

  case class Depth(value: Int) extends Direction

}
