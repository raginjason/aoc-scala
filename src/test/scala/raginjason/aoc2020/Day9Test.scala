package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day9._
import raginjason.aoc2020.Day9Test._

class Day9Test extends Suites(
  new Part1Test
)

object Day9Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleXMAS: String =
      """20
        |19
        |18
        |17
        |16
        |15
        |14
        |13
        |12
        |11
        |10
        |9
        |8
        |7
        |6
        |5
        |4
        |3
        |2
        |1
        |21
        |22
        |23
        |24
        |25""".stripMargin

    test(s"parseXMAS() against sample") {
      val a = parseXMAS(sampleXMAS)
      assert(a == Seq(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 21, 22, 23, 24, 25))
    }

    /*
    26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).
    49 would be a valid next number, as it is the sum of 24 and 25.
    100 would not be valid; no two of the previous 25 numbers sum to 100.
    50 would also not be valid; although 25 appears in the previous 25 numbers, the two numbers in the pair must be different.
    */
    test(s"validateLast() against sample, A") {
      val XMASSeq = parseXMAS(sampleXMAS)
      assert(validateLast(XMASSeq :+ BigInt(26)))
      assert(validateLast(XMASSeq :+ BigInt(49)))
      assert(!validateLast(XMASSeq :+ BigInt(100)))
      assert(!validateLast(XMASSeq :+ BigInt(50)))
    }

    /*
    26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.
    65 would not be valid, as no two of the available numbers sum to it.
    64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.
    */
    test(s"validateLast() against sample, B") {
      val XMASSeq = parseXMAS(sampleXMAS)
      assert(validateLast(XMASSeq.drop(1) ++ Seq(BigInt(45), BigInt(26))))
      assert(!validateLast(XMASSeq.drop(1) ++ Seq(BigInt(45), BigInt(65))))
      assert(validateLast(XMASSeq.drop(1) ++ Seq(BigInt(45), BigInt(64))))
      assert(validateLast(XMASSeq.drop(1) ++ Seq(BigInt(45), BigInt(66))))
    }

    test(s"sequencer against sample") {
      val XMASSeq = parseXMAS(sampleXMAS)
      assert(sequencer(XMASSeq ++ Seq(BigInt(45), BigInt(26)), 26).isEmpty)
      assert(sequencer(XMASSeq ++ Seq(BigInt(45), BigInt(65)), 26).head == BigInt(65))
      assert(sequencer(XMASSeq ++ Seq(BigInt(45), BigInt(64)), 26).isEmpty)
      assert(sequencer(XMASSeq ++ Seq(BigInt(45), BigInt(66)), 26).isEmpty)
    }

    val sampleFiveXMAS: String =
      """35
        |20
        |15
        |25
        |47
        |40
        |62
        |55
        |65
        |95
        |102
        |117
        |150
        |182
        |127
        |219
        |299
        |277
        |309
        |576""".stripMargin

    test("sequencer against sampleFiveXMAS to find 127") {
      val XMASSeq = parseXMAS(sampleFiveXMAS)
      assert(sequencer(XMASSeq, 6).head == BigInt(127))
    }

    test("sequencer against input to find 23278925") {
      val XMASSeq = parseXMAS(input)
      assert(sequencer(XMASSeq, 26).head == BigInt(23278925))
    }
  }

}
