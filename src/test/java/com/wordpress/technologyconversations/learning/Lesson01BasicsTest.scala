package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.BddSpec

class Lesson01BasicsTest extends BddSpec {

  info("As a developer")
  info("I want to learn Scala basic")
  info("So I can move on to more complicated stuff")

  feature("Values are declared with val") {

    scenario("Set myValue to an integer") {

      Given("value myValue is 35")
      val myValue = 35

      Then("content is 35")
      assertResult(35) {
        myValue
      }

    }

    scenario("Values are constants and their contents cannot be changed") {

      Given("value myValue is 35")
      val myValue = 35

      When("content is changed to 5 does not compile")
      // myValue = 5

    }

    scenario("Values type is inferred from the type of the expression but it can be specified if necessary") {

      Given("value myValue is String \"Hello\"")
      val myValue: String = "Hello"
      val myOtherValue = "Hello"

      Then("content is \"Hello\"")
      assertResult("Hello") {
        myValue
      }
      assertResult(myOtherValue) {
        myValue
      }

    }

    scenario("Multiple values can be declared together") {

      Given("values myVariable1 and myVariable2 are declared")
      val myValue1, myValue2 = 42

      Then("they all have the same content")
      assertResult(42) {
        myValue1
      }
      assertResult(42) {
        myValue2
      }

    }

  }

  feature("Variables are declared with var. They are similar to values but their content can change.") {

    scenario("Variables can change their value") {

      Given("variable myVariable is \"How are you?\"")
      var myVariable = "How are you?"
      assertResult("How are you?") {
        myVariable
      }

      When("variable is changed to \"Fine, thanks for asking\"")
      myVariable = "Fine, thanks for asking"

      Then("variable content is \"Fine, thanks for asking\"")
      assertResult("Fine, thanks for asking") {
        myVariable
      }

    }

  }

  feature("Seven numeric types are supported: Byte, Char, Short, Int, Long, Float and Double") {

    scenario("All types are classes that can invoke methods") {

      Then("42.toString yields \"42\"")
      assertResult("42") {
        42.toString
      }

      Then("\"42\".toInt yields 42")
      assertResult(42) {
        "42".toInt
      }

    }

  }

  feature("Arithmetic operators + - * / % & | ^ >> << work in the same way as in Java or C++. All operators are methods") {

    scenario("Operators are methods") {

      Then("1 + 2 is the same as 1.+(2)")
      assertResult(1 + 2) {
        1.+(2)
      }

    }

  }

  feature("Both functions and methods are supported") {

    scenario("Functions are simpler to use than Java methods") {

      Then("Function pow(2, 4) yields 16.0")
      assertResult(16) {
        scala.math.pow(2, 4)
      }

    }

    scenario("When methods don't have parameters, parenthesis are not used") {

      Then("\"aaabbc\".distinct yields \"abc\"")
      assertResult("abc") {
        "aaabbc".distinct
      }

    }


    scenario("Method .apply([PARAM]) can be used like ([PARAM]). Functionality depends on the type.") {

      When("value myValue gets content \"Technology\"(3)")
      val myValue = "Technology"(3)

      Then("\"Technology\"(3) is a shortcut for \"Technology\".apply(3)")
      assertResult("Technology".apply(3)) {
        myValue
      }

      And("content is h (character at index 3)")
      assertResult('h') {
        myValue
      }

    }

  }

}
