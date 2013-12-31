package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson01BasicsTest extends UnitSpec {

  "Value" should "be declared with val" in {
    val myValue = 35
    assertResult(35) {
      myValue
    }
  }

  it should "be constant and its content cannot be changed" in {
    val myValue = 35
    // Following line, if not commented, would not compile
    // myValue = 5
    assertResult(35) {
      myValue
    }
  }

  it can "have the type inferred from the type of the expression or it can be specified" in {
    val myValue: String = "Hello" // This is a string
    val myOtherValue = "Hello" // This is also a string
    assertResult(myValue) {
      myOtherValue
    }
  }

  "Multiple values" can "be declared together" in {
    val myValue1, myValue2 = 42 // Both values have the same content 42
    assertResult(myValue1) {
      myValue2
    }
  }

  "Variable" should "be declared with var" in {
    var myVariable = "How are you?"
    assertResult("How are you?") {
      myVariable
    }
  }

  it can "change its content" in {
    var myVariable = "How are you?"
    myVariable = "Fine, thanks for asking"
    assertResult("Fine, thanks for asking") {
      myVariable
    }
  }

  "Numeric type" can "be Byte, Char, Short, Int, Long, Float and Double. They are all classses and can invoke methods" in {
    val myStringValue = 42.toString
    assertResult("42") {
      myStringValue
    }
    val myIntegerValue = "42".toInt
    assertResult(42) {
      myIntegerValue
    }
  }

  "Arithmetic operators" should "work in the same was as in Java or C++" in {
    val myValue = (1 + 3) * 5
    assertResult(20) {
      myValue
    }
  }
  it should "be methods" in {
    val myValue1 = 1 + 2
    val myValue2 = 1.+(2) // Both ways of using methods are allowed
    assertResult(myValue1) {
      myValue2
    }
  }

  "Functions and methods" should "be supported" in {
    val myValue = scala.math.pow(2, 4) // Imports and math will be explained later.
    assertResult(16) {
      myValue
    }
  }

  "Method" should "be used without parenthesis when it doesn't have parameters" in {
    val myValue = "aaabbc".distinct // Distinct will be explained later
    assertResult("abc") {
      myValue
    }
  }

  "Method apply" can "be omitted" in {
    val myValue1 = "Technology"(3)
    val myValue2 = "Technology".apply(3) // Both are the same. Apply depends on the type.
    assertResult(myValue1) {
      myValue2
    }
    assertResult('h') {
      myValue1
    }
  }

}
