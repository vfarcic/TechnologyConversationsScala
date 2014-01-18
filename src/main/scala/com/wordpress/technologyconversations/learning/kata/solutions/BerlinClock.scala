package com.wordpress.technologyconversations.learning.kata.solutions

object BerlinClock {

  def convertToBerlinTime(time: String) = {
    val parts = time.split(":").map(_.toInt)
    Array(
      seconds(parts(2)),
      topHours(parts(0)),
      bottomHours(parts(0)),
      topMinutes(parts(1)),
      bottomMinutes(parts(1)))
  }

  def seconds(number: Int) = {
    if (number % 2 == 0) "Y" else "O"
  }

  def topHours(number: Int) = onOff(4, topNumberOfOnSigns(number))

  def bottomHours(number: Int) = onOff(4, number % 5)

  def topMinutes(number: Int) = onOff(11, topNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR")

  def bottomMinutes(number: Int) = onOff(4, number % 5, "Y")

  private def onOff(lamps: Int, onSigns: Int, onSign: String = "R") = {
    onSign * onSigns + "O" * (lamps - onSigns)
  }

  private def topNumberOfOnSigns(number: Int) = (number - (number % 5)) / 5

}