package com.wordpress.technologyconversations.learning.solutions

import org.scalatest.{Matchers, FlatSpec}


// TODO Publish
class Lesson12TraitsTest extends FlatSpec with Matchers {

  "Trait" can "have abstract methods (works like a Java interface)" in {
    trait Greeting {
      def greet(name: String): String // There is not body so it is abstract
    }
    class Person extends Greeting { // extends instead of implement
      def greet(name: String) = "Hello " + name // No need for override
    }
    val person = new Person
    person.greet("John") should be ("Hello John")
  }

  "Multiple traits" can "be added using the with keyword" in {
    trait Greeting {
      def greet(name: String): String // There is not body so it is abstract
    }
    trait Yelling {
      def yell(name: String): String
    }
    class Person extends Greeting with Yelling {
      def greet(name: String) = "Hello " + name
      def yell(name: String) = "Go away " + name + "!"
    }
    val person = new Person
    person.greet("John") should be ("Hello John")
    person.yell("Bob") should be ("Go away Bob!")
  }

  "Trait" can "have non-abstract methods" in {
    trait Greeting {
      def greet(name: String) = "Hello " + name + ". How are you?"
    }
    trait Yelling {
      def yell(name: String) = name + ", go back to your desk!"
    }
    class Person extends Greeting with Yelling { }
    val person = new Person
    person.yell("Bob") should be ("Bob, go back to your desk!")
  }

  it can "be added to an object during construction" in {
    trait Greeting {
      def greet(name: String) = "Hello " + name + ". How are you?"
    }
    trait Yelling {
      def yell(name: String) = name + ", go back to your desk!"
    }
    trait PoliteYelling extends Yelling {
      override def yell(name: String) = name + ", please go away!"
    }
    class Person extends Greeting with Yelling { }
    // Trait added during construction
    val person = new Person with PoliteYelling
    person.yell("Bob") should be ("Bob, please go away!")
  }

  "Traits" can "invoke each other" in {
    trait Greeting {
      def greet(name: String) = "Hello " + name + "."
    }
    trait GreetingWithQuestion extends Greeting {
      override def greet(name: String) = super.greet(name) + " How are you?"
    }
    class Person extends GreetingWithQuestion {}
    val person = new Person
    person.greet("John") should be ("Hello John. How are you?")
  }

  it should "call each other starting from the last one" in {
    trait Greeting {
      def greet(name: String) = "Hello " + name + "."
    }
    trait GreetingWithQuestion extends Greeting {
      override def greet(name: String) = super.greet(name) + " How are you?"
    }
    trait GreetingWithStatement extends Greeting {
      override def greet(name: String) = super.greet(name) + " Long time no see."
    }
    class Person { }
    // GreetingWithStatement calls super.greet on GreetingWithQuestion that calls super.greet on Greeting
    val person1 = new Person with Greeting with GreetingWithQuestion with GreetingWithStatement
    // GreetingWithQuestion calls super.greet on GreetingWithStatement that calls super.greet on Greeting
    val person2 = new Person with Greeting with GreetingWithStatement with GreetingWithQuestion
    person1.greet("John") should be ("Hello John. How are you? Long time no see.")
    person2.greet("John") should be ("Hello John. Long time no see. How are you?")
  }

  "Trait" can "have an concrete field" in {
    trait Location {
      // An concrete field because it has an initial value
      val planet = "Earth"
    }
    class Person(name: String) extends Location {
      def lives = name + " lives on planet " + planet
    }
    val john = new Person("John")
    john.lives should be ("John lives on planet Earth")
  }

  it can "have an abstract field" in {
    trait Location {
      // An abstract field because it does not have an initial value
      val name: String
      val planet: String
      def lives = name + " lives on planet " + planet
    }
    class Person(val name: String) extends Location {
      val planet = "Mars"
    }
    val bob = new Person("Bob")
    bob.lives should be ("Bob lives on planet Mars")
  }

  it must "be mixed into a subclass of the given type when it has self type specified" in {
    trait Greeting {
    }
    trait GreetingWithQuestion {
      // Self type is specified with this: Type
      this: Greeting =>
      def greet(name: String) = "Hi " + name + ". How are you?"
    }
    // This does not compile since Person is not a subtype of Greeting
    // class Person extends GreetingWithQuestion { }
    class Person extends Greeting with GreetingWithQuestion { }
    val john = new Person
    john.greet("John") should be ("Hi John. How are you?")
  }

  "Self type" can "structural" in {
    trait GreetingWithQuestion {
      // Structural self type specifies methods and fields that a class must have
      this: {val name: String} =>
      def greet = "Hi " + name + ". How are you?"
    }
    class Person(val name: String) extends GreetingWithQuestion { }
    val bob = new Person("Bob")
    bob.greet should be ("Hi Bob. How are you?")
  }

}