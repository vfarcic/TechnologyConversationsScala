package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{GivenWhenThen, FeatureSpec, Matchers, FlatSpec}
import scala.util.Sorting

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
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    victor.winBall
    victor.winBall
    sarah.winBall
    victor.winBall
    victor.score should be (3)
    sarah.score should be (1)
  }

  "Love" should "be description for score 0" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    game.score should be ("love, love")
  }

  "Fifteen" should "be description for score 1" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    sarah.winBall
    game.score should be ("love, fifteen")
  }

  "Thirty" should "be description for score 2" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    victor.winBall
    victor.winBall
    sarah.winBall
    game.score should be ("thirty, fifteen")
  }

  "Forty" should "be description for score 3" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => victor.winBall)
    game.score should be ("forty, love")
  }

  "Advantage" should "describe when least three points have been scored by each side and a player has one more point than his opponent" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => victor.winBall)
    (1 to 4).foreach(x => sarah.winBall)
    game.score should be ("advantage Sarah")
  }

  "Deuce" should "be description when at least three points have been scored by each player and the scores are equal" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    (1 to 3).foreach(x => victor.winBall)
    (1 to 3).foreach(x => sarah.winBall)
    game.score should be ("deuce")
    victor.winBall
    game.score should not be "deuce"
    sarah.winBall
    game.score should be ("deuce")
  }

  "Game" should "be won by the first player to have won at least four points in total and with at least two points more than the opponent" in {
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)
    (1 to 4).foreach(x => victor.winBall)
    (1 to 3).foreach(x => sarah.winBall)
    game.score should not be "Victor won"
    game.score should not be "Sarah won"
    victor.winBall
    game.score should be ("Victor won")
  }

}

class TennisGameScenarioTest extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Simple tennis game") {

    Given("new game between Victor and Sarah starts")
    val victor = Player("Victor")
    val sarah = Player("Sarah")
    val game = new TennisGame(victor, sarah)

    Then("score is love, love")
    game.score should be ("love, love")

    When("Victor wins the ball")
    victor.winBall

    Then("score is fifteen, love")
    game.score should be ("fifteen, love")

    When("Victor wins the ball")
    victor.winBall

    Then("score is thirty, love")
    game.score should be ("thirty, love")

    When("Victor wins the ball")
    victor.winBall

    Then("score is forty, love")
    game.score should be ("forty, love")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is forty, fifteen")
    game.score should be ("forty, fifteen")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is forty, thirty")
    game.score should be ("forty, thirty")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is deuce")
    game.score should be ("deuce")

    When("Victor wins the ball")
    victor.winBall

    Then("score is advantage Victor")
    game.score should be ("advantage Victor")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is deuce")
    game.score should be ("deuce")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is advantage Sarah")
    game.score should be ("advantage Sarah")

    When("Sarah wins the ball")
    sarah.winBall

    Then("score is Sarah won")
    game.score should be ("Sarah won")

  }

}