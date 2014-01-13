package com.wordpress.technologyconversations.learning.solutions

import org.scalatest.{Matchers, FlatSpec}


class Lesson09InheritanceTest extends FlatSpec with Matchers {

  "Class" can "extend another class" in {
    class Person {
      val address = "Unknown"
    }
    class John extends Person {
      val name = "John"
    }
    val john = new John
    john.address should be ("Unknown")
  }

  "Classes, methods and fields" can "be declared final so that they cannot be extended or overridden" in {
    final class Person {
      val address = "Unknown"
    }
    // John cannot extend Person because it is final
    // In Java final is immutable and more similar to val
    //final class John extends Person {
    //  val name = "John"
    //}
  }

  "Method" must "use override modifier when overriding another non-abstract method" in {
    class Person {
      val address = "Unknown"
    }
    class John extends Person {
      override val address = "Rainy Street"
      val name = "John"
    }
    val john = new John
    john.address should be ("Rainy Street")
  }

  "Superclass method" can "be invoked with super" in {
    class Person {
      override def toString = "Unknown Person"
    }
    class John extends Person {
      val name = "John"
      override def toString = super.toString + " became " + name
    }
    val john = new John
    john.toString should be ("Unknown Person became John")
  }

  "(is|as]InstanceOf and classOf" can "be used to test and convert an instance" in {
    class Person {
      override def toString = "Unknown Person"
    }
    class John extends Person {
      val name = "John"
      override def toString = super.toString + " became " + name
    }
    val john = new John
    john.isInstanceOf[John]
    assert(john.isInstanceOf[Person])
    john.getClass should be (classOf[John])
    john.getClass should not be classOf[Person]
    // This does not compile since person does not have name field
    // john.asInstanceOf[Person].name
  }

  "Protected field or method" can "be accessed only from subclass" in {
    // In Java protected gives visibility throughout the same package
    class Person {
      protected var secret = "I don't like vegetables"
    }
    class NewPerson {
      val person = new Person
      // This does not compile because NewPerson is not a subclass of Person
      // person.secret = "New person's secret"
    }
    class John extends Person {
      secret = "John's secret"
    }
  }

  "Only the primary constructor" can "call a superclass constructor" in {
    class Person(address: String) { }
    // John has two parameters: address and name, one of which it passes to the superclass Person
    class John(address: String, name: String) extends Person(address) {
    }
  }

  "def" can "only override another def" in {
    class Person {
      def name = "Unknown"
    }
    class John() extends Person {
      override def name = "John"
    }
    val john = new John()
    john.name should be ("John")
  }

  "val" can "only override another val or def without parameters" in {
    abstract class Person { // Abstract class will be explained later
      def name: String
      val address = "Unknown"
    }
    class John(override val name: String) extends Person {
      override val address = "Rainy Street"
    }
    val john = new John("John")
    john.name should be ("John")
    john.address should be ("Rainy Street")
  }

  "var" can "only override an abstract var" in {
    abstract class Person {
      var name: String
    }
    class John(override var name: String) extends Person { }
    val john = new John("John")
    john.name should be ("John")
  }

  "Anonymous subclass" can "be created with a block" in {
    class Person(val name: String) { }
    // Creates an object with structural type that will be explained later
    val john = new Person("John") {
      def findHim = "Rainy Street is where " + name + " lives"
    }
    john.findHim should be ("Rainy Street is where John lives")
  }

  "Methods and fields" can "be abstract if their bodies are omitted" in {
    abstract class Person {
      // Abstract; there is no field body
      val name: String
      // Abstract; there is no method body
      def address: String
      // Abstract; there is no field body
      var country: String
    }
    // Override keywords are not required
    class Bob(val name: String) extends Person {
      var country = "UK"
      def address = "Rainy Street in " + country
    }
    val bob = new Bob("Bob")
    bob.name should be ("Bob")
    bob.country should be ("UK")
    bob.address should be ("Rainy Street in UK")
  }

  "Abstract fields" can "be customized using an anonymous type" in {
    abstract class Person {
      val name: String
    }
    val john = new Person {
      val name = "John"
    }
    john.name should be ("John")
  }

  "equals" must "checks whether two references refer to the same object" in {
    class Person(val name: String) {
      final override def equals(other: Any) = {
        val that = other.asInstanceOf[Person]
        if (that == null) false
        else name == that.name
      }
      final override def hashCode = 32 * name.hashCode + 17 * name.hashCode
    }
    new Person("John") should be (new Person("John"))
    new Person("John") should not be new Person("Bob")
  }

}
