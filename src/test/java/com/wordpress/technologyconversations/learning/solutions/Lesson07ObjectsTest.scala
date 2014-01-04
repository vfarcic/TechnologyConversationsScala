package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson07ObjectsTest extends UnitSpec {

  "Object" should "define a single instance of a class(similar to Java singleton) without constructor parameters" in {
    val expected = 3
    object Person {
      private var id = 0
      def uniqueId() = { id += 1; id }
    }
    Person.uniqueId()
    Person.uniqueId()
    assertResult(expected) {
      Person.uniqueId()
    }
  }

  it can "be companion to a class with the same name in which case both can access each others private features" in {
    val expected = 2
    val person1 = new SomePerson("John") // Person class is defined at the bottom of this source file
    val person2 = new SomePerson("Bob")
    person1.id
    assertResult(expected) {
      person2.id
    }
  }

  it can "extend a class and/or one or more traits" in { // Traits will be explained later
    val expected = "unknown"
    class UnknownPerson {
      val name = "unknown"
      val address = "unknown"
    }
    object John extends UnknownPerson {
      override val name = "John"
    }
    assertResult("John") {
      John.name
    }
    assertResult(expected) {
      John.address
    }
  }

  it can "have apply method" in {
    val expected = "Eva"
    // Each Person object (defined at the bottom of this source file) is calling the apply method
    val people = Array(SomePerson("John"), SomePerson("Bob"), SomePerson("Eva"))
    assertResult(expected) {
      people(2).name
    }
  }

  // TODO Add main and application object
  // TODO Add enumerations

}

class SomePerson(val name: String) {
  val id = SomePerson.uniqueId()
}
object SomePerson {
  private var id = 0
  private def uniqueId() = { id += 1; id }
  def apply(name: String) = {new SomePerson(name)}
}