package com.technologyconversations.learning.kata.solutions

object BankOcr {

  val pipesMap = Map(
    0 -> Seq(" _ ", "   ", " _ ", " _ ", "   ", " _ ", " _ ", " _ ", " _ ", " _ "),
    1 -> Seq("| |", "  |", " _|", " _|", "|_|", "|_ ", "|_ ", "  |", "|_|", "|_|"),
    2 -> Seq("|_|", "  |", "|_ ", " _|", "  |", " _|", "|_|", "  |", "|_|", " _|"),
    3 -> Seq("   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ")
  )

  def getDigitPipes(digit: Int, line: Int = 0, pipes: Seq[String] = Seq()): Seq[String] = {
    if (line == pipesMap.size) pipes
    else getDigitPipes(digit, line + 1, pipes :+ pipesMap(line)(digit))
  }

  def getDigit(pipes: Seq[String]): String = {
    for(digit <- 0 to 9) {
      if (getDigitPipes(digit).sameElements(pipes)) return digit.toString
    }
    "ERROR"
  }

  def getPipesSequence(pipes: String): List[String] = {
    val lines = pipes.split("\n").toList
    val digit = Seq[String]()
    def getPipesSequence(lines: List[String], pipes: Seq[String] = Seq()): Seq[String] = {
      if (lines == Nil) pipes
      else getPipesSequence(lines.tail, pipes :+ lines.head.take(3))
    }
    def getPipesDigits(lines: List[String], digits: List[String]): List[String] = {
      if (lines(0).length >= 3) {
        val digit = getDigit(getPipesSequence(lines))
        val reducedLines = lines.map(line => line.drop(3))
        getPipesDigits(reducedLines, digits :+ digit)
      } else digits
    }
    getPipesDigits(lines, List())
  }

}