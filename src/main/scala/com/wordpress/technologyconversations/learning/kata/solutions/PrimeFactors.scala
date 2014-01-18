package com.wordpress.technologyconversations.learning.kata.solutions

object PrimeFactors {

  def result(number: Int): List[Int] = {
    for(n <- 2 to number if (number % n == 0)) {
      return n :: result(number / n)
    }
    List()
  }

}