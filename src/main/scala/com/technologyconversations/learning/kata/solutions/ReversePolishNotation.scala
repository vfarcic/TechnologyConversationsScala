package com.technologyconversations.learning.kata.solutions

object ReversePolishNotation {

  implicit class Calculator(input: String) {

    def calc: Double = {
      input.split(" ").foldLeft(List[Double]())((out, in) => {
        in match {
          case "+" => calcSign(out, _ + _)
          case "-" => calcSign(out, _ - _)
          case "*" => calcSign(out, _ * _)
          case "/" => calcSign(out, _ / _)
          case _ => out :+ in.toDouble
        }
      }).head
    }

  }

  private[solutions] def calcSign(nums: List[Double], operation:(Double, Double) => Double) = {
    nums.dropRight(2) :+ operation(nums.dropRight(1).last, nums.last)
  }

}
