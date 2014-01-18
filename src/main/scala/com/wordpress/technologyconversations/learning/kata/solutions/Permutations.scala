package com.wordpress.technologyconversations.learning.kata.solutions

object Permutations {
  def apply(text: String) = text.toCharArray.permutations.toArray.map(_.mkString)
}