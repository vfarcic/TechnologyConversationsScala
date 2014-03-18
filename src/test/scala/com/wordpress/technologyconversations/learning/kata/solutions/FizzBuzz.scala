package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{Matchers, FlatSpec}


/*
For a given natural number greater than zero return:
* "fizz" if the number is dividable by 3
* "buzz" if the number is dividable by 5
* "fizzbuzz" if the number is dividable by 15
* the same number if no other requirement is fulfilled
 */
class FizzBuzzTest extends FlatSpec with Matchers {

  "FizzBuzz" should "return fizz if the number is dividable by 3" in {
    FizzBuzz.getResult(3) should be ("fizz")
    FizzBuzz.getResult(6) should be ("fizz")
  }

  it should "return buzz if the number is dividable by 5" in {
    FizzBuzz.getResult(5) should be ("buzz")
    FizzBuzz.getResult(10) should be ("buzz")
  }

  it should "return fizzbuzz if the number is dividable by 15" in {
    FizzBuzz.getResult(15) should be ("fizzbuzz")
    FizzBuzz.getResult(30) should be ("fizzbuzz")
  }

  it should "return the same number if no other requirement is fulfilled" in {
    FizzBuzz.getResult(1) should be ("1")
    FizzBuzz.getResult(2) should be ("2")
    FizzBuzz.getResult(4) should be ("4")
  }

}
