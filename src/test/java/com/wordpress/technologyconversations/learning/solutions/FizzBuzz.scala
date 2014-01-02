package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

object FizzBuzz {

  def getResult(number: Int) = {
    var result = ""
    if (number % 3 == 0) result = "fizz"
    if (number % 5 == 0) result += "buzz"
    if (result == "") number
    else result
  }

}

// For a given natural number greater zero return:
// * "fizz" if the number is dividable by 3
// * "buzz" if the number is dividable by 5
// * "fizzbuzz" if the number is dividable by 15
// * the same number if no other requirement is fulfilled
class FizzBuzzTest extends UnitSpec {

  "FizzBuzz" should "return fizz if the number is dividable by 3" in {
    assertResult("fizz") {
      FizzBuzz.getResult(3)
    }
    assertResult("fizz") {
      FizzBuzz.getResult(6)
    }
  }

  it should "return buzz if the number is dividable by 5" in {
    assertResult("buzz") {
      FizzBuzz.getResult(5)
    }
    assertResult("buzz") {
      FizzBuzz.getResult(10)
    }
  }

  it should "return fizzbuzz if the number is dividable by 15" in {
    assertResult("fizzbuzz") {
      FizzBuzz.getResult(15)
    }
  }

  it should "return the same number if no other requirement is fulfilled" in {
    assertResult(1) {
      FizzBuzz.getResult(1)
    }
    assertResult(2) {
      FizzBuzz.getResult(2)
    }
    assertResult(4) {
      FizzBuzz.getResult(4)
    }
  }

}
