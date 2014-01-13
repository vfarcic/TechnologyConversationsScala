package com.wordpress.technologyconversations.learning.solutions

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import org.scalatest.{Matchers, FlatSpec}

// TODO Publish
class Lesson13OperatorsTest extends FlatSpec with Matchers {

  "Identifier" can "use much broader set of characters for its name than it is allowed in Java" in {
    val ** = scala.math.pow(_, _)
    **(10, 3) should be (1000)
  }

  it can "can include any sequence of characters in back quotes" in {
    val `yield` = 42
    `yield` should be (42)
  }

  "Operator" can "be infix when between two parameters" in {
    // 1 is implicit and 3 is explicit parameter
    val range1 = 1.to(3)
    val range2 = 1 to 3 // Same as previous
    range1 should be (range2)
  }

  it can "be unary when with one parameter" in {
    val string1 = 42 toString
    val string2 = 42.toString // Same as previous
    string1 should be (string2)
  }

  it can "be assignment" in {
    var number1 = 3
    var number2 = 3
    number1 += 5
    number2 = number2 + 5 // Same as previous
    number1 should be (number2)
  }

  "Apply method" can "be called without .apply" in {
    class Math {
      def apply(number1: Int, number2: Int) = {
        number1 + number2
      }
    }
    val math = new Math
    val sum1 = math.apply(3, 7)
    val sum2 = math(3, 7) // Same as previous
    sum1 should be (sum2)
  }

  it can "be used in companion objects to construct new objects without calling new" in {
    val names1 = mutable.HashMap("Victor" -> 39, "Sara" -> 3, "Jordi" -> 35)
    val names2 = new mutable.HashMap[String, Int]()
    names2 += ("Victor" -> 39, "Sara" -> 3, "Jordi" -> 35) // Same as names1
    names1 should be (names2)
  }

  "Update method" can "be called with =" in {
    val names = mutable.Map("Victor" -> 39, "Sara" -> 3, "Jordi" -> 35)
    names.update("Sara", 4)
    names("Sara") = 4 // Same as previous
    names("Sara") should be (4)
  }

  // TODO Write unapply tests

}
