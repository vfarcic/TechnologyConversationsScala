package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson01BasicsTest extends UnitSpec {

  "Value" should "be declared with val" in {
    val expected = ()
    val myValue = 35
    assertResult(expected) {
      myValue
    }
  }

  it should "be constant and its content cannot be changed" in {
    val expected = ()
    val myValue = 35
    // Following line, if not commented, would not compile
    // myValue = 5
    assertResult(expected) {
      myValue
    }
  }

  it can "have the type inferred from the type of the expression or it can be specified" in {
    val expected = ()
    val myValue: String = "Hello" // This is a string
    val myOtherValue = "Hello" // This is also a string
    assertResult(myValue) {
      myOtherValue
    }
    assertResult(expected) {
      myValue
    }
  }

  it can "be lazy in which case its initialization is deferred until it is accessed for the first time" in {
    val expected = ()
    var actual = "" // Var will be explained later soon
    lazy val lazyVal = scala.io.Source.fromFile("THIS_FILE_DOES_NOT_EXIST").mkString // File operations are explained later. For now, mkString reads content of a file into a string.
    try { // try/catch/finally will be explained later. For now, they are similar as in Java.
      actual = lazyVal // There was no need to put lazyVal declaration in try block because it is initialized here where it is accessed for the first time
    } catch {
      case _: Exception => actual = "There was an exception"
    }
    assertResult(expected) {
      actual
    }
  }

  "Multiple values" can "be declared together" in {
    val expected = ()
    val myValue1, myValue2 = 42 // Both values have the same content 42
    assertResult(myValue1) {
      myValue2
    }
    assertResult(expected) {
      myValue1
    }
  }

  "Variable" should "be declared with var" in {
    val expected = ()
    var myVariable = "How are you?"
    assertResult(expected) {
      myVariable
    }
  }

  it can "change its content" in {
    val expected = ()
    var myVariable = "How are you?"
    myVariable = "Fine, thanks for asking"
    assertResult(expected) {
      myVariable
    }
  }

  "Numeric type" can "be Byte, Char, Short, Int, Long, Float and Double. They are all classses and can invoke methods" in {
    val expected = ()
    val myStringValue = 42.toString
    assertResult("42") {
      myStringValue
    }
    val myIntegerValue = "42".toInt
    assertResult(expected) {
      myIntegerValue
    }
  }

  "Arithmetic operators" should "work in the same was as in Java or C++" in {
    val expected = ()
    val myValue = (1 + 3) * 5
    assertResult(expected) {
      myValue
    }
  }
  it should "be methods" in {
    val expected = ()
    val myValue1 = 1 + 2
    val myValue2 = 1.+(2) // Both ways of using methods are allowed
    assertResult(myValue1) {
      myValue2
    }
    assertResult(expected) {
      myValue2
    }
  }

  "Functions and methods" should "be supported" in {
    val expected = ()
    val myValue = scala.math.pow(2, 4) // Functions, imports and math package will be explained later. For now, pow raises first parameter to the power of second.
    assertResult(expected) {
      myValue
    }
  }

  "Method" should "be used without parenthesis when it doesn't have parameters" in {
    val expected = ()
    val myValue = "aaabbc".distinct // Distinct will be explained later
    assertResult(expected) {
      myValue
    }
  }

  "Method apply" can "be omitted" in {
    val expected = ()
    val myValue1 = "Technology"(3) // Apply depends on the type. For strings, apply returns character at specified position.
    val myValue2 = "Technology".apply(3) // Both are the same.
    assertResult(myValue1) {
      myValue2
    }
    assertResult(expected) {
      myValue1
    }
  }

}
