package com.wordpress.technologyconversations.learning.kata.solutions

object FizzBuzz {

//  def getResult(number: Int) = {
//    var result = ""
//    if (number % 3 == 0) result = "fizz"
//    if (number % 5 == 0) result += "buzz"
//    if (result == "") number.toString
//    else result
//  }

  def getResult(number: Int): String = (number % 3, number % 5) match {
    case (0 , 0) => "fizzbuzz"
    case (0 , _) => "fizz"
    case (_ , 0) => "buzz"
    case  _      => number.toString()
  }

}