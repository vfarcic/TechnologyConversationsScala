package com.wordpress.technologyconversations.learning.kata.solutions


class TennisGame(player1: Player, player2: Player) {

  def score = {
    if (player1.score >= 3 && player2.score >= 3) {
      if (Math.abs(player2.score - player1.score) >= 2) leadPlayer.name + " won"
      else if (player1.score == player2.score) "deuce"
      else "advantage " + leadPlayer.name
    } else player1.scoreDescription + ", " + player2.scoreDescription
  }

  def leadPlayer = if (player1 > player2) player1 else player2

}

class Player(val name: String) extends Ordered[Player] {
  val pointsDescription = Array("love", "fifteen", "thirty", "forty")
  var score = 0
  def scoreDescription = pointsDescription(score)
  def compare(that: Player) = this.score - that.score
  def winBall { score += 1 }
}

object Player {
  def apply(name: String) = {new Player(name)}
}