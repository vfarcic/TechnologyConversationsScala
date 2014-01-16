package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{GivenWhenThen, FeatureSpec, Matchers, FlatSpec}

class TennisGame(player1: String, player2: String) {

  val points = collection.mutable.Map(player1 -> 0, player2 -> 0)
  val pointsDescription = Array("love", "fifteen", "thirty", "forty")

  def winBall(player: String) { points(player) += 1 }

  def score() = {
    val arr = points.toList.sortBy(_._2)
    val (lp, wp) = (arr(0)._1, arr(1)._1) // Loosing and winning players
    if (points(wp) >= 3 && points(lp) >= 3) {
      if (points(wp) - points(lp) >= 2) wp + " won"
      else if (points(lp) == points(wp)) "deuce"
      else "advantage " + wp
    } else pointsDescription(points(player1)) + ", " + pointsDescription(points(player2))
  }

}

/*
Implement a simple tennis game

**Rules:**

* Scores from zero to three points are described as "love", "fifteen", "thirty", and "forty" respectively.
* If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is "advantage" for the player in the lead.
* If at least three points have been scored by each player, and the scores are equal, the score is "deuce".
* A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
*/
class TennisGameUnitTest extends FlatSpec with Matchers {

  "Points" can "be added to each player" in {
    val game = new TennisGame("Victor", "Sarah")
    game.winBall("Victor")
    game.winBall("Victor")
    game.winBall("Sarah")
    game.winBall("Victor")
    game.points("Victor") should be (3)
    game.points("Sarah") should be (1)
  }

  "Love" should "be description for score 0" in {
    val game = new TennisGame("Victor", "Sarah")
    game.score() should be ("love, love")
  }

  "Fifteen" should "be description for score 1" in {
    val game = new TennisGame("Victor", "Sarah")
    game.winBall("Sarah")
    game.score() should be ("love, fifteen")
  }

  "Thirty" should "be description for score 2" in {
    val game = new TennisGame("Victor", "Sarah")
    game.winBall("Victor")
    game.winBall("Victor")
    game.winBall("Sarah")
    game.score() should be ("thirty, fifteen")
  }

  "Forty" should "be description for score 3" in {
    val game = new TennisGame("Victor", "Sarah")
    (1 to 3).foreach(x => game.winBall("Victor"))
    game.score() should be ("forty, love")
  }

  "Advantage" should "describe when least three points have been scored by each side and a player has one more point than his opponent" in {
    val game = new TennisGame("Victor", "Sarah")
    (1 to 3).foreach(x => game.winBall("Victor"))
    (1 to 4).foreach(x => game.winBall("Sarah"))
    game.score() should be ("advantage Sarah")
  }

  "Deuce" should "be description when at least three points have been scored by each player and the scores are equal" in {
    val game = new TennisGame("Victor", "Sarah")
    (1 to 3).foreach(x => game.winBall("Victor"))
    (1 to 3).foreach(x => game.winBall("Sarah"))
    game.score() should be ("deuce")
    game.winBall("Victor")
    game.score() should not be "deuce"
    game.winBall("Sarah")
    game.score() should be ("deuce")
  }

  "Game" should "be won by the first player to have won at least four points in total and with at least two points more than the opponent" in {
    val game = new TennisGame("Victor", "Sarah")
    (1 to 4).foreach(x => game.winBall("Victor"))
    (1 to 3).foreach(x => game.winBall("Sarah"))
    game.score() should not be "Victor won"
    game.score() should not be "Sarah won"
    game.winBall("Victor")
    game.score() should be ("Victor won")
  }

}

class TennisGameScenarioTest extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Simple tennis game") {

    Given("new game between Victor and Sarah starts")
    val game = new TennisGame("Victor", "Sarah")

    Then("score is love, love")
    game.score() should be ("love, love")

    When("Victor wins the ball")
    game.winBall("Victor")

    Then("score is fifteen, love")
    game.score() should be ("fifteen, love")

    When("Victor wins the ball")
    game.winBall("Victor")

    Then("score is thirty, love")
    game.score() should be ("thirty, love")

    When("Victor wins the ball")
    game.winBall("Victor")

    Then("score is forty, love")
    game.score() should be ("forty, love")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is forty, fifteen")
    game.score() should be ("forty, fifteen")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is forty, thirty")
    game.score() should be ("forty, thirty")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is deuce")
    game.score() should be ("deuce")

    When("Victor wins the ball")
    game.winBall("Victor")

    Then("score is advantage Victor")
    game.score() should be ("advantage Victor")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is deuce")
    game.score() should be ("deuce")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is advantage Sarah")
    game.score() should be ("advantage Sarah")

    When("Sarah wins the ball")
    game.winBall("Sarah")

    Then("score is Sarah won")
    game.score() should be ("Sarah won")

  }

}