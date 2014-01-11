package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson02ControlStructuresTest extends UnitSpec {

  "If/else construct" should "always have value of the last expression" in {
    val expected = "smaller"
    val myValue = if (1 > 2) "bigger" else "smaller"
    assertResult(expected) {
      myValue
    }
  }

  it should " have value of type Unit when statement yields no value. Unit has a single value of ()." in {
    val expected = ()
    val myValue = if (1 > 2) "bigger"
    assertResult(expected) {
      myValue
    }
  }

  "semicolon" should "be optional when it falls just before the end of line, is before an }, an else and when it is clear from context where is the end of a statement" in {
    val expected = 3
    val myValue1 = 1 // There is no semicolon at the end of this statement
    val myValue2 = 2
    assertResult(expected) {
      myValue1 + myValue2
    }
  }

  it should "be used when multiple statements are on the same line" in {
    val expected = "bigger then 1"
    var myVariable = ""
    // There are two statements separated with semicolon
    if (2 > 1) { myVariable = "bigger"; myVariable += " then 1" }
    assertResult(expected) {
      myVariable
    }
  }

  "block statement" should "always have value of the last expression" in {
    val expected = 17
    val sum = {
      val number1 = 5
      val number2 = 12
      number1 + number2 // This is an expression
    }
    assertResult(expected) {
      sum
    }
  }

  it should "have value of type Unit when it ends with an assignment" in {
    val expected = ()
    val sum = {
      val number1 = 5
      val number2 = 12
      val number3 = number1 + number2 // This is an assignment
    }
    assertResult(expected) {
      sum
    }
  }

  "while loop" should "work in the same way as in Java" in {
    val expected = 10
    var number = 0
    var sum = 0
    while(number < 5) {
      sum += number
      number += 1
    }
    assertResult(expected) {
      sum
    }
  }

  "do loop" should "work in the same way as in Java" in {
    val expected = 10
    var number = 0
    var sum = 0
    do {
      sum += number
      number += 1
    } while(number < 5)
    assertResult(expected) {
      sum
    }
  }

  "for loop" should "be used with Range (more about Range later on) to accomplish the same result as for(initialization; test; update) loop in Java" in {
    val expected = 10
    var sum = 0
    for(number <- 0 until 5) { // Equivalent to for(int number = 0; number < 5; number++) in Java. Range until does NOT include upper bound.
      sum += number
    }
    assertResult(expected) {
      sum
    }
  }

  it can "be used with multiple generators (variable <- expression) separated by semicolons" in {
    val expected = "1 1; 1 2; 2 1; 2 2; 3 1; 3 2; "
    var result = ""
    for(number1 <- 1 to 3; number2 <- 1 to 2) { // Range to includes upper bound.
      result += number1 + " " + number2 + "; "
    }
    assertResult(expected) {
      result
    }
  }

  it can "have a 'guard' with each generator. Guard excludes iterations." in {
    val expected = "1 2; 2 1; 3 1; 3 2; "
    var result = ""
    for(number1 <- 1 to 3; number2 <- 1 to 2 if number1 != number2) { // if number1 != number2 is a guard
      result += number1 + " " + number2 + "; "
    }
    assertResult(expected) {
      result
    }
  }

  it can "have definitions" in {
    val expected = "1 2; 1 3; 2 1; 2 2; 2 3; "
    var result = ""
    for(number1 <- 1 to 2; number2 = 3 - number1; number3 <- number2 to 3) {
      result += number1 + " " + number3 + "; "
    }
    assertResult(expected) {
      result
    }
  }

  it can "have yield in the body that constructs collection" in {
    val expected = Vector("1", "2", "3", "4", "5")
    val myCollection = for(number <- 1 to 5) yield number.toString // Creates Vector. More on collections later.
    assertResult(expected) { // Vector can be instantiated as Vector(VALUE1, VALUE2, ...)
      myCollection
    }
  }

  it can "enclose generators, guards and definitions inside braces and can use new lines to separate them" in {
    val expected = "1 1; 1 2; 2 1; 2 2; 3 1; 3 2; "
    var result = ""
    for{
      number1 <- 1 to 3
      number2 <- 1 to 2} {
      result += number1 + " " + number2 + "; "
    }
    assertResult(expected) {
      result
    }
  }

}
