package raginjason.aoc2020

import scala.{Int, Option}
import scala.collection.mutable
import scala.annotation.tailrec
import scala.collection.mutable.Map

object Day15 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day15.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    val a = parseSeedMoves(input)
    val g = Game()
    g.makeSeedMoves(a)

    println(f"Part 1: ${g.playUntil(2020)}")
    println(f"Part 2: ${g.playUntil(30000000)}")
  }

  def parseSeedMoves(input: String): Seq[Int] = {
    input.trim.split(',').map(_.toInt)
  }

  type Move = Int
  type TurnPlayed = Int

  case class TurnHistory(mostRecentTurn: TurnPlayed = 0, secondMostRecentTurn: TurnPlayed = 0) {
    def nextTurn(turn: TurnPlayed): TurnHistory = {
      TurnHistory(turn, this.mostRecentTurn)
    }
    val distance = this.mostRecentTurn - this.secondMostRecentTurn
  }

  case class Game() {

    def makeSeedMoves(seedMoves: Seq[Int]): Unit = {
      seedMoves.foreach(
        x => (this.makeMove _).tupled(this.calculateSeedMove(x))
      )
    }

    def calculateSeedMove(seedMove: Int): (Move, TurnHistory) = {
      val offset = this.moveOffset + 1
      val turnHistory = this.MoveHistory.getOrElse(seedMove, TurnHistory())
      val (newMove, newTurnHistory) = turnHistory match {
        case TurnHistory(0,0) => (seedMove, turnHistory.nextTurn(offset))
        case _ => (0, TurnHistory())
      }
      (newMove, newTurnHistory)
    }

    var MoveHistory: mutable.Map[Move, TurnHistory] = mutable.Map[Move, TurnHistory]()
    var lastMove: Int = 0
    var moveOffset: Int = 0

    def calculateNextMove(): (Move, TurnHistory) = {
      val turnHistory = this.MoveHistory.getOrElse(this.lastMove, TurnHistory())

      val nextMove = turnHistory match {
        case t if t.secondMostRecentTurn == 0 => 0
        case TurnHistory(0,0) => 0
        case z => z.distance
      }

      val nextTurnHistory = this.MoveHistory.getOrElse(nextMove, TurnHistory()) match {
        case t => t.nextTurn(this.moveOffset + 1)
      }

      (nextMove, nextTurnHistory)
    }

    def makeMove(move: Move, turnHistory: TurnHistory): Unit = {
      this.moveOffset += 1
      this.lastMove = move
      this.MoveHistory(move) = turnHistory
    }

    def playUntil(finalMove: Int): Int = {
      do {
        (this.makeMove _).tupled(this.calculateNextMove())
      } while (this.moveOffset != finalMove)
      this.lastMove
    }

  }

}
