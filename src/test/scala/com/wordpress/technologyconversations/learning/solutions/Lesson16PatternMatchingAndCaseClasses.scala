package com.wordpress.technologyconversations.learning.solutions

import org.scalatest.{Matchers, FlatSpec}

class Lesson16PatternMatchingAndCaseClasses  extends FlatSpec with Matchers {

  "Match" should "be similar to Java switch" in {
    var myColor = ""
    val color = "Blue"
    color match {
      case "Red" => myColor = "My color is red"
      case "Blue" => myColor = "My color is blue"
    }
    myColor should be ("My color is blue")
  }

  it should "throw MatchError if no pattern matches" in {
    var myColor = ""
    val color = "Gray"
    evaluating(
      color match {
        case "Red" => myColor = "My color is red"
        case "Blue" => myColor = "My color is blue"
      }
    ) should produce [MatchError]
  }

  it should "use _ pattern for default case" in {
    var myColor = ""
    val color = "Blue"
    color match {
      case "Red" => myColor = "My color is red"
      case _ => myColor = "My color is not red"
    }
    myColor should be ("My color is not red")
  }

  it should "be an expression" in {
    val color = "Blue"
    // match expression is assigned to myColor
    val myColor = color match {
      case "Red" => "My color is red"
      case "Blue" => "My color is blue"
    }
    myColor should be ("My color is blue")
  }

  it can "have guard clause" in {
    val color = "dark blue"
    val myColor = color match {
      case "red" => "My color is red"
      case _ if color.contains("dark") => "My color is not light"
      case _ => "My color is exotic"
    }
    myColor should be ("My color is not light")
  }

  it can "use variable as case keyword" in {
    val color = "brown"
    val myColor = color match {
      case "red" => "My color is red"
      // variable clr is acting as _
      case clr => "My color is not red but " + clr
    }
    myColor should be ("My color is not red but brown")
  }

  it can "use type of an expression" in {
    val answer: Any = 42
    val result = answer match {
      case _: String => "It's a string"
      case _: Int => "It's an int"
      case _ => "It's something else"
    }
    result should be ("It's an int")
  }

  it can "be used with Arrays" in {
    val colors = Array("Red", "Green")
    val color = colors match {
      // Matches the array containing "Brown"
      case Array("Brown") => "Depressing"
      // Matches the array with 2 elements
      case Array(x, y) => "Only colors " + x + " and " + y + "?"
      // Matches any array starting with "Blue"
      case Array("Blue", _*) => "Let it start with blue"
      case _ => "We'll never know"
    }
    color should be ("Only colors Red and Green?")
  }

  it can "be used with Lists" in {
    val colors = List("Blue", "Red", "Green")
    val color = colors match {
      case "Brown" :: Nil => "Depressing"
      case x :: y :: Nil => "Only colors " + x + " and " + y + "?"
      case "Blue" :: tail => "Let it start with blue"
      case _ => "Let it start with blue"
    }
    color should be ("Let it start with blue")
  }

  it can "be used with tuple notations" in {
    val colors = ("Yellow", "Brown", "Green")
    val color = colors match {
      case (_, "Brown", _) => "Depressing"
      case (x, y, "Black") => "Colors are " + x + ", " + y + " and Black?"
      case ("Blue", "Red", "Yellow") => "Let it start with blue"
      case _ => "We'll never know"
    }
    color should be ("Depressing")
  }

  "Patterns" can "be used to declar variables" in {
    val (color1, color2) = ("Red", "Green")
    color2 should be ("Green")
  }

  it can "be used with arrays" in {
    val array = Array("Red", "Green", "Blue")
    // redColor and greenColor are assigned content of the first and second Array elements
    val Array(redColor, greenColor, _*) = array
    greenColor should be ("Green")
  }

}
