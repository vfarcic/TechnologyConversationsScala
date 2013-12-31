package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson03FunctionsTest extends UnitSpec {

  "function" should "specify types of all parameters" in {
    val expected = ()
    def addNumbers(num1: Int, num2: Int) = num1 + num2 // This function specifies types of both parameters num1 and num2 as Int
    val sum = addNumbers(5, 4)
    assertResult(expected) {
      sum
    }
  }

  it should "determine the return type from the expression to the right of the = symbol" in {
    val expected = ()
    def addStrings(string1: String, string2: String) = string1 + string2 // There is no return type
    assertResult(expected) {
      addStrings("5", "4")
    }
  }

  it should "use block if there is more than one expression" in {
    val expected = ()
    def sumLoop(count: Int) = { // Using { to start the block
      var sum = 0
      for(number <- 1 to count) sum += number
      sum
    }
    assertResult(expected) {
      sumLoop(5)
    }
  }

  it should "have return type specified when it is defined as recursive" in {
    val expected = ()
    def factorial(number: Int): Int = { // Return type specified as Int
      if (number <= 1) 1
      else number * factorial(number - 1) // Recursive (calls itself)
    }
    assertResult(expected) {
      factorial(4)
    }
  }

  it can "have default arguments" in {
    val expected = ()
    def conversation(salute: String,
                     question: String = "How are you?",
                     response: String = "I'm all well.") = salute + " " + question + " " + response
    assertResult(expected) {
      conversation("Hello!", "Are you OK?") // sentences is call with only first 2 arguments
    }
  }

  it can "can be called with parameter names" in {
    val expected = ()
    def conversation(salute: String,
                     question: String = "How are you?",
                     answer: String = "I'm all well.") = salute + " " + question + " " + answer
    assertResult(expected) {
      conversation("Hello!", answer = "I'm much better.") // Second argument has parameter name specified
    }
  }

  it can "have arguments with parameter names in any order" in {
    val expected = ()
    def conversation(salute: String,
                     question: String = "How are you?",
                     answer: String = "I'm all well.") = salute + " " + question + " " + answer
    assertResult(expected) {
      conversation(answer = "I'm much better.", salute = "Hello!") // Arguments are specified in a different order
    }
  }

  it can "have variable number of arguments (single parameter called Seq)" in {
    val expected = ()
    def sum(numbers: Int*) = { // Variable number of arguments is set with *
      var result = 0
      for(number <- numbers) result += number
      result
    }
    assertResult(expected) {
      sum(1, 5, 7, 3)
    }
  }

  it can "be called with range converted to sequence" in {
    val expected = ()
    def sum(numbers: Int*) = { // Variable number of arguments is set with *
    var result = 0
      for(number <- numbers) result += number
      result
    }
    assertResult(expected) {
      sum(1 to 5: _*) // Range 1 to have is converted to sequence using _*
    }
  }

  "procedure" should "be a function that returns type Unit (no value)" in {
    val expected = ()
    def sum(numbers: Int*) { // = symbol was omitted
    var result = 0
      for(number <- numbers) result += number
      result
    }
    assertResult(expected) {
      sum(1, 5, 7, 3)
    }
  }

  it should "can specify explicit Unit as return type" in {
    val expected = ()
    def sum(numbers: Int*): Unit = { // Using Unit as return type
    var result = 0
      for(number <- numbers) result += number
      result
    }
    assertResult(expected) {
      sum(1, 5, 7, 3)
    }
  }

  "exception" should "is modeled after try/catch pattern matching syntax. Only runtime exceptions exist." in {
    val expected = ()
    var words = ""
    try {
      words = scala.io.Source.fromFile("THIS_FILE_DOES_NOT_EXIST").mkString // File operations are explained later. For now, mkString reads content of a file into a string.
    } catch {
      case ex: java.io.FileNotFoundException => words = "Not found (more specific exception)" // More specific should be used before more general exception type
      case _: Exception => words = "Not found (more general exception)" // _ can be used for variable name if it is not needed
    }
    assertResult(expected) {
      words
    }
  }

  it can "have finally clause that is executed whether or not the process throws an exception" in {
    val expected = ()
    var words = ""
    try {
      words = "Here are some words"
    } finally {
      words = "Let us put some words just in case"
    }
    assertResult(expected) {
      words
    }
  }

  it can "can have only catch, only finally or both clauses" in {
    val expected = ()
    var words = ""
    try {
      words = scala.io.Source.fromFile("THIS_FILE_DOES_NOT_EXIST").mkString
    } catch {
      case ex: java.io.FileNotFoundException => words = "Not found"
    } finally {
      words = "Let us put some words just in case"
    }
    assertResult(expected) {
      words
    }
  }

}
