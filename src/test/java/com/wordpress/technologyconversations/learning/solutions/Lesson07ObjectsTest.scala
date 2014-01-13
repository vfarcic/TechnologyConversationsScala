package com.wordpress.technologyconversations.learning.solutions

import org.scalatest.{Matchers, FlatSpec}


class Lesson07ObjectsTest extends FlatSpec with Matchers {

  "Object" should "define a single instance of a class (similar to Java singleton) without constructor parameters" in {
    object Person {
      private var id = 0
      def uniqueId() = { id += 1; id }
    }
    Person.uniqueId() should be (1)
    Person.uniqueId() should be (2)
    Person.uniqueId() should be (3)
  }

  it can "be a companion to a class with the same name in which case both can access each others private features" in {
    val person1 = new SomePerson("John") // Person class is defined at the bottom of this source file
    val person2 = new SomePerson("Bob")
    person1.id should be (1)
    person2.id should be (2)
  }

  it can "extend a class and/or one or more traits" in { // Traits will be explained later
    class UnknownPerson {
      val name = "unknown"
      val address = "unknown"
    }
    object John extends UnknownPerson {
      override val name = "John"
    }
    John.name should be ("John")
    John.address should be ("unknown")
  }

  it can "have apply method" in {
    // Each SomePerson object (defined at the bottom of this source file) is calling the apply method
    val people = Array(SomePerson("John"), SomePerson("Bob"), SomePerson("Eva"))
    people(2).name should be ("Eva")
  }

  "Main method" should "be of type Array[String] => Unit" in {
    object Hello {
      var salute = "Hello, stranger"
      def main(args: Array[String]) {
        salute = "Hello, " + args(0)
      }
    }
    val hello = Hello
    hello.main(Array("darling"))
    hello.salute should be ("Hello, darling")
  }

  it can "be obtained by extending App trait and having program code in the constructor body" in {
    object Hello extends App {
      var salute = "Hello, " + args(0)
    }
    val hello = Hello
    hello.main(Array("darling"))
    hello.salute should be ("Hello, darling")
  }

  "Enumeration" should "be created by extending Enumeration class" in {
    object Colors extends Enumeration {
      val Red, Yellow, Green = Value
    }
    Colors.values should have size 3
  }

  it can "use IDs and/or names" in {
    object Colors extends Enumeration {
      // Value with only ID
      val Red = Value(1)
      // Value with both ID and name
      val Yellow = Value(2, "Yellow Color")
      // Value with only name
      val Green = Value("Green Color")
    }
    Colors.Red.id should be (1)
    Colors.Green.toString should be ("Green Color")
  }

  it can "be looked up by its ID or name" in {
    object Colors extends Enumeration {
      val Red = Value(1, "Red Color")
      val Yellow = Value(2, "Yellow Color")
      val Green = Value(3, "Green Color")
    }
    Colors(1) should equal (Colors.Red)
    Colors.withName("Green Color") should equal (Colors.Green)
  }

}

class SomePerson(val name: String) {
  val id = SomePerson.uniqueId()
}
object SomePerson {
  private var id = 0
  private def uniqueId() = { id += 1; id }
  def apply(name: String) = { new SomePerson(name) }
}