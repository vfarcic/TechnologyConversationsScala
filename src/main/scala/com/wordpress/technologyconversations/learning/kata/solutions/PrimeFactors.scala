package com.wordpress.technologyconversations.learning.kata.solutions

object PrimeFactors {

  def result(number: Int, list: List[Int] = List()): List[Int] = {
    for(n <- 2 to number if (number % n == 0)) {
      return result(number / n, list :+ n)
    }
    list
  }

}