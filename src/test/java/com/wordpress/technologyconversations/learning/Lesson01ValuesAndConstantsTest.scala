package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.BddSpec

class Lesson01ValuesAndConstantsTest extends BddSpec {

  info("As a developer")
  info("I want to be able to declare values and variables")
  info("So I can assign them some content")

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

      Given("value myValue is String 'Hello'")
      val myValue: String = "Hello"

      Then("content is 'Hello'")
      assertResult("Hello") {
        myValue
      }

    }

    scenario("Multiple values can be declared together") {

      Given("variables myVariable1 and myVariable2 are declared")
      val myVariable1, myVariable2 = 42

      Then("the all have the same content")
      assertResult(42) {
        myVariable1
      }
      assertResult(42) {
        myVariable2
      }

    }

  }

  feature("Variables are declared with var. They are similar to values but their content can change.") {

    scenario("Variables can change their value") {

      Given("variable myVariable is 'How are you?'")
      var myVariable = "How are you?"
      assertResult("How are you?") {
        myVariable
      }

      When("variable is changed to 'Fine, thanks for asking'")
      myVariable = "Fine, thanks for asking"

      Then("content is 'Fine, thanks for asking'")
      assertResult("Fine, thanks for asking") {
        myVariable
      }

    }

  }

}
