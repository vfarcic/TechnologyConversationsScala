package com.wordpress.technologyconversations.learning.kata.solutions

class BowlingGame {

  var frames = List[Frame]()

  def roll(pins: Int) {
    if (frames.size > 0 && !frames.last.frameFinished) {
      frames.last.roll2 = pins
      frames.last.frameFinished = true
    } else {
      frames = frames :+ Frame(pins)
    }
  }

  def score: Int = {
    sumScore(frames)
  }

  private def sumScore(framesLeft: List[Frame]): Int = {
    var total = framesLeft.head.sum
    if (framesLeft.tail != Nil && framesLeft.head.sum == 10) {
      total += framesLeft(1).roll1
      if (framesLeft.head.strike) {
        if (!framesLeft(1).strike) total += framesLeft(1).roll2
        else total += framesLeft(2).roll1
      }
    }
    if (!framesLeft.tail.isEmpty && (frames.size - framesLeft.tail.size) < 10) total += sumScore(framesLeft.tail)
    total
  }

  case class Frame(roll1: Int) {
    var roll2: Int = 0
    def strike = roll1 == 10
    var frameFinished: Boolean = false
    if (strike) frameFinished = true
    def sum: Int = roll1 + roll2
  }

}