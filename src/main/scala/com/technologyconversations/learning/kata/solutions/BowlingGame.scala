package com.technologyconversations.learning.kata.solutions

/*
Count and sum the scores of a bowling game of one player.

Original description by Martin Fowler: http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata
Wikipedia article with scoring rules: http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
*/
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
    def sumScore(framesLeft: List[Frame], total: Int): Int = {
      var tempTotal = framesLeft.head.sum
      if (framesLeft.tail != Nil && framesLeft.head.sum == 10) {
        tempTotal += framesLeft(1).roll1
        if (framesLeft.head.strike) {
          if (!framesLeft(1).strike) tempTotal += framesLeft(1).roll2
          else tempTotal += framesLeft(2).roll1
        }
      }
      if (!framesLeft.tail.isEmpty && (frames.size - framesLeft.tail.size) < 10) sumScore(framesLeft.tail, total + tempTotal)
      else total + tempTotal
    }
    sumScore(frames, 0)
  }

  case class Frame(roll1: Int, var roll2: Int = 0) {
    def strike = roll1 == 10
    var frameFinished: Boolean = false
    if (strike) frameFinished = true
    def sum = roll1 + roll2
  }

}