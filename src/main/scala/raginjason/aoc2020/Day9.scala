package raginjason.aoc2020

object Day9 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day9.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(sequencer(parseXMAS(input), 26).head)
  }

  def parseXMAS(input: String): Seq[BigInt] = {
    input.trim.split('\n').map(BigInt(_))
  }

  // slideSize should include the first test, so for this problem it should be 26 (25 + 1) or 6 (5 + 1)
  def sequencer(xmas: Seq[BigInt], slideSize: Int): Seq[BigInt] = {
    xmas.sliding(slideSize, 1).toList.collect {
      case x if !validateLast(x) => x.last
    }
  }

  def validateLast(xmasChunk: Seq[BigInt]): Boolean = {
    val needle = xmasChunk.last
    val haystack = xmasChunk.dropRight(1)
    val uniquePairs = for {
      (x, idxX) <- haystack.zipWithIndex
      (y, idxY) <- haystack.zipWithIndex
      if idxX < idxY
    } yield (x, y)
    val v = uniquePairs.collectFirst {
      case (x, y) if x + y == needle => true
    }.getOrElse(false)
    //println(s"Searching for $needle in $haystack, found: $v")
    v
  }

}
