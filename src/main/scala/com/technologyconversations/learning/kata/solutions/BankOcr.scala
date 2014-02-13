package com.technologyconversations.learning.kata.solutions

class BankOcr {

  val pipesMap = Map(
    0 -> Seq(" _ ", "   ", " _ ", " _ ", "   ", " _ ", " _ ", " _ ", " _ ", " _ "),
    1 -> Seq("| |", "  |", " _|", " _|", "|_|", "|_ ", "|_ ", "  |", "|_|", "|_|"),
    2 -> Seq("|_|", "  |", "|_ ", " _|", "  |", " _|", "|_|", "  |", "|_|", " _|"),
    3 -> Seq("   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ")
  )

  def digitPipes(digit: Int, line: Int = 0, pipes: Seq[String] = Seq()): Seq[String] = {
    if (line == pipesMap.size) pipes
    else digitPipes(digit, line + 1, pipes :+ pipesMap(line)(digit))
  }

  def digit(pipes: Seq[String]): String = {
    for(digit <- 0 to 9) {
      if (digitPipes(digit).sameElements(pipes)) return digit.toString
    }
    "?"
  }

  def validChecksum(digits: String): Boolean = {
    def sum(digits: String, multiplier: Int = 9, total: Int = 0): Int = {
      if (multiplier == 0) total
      else sum(digits.drop(1), multiplier -1, total + (digits.take(1).toInt * multiplier))
    }
    sum(digits) % 11 == 0
  }

  def status(digits: String): String = {
    if (digits.contains("?")) "ILL"
    else if (!validChecksum(digits)) "ERR"
    else ""
  }

  def findVariations(pipesDigit: String, charIndex: Int = 0, digitVariations: Set[String] = Set()): Set[String] = {
    val pipes = "_| "
    if (charIndex >= pipesDigit.size) digitVariations
    else {
      for (pipe <- pipes) {
        val newDigit = digit(pipesDigit.patch(charIndex, pipe.toString, 1).split("\n"))
        if (!digitVariations.contains(newDigit) && newDigit != "?") {
          return findVariations(pipesDigit, charIndex, digitVariations + newDigit)
        }
      }
      findVariations(pipesDigit, charIndex + 1, digitVariations)
    }
  }

  def parse(pipes: String): String = {
    def pipesToSequence(lines: List[String], pipes: Seq[String] = Seq()): Seq[String] = {
      if (lines == Nil) pipes
      else pipesToSequence(lines.tail, pipes :+ lines.head.take(3))
    }
    def pipesToDigits(lines: List[String], digits: List[String] = List()): List[String] = {
      if (lines(0).length >= 3) {
        pipesToDigits(
          lines.map(line => line.drop(3)),
          digits :+ digit(pipesToSequence(lines)))
      } else digits
    }
    val digits = pipesToDigits(pipes.split("\n").toList).mkString
    val digitsStatus = status(digits)
    s"$digits $digitsStatus".trim
  }

}

object BankOcr {

  def apply(pipes: String) = new BankOcr().parse(pipes)

}