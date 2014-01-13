package com.wordpress.technologyconversations.learning.kata.solutions

import com.wordpress.technologyconversations.learning.specs.{BddSpec, UnitSpec}

// TODO Publish
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

  case class Frame(roll1: Int, var roll2: Int = 0, var frameFinished: Boolean = false) {
    def strike = roll1 == 10
    if (strike) frameFinished = true
    def sum: Int = roll1 + roll2
  }

}

// Sum the scores of a bowling game of one player.
// http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring
class BowlingGameScenarioTest extends BddSpec {

  scenario("Bowling game") {

    Given("new game started")
    val game = new BowlingGame

    When("1 pin is knocked in frame 1 and roll 1")
    game.roll(1)

    When("4 pins are knocked in frame 1 and roll 2")
    game.roll(4)

    Then("score is 5")
    game.score should be (5)

    When("4 pins are knocked in frame 2 and roll 1")
    game.roll(4)

    When("5 pins are knocked in frame 2 and roll 2")
    game.roll(5)

    Then("score is 14")
    game.score should be (14)

    When("6 pins are knocked in frame 3 and roll 1")
    game.roll(6)

    When("4 pins are knocked (spare) in frame 3 and roll 2")
    game.roll(4)

    Then("score is 24 (spare roll was still not played")
    game.score should be (24)

    When("5 pins are knocked in frame 4 and roll 1")
    game.roll(5)

    When("5 pins are knocked (spare) in frame 4 and roll 2")
    game.roll(5)

    Then("score is 39 (added 5 to the spare from the frame 3)")
    game.score should be (39)

    When("10 pins are knocked (strike) in frame 5 and roll 1")
    game.roll(10)

    Then("score is 59 (added 10 to the spare from the frame 4; strike roles were still not played)")
    game.score should be (59)

    When("0 pins are knocked in frame 6 and roll 1")
    game.roll(0)

    When("1 pin is knocked in frame 6 and roll 2")
    game.roll(1)

    Then("score is 61 (added 1 to the strike from the frame 5)")
    game.score should be (61)

    When("7 pins are knocked in frame 7 and roll 1")
    game.roll(7)

    When("3 pins are knocked (spare) in frame 7 and roll 2")
    game.roll(3)

    Then("score is 71 (spare roll was still not played)")
    game.score should be (71)

    When("6 pins are knocked in frame 8 and roll 1")
    game.roll(6)

    When("4 pins are knocked (spare) in frame 8 and roll 2")
    game.roll(4)

    Then("score is 87 (added 6 to the spare from the frame 7; spare roll was still not played)")
    game.score should be (87)

    When("10 pins are knocked (strike) in frame 9 and roll 1")
    game.roll(10)

    Then("score is 107 (added 10 to the spare from the frame 8; strike rolls were still not played)")
    game.score should be (107)

    When("2 pins are knocked in frame 10 and roll 1")
    game.roll(2)

    When("8 pins are knocked (spare) in frame 10 and roll 2")
    game.roll(8)

    Then("score is 127 (added 10 to the strike from the frame 9; spare roll was still not played)")
    game.score should be (127)

    When("6 pins are knocked (spare) in the bonus frame")
    game.roll(6)

    Then("score is 133 (added 6 to the spare from the frame 10)")
    game.score should be (133)

  }

}

class BowlingGameUnitTest extends UnitSpec {

  "First roll" should "store pins as roll1 and () as roll2" in {
    val game = new BowlingGame
    game.roll(4)
    game.frames should equal (List(game.Frame(4, 0, frameFinished = false)))
  }

  "Second roll" should "set pins as roll2 to the last rolls element" in {
    val game = new BowlingGame
    game.roll(4)
    game.roll(2)
    game.frames should equal (List(game.Frame(4, 2, frameFinished = true)))
  }

  "Three frames" should "be stored as three rolls elements" in {
    val game = new BowlingGame
    game.roll(1)
    game.roll(2)
    game.roll(3)
    game.roll(4)
    game.roll(5)
    game.roll(6)
    game.frames should equal (List(
      game.Frame(1, 2, frameFinished = true),
      game.Frame(3, 4, frameFinished = true),
      game.Frame(5, 6, frameFinished = true)))
  }

  "Score" should "be sum of all roles" in {
    val game = new BowlingGame
    game.roll(1)
    game.roll(2)
    game.roll(3)
    game.roll(4)
    game.roll(5)
    game.roll(4)
    game.score should be (1 + 2 + 3 + 4 + 5 + 4)
  }

  "Strike" should "set 0 as roll2" in {
    val game = new BowlingGame
    game.roll(10)
    game.frames should equal (List(game.Frame(10, 0, true)))
  }

  it should "add next two rolls (not strikes) to the score" in {
    val game = new BowlingGame
    game.roll(10)
    game.roll(1)
    game.roll(2)
    game.score should be ((10 + 1 + 2) + (1 + 2))
  }

  it should "add next two rolls (strike and not strike) to the score" in {
    val game = new BowlingGame
    game.roll(10)
    game.roll(10)
    game.roll(1)
    game.roll(2)
    game.roll(3)
    game.roll(4)
    game.score should be ((10 + 10 + 1) + (10 + 1 + 2) + (1 + 2) + (3 + 4))
  }

  it should "add next two rolls (both strikes) to the score" in {
    val game = new BowlingGame
    game.roll(10)
    game.roll(10)
    game.roll(10)
    game.roll(2)
    game.roll(3)
    game.score should be ((10 + 10 + 10) + (10 + 10 + 2) + (10 + 2 + 3) + (2 + 3))
  }

  it should "not add additional rolls if they were not played" in {
    val game = new BowlingGame
    game.roll(1)
    game.roll(1)
    game.roll(10)
    game.score should be ((1 + 1) + 10)
  }

  "Spare" should "add next roll to the score" in {
    val game = new BowlingGame
    game.roll(9)
    game.roll(1)
    game.roll(7)
    game.roll(3)
    game.roll(1)
    game.roll(2)
    game.score should be ((9 + 1 + 7) + (7 + 3 + 1) + (1 + 2))
  }

  it should "not add additional roll if it was not played" in {
    val game = new BowlingGame
    game.roll(1)
    game.roll(1)
    game.roll(8)
    game.roll(2)
    game.score should be ((1 + 1) + (8 + 2))
  }

  "Tenth frame" should "give 30 points for three strikes" in {
    val game = new BowlingGame
    (1 to 18).foreach(i => game.roll(0))
    game.roll(10)
    game.roll(10)
    game.roll(10)
    game.score should be (30)
  }

  it should "add one roll for spare" in {
    val game = new BowlingGame
    (1 to 18).foreach(i => game.roll(0))
    game.roll(9)
    game.roll(1)
    game.roll(10)
    game.score should be (20)
  }

  "Perfect game" should "score 300 for 12 strikes (12 regular and 2 bonus)" in {
    val game = new BowlingGame
    (1 to 12).foreach(i => game.roll(10))
    game.score should be (300)
  }

}
