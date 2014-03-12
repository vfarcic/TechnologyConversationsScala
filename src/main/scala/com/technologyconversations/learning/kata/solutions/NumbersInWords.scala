package com.technologyconversations.learning.kata.solutions

class NumbersInWords {

  private[solutions] val numbersMap = Map(
    0 -> "", 1 -> "one", 2 -> "two", 3 -> "three", 4 -> "four",
    5 -> "five", 6 -> "six", 7 -> "seven", 8 -> "eight", 9 -> "nine",
    10 -> "ten", 11 -> "eleven", 12 -> "twelve", 13 -> "thirteen", 14 -> "fourteen",
    15 -> "fifteen", 16 -> "sixteen", 17 -> "seventeen", 18 -> "eighteen", 19 -> "nineteen",
    20 -> "twenty", 30 -> "thirty", 40 -> "forty",
    50 -> "fifty", 60 -> "sixty", 70 -> "seventy", 80 -> "eighty", 90 -> "ninety",
    100 -> "hundred", 1000 -> "thousand", 1000000 -> "million"
  )

  private[solutions] val wordsMap = numbersMap.map(_.swap)

  private[solutions] val multipliers = List(1000000, 1000, 100, 10, 1, 0)

  def numbersToWords(amount: Int): String = {
    if (amount > 0) numbersToWords(amount, "").trim
    else ""
  }

  def wordsToNumbers(amount: String): Int = {
    wordsToNumbers(amount.split(" ").toList, 0, 0)
  }

  private[solutions] def numbersToWords(amount: Int, words: String): String = {
    val multiplier = multipliers.filter(_ <= amount).head
    lazy val times = (amount - (amount % multiplier)) / multiplier
    lazy val newAmount = amount % multiplier
    if (amount < 20) {
      words + " " + numbersMap(amount)
    } else if (amount < 100) {
      numbersToWords(newAmount, words + " " + numbersMap(times * multiplier))
    } else if (amount >= 1000 && amount < 1000000) {
      numbersToWords(newAmount, words + " " + numbersToWords(times) + " " + numbersMap(1000))
    } else {
      numbersToWords(newAmount, words + " " + numbersMap(times) + " " + numbersMap(multiplier))
    }
  }

  private[solutions] def wordsToNumbers(amount: List[String], numbers: Int, prevNumber: Int): Int = {
    if (amount == Nil) return numbers + prevNumber
    val currentWords = amount.head.trim
    if (currentWords.isEmpty || !wordsMap.contains(currentWords)) return 0
    val currNumber = wordsMap(currentWords)
    if (currNumber < 10) {
      wordsToNumbers(amount.tail, numbers, currNumber)
    } else if (currNumber == 1000) {
      val currTotal = numbers + prevNumber
      val nonMillions = currTotal % 1000000
      val millions = currTotal - nonMillions
      wordsToNumbers(amount.tail, (nonMillions * currNumber) + millions, 0)
    } else if (prevNumber > 0) {
      wordsToNumbers(amount.tail, numbers + (currNumber * prevNumber), 0)
    } else {
      wordsToNumbers(amount.tail, numbers + currNumber, 0)
    }
  }

}
object NumbersInWords {

  def apply() = { new NumbersInWords }

}
