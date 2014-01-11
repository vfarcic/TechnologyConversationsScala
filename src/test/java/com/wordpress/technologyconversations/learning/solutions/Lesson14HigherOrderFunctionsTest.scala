package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

// TODO Publish
class Lesson14HigherOrderFunctionsTest extends UnitSpec {

  "Function" can "be stored as variable" in {
    // _ indicates that it is a function
    val myFloor = math.floor _
    myFloor(3.4) should be (3)
  }

  "Variable containing a function" can "be used inside another function" in {
    val myFloor = math.floor _
    val myArray = Array(1.3, 2.0, 3.9).map(myFloor)
    myArray should equal (Array(1, 2, 3))
  }

  "Function" can "be anonymous" in {
    def thirteenTimesDef(number: Int) = 13 * number
    // Anonymous function stored in a variable
    // Same as if def was used
    val thirteenTimes = (number: Int) => 13 * number
    thirteenTimes(8) should be (thirteenTimesDef(8))
  }

  it can "take another function as argument" in {
    // (parameterType) => resultType
    // Argument can be any function that receives and returns Double
    def functionWithHalf(func: (Double) => Double) = "Result is " + func(0.5)
    val ceilVal = functionWithHalf(math.ceil _)
    ceilVal should be ("Result is 1.0")
    val floorVal = functionWithHalf(math.floor _)
    floorVal should be ("Result is 0.0")
  }

  "Anonymous function" can "have types deduced" in {
    def functionWithHalf(func: (Double) => Double) = "Result is " + func(0.5)
    val result1 = functionWithHalf((number: Double) => 13 * number)
    val result2 = functionWithHalf((number) => 13 * number) // Same as previous; without parameterType
    result1 should be (result2)
    result2 should be ("Result is 6.5")
  }

  it can "be without parenthesis when there is only one parameter" in {
    def functionWithHalf(func: (Double) => Double) = "Result is " + func(0.5)
    val result1 = functionWithHalf((number) => 13 * number)
    val result2 = functionWithHalf(number => 13 * number) // Same as previous; without parenthesis around parameter
    result1 should be (result2)
    result2 should be ("Result is 6.5")
  }

  it can "have parameter replaced with an underscore if it occurs only once on the right-hand side of the =>" in {
    def functionWithHalf(func: (Double) => Double) = "Result is " + func(0.5)
    val result1 = functionWithHalf(number => 13 * number)
    val result2 = functionWithHalf(13 * _) // Same as previous; parameter replaced with _
    result1 should be (result2)
    result2 should be ("Result is 6.5")
  }

  "Closure" should "consist of code together with the definitions of any non-local variables that it uses" in {
    def myPower(power: Double) = (number: Double) => math.pow(number, power)
    val doublePower = myPower(2)
    val triplePower = myPower(3)
    doublePower(10) should be (100)
    triplePower(5) should be (125)
  }

  "Currying" can "turn a function that takes two arguments into a function that takes one argument" in {
    def myPowerLong(power: Double) = (number: Double) => math.pow(number, power)
    def myPowerShort(power: Double)(number: Double) = math.pow(number, power) // Same as previous
    myPowerLong(2)(10) should be (myPowerShort(2)(10))
    myPowerShort(2)(10) should be (100)
  }

  // Write test for control abstractions
  // Write test for return expression

}
