package com.wordpress.technologyconversations.learning.solutions

import scala.collection.mutable
import scala.collection.immutable
import java.util.NoSuchElementException
import scala.beans.BeanProperty
import org.scalatest.{Matchers, FlatSpec}

class Lesson06ClassesTest extends FlatSpec with Matchers {

  "Everything" can "be nested" in {
    val expected = 42
    class MyClass {
      // This class is nested within the MyClass
      class MyNestedClass {
        def myMethod = 42
      }
    }
    val myClass = new MyClass
    val myNestedClass = new myClass.MyNestedClass
    assertResult(expected) {
      myNestedClass.myMethod
    }
  }

  "Method" must "be public by default" in {
    val expected = 1
    class FirstClass {
      private var value = 0
      def increaseValueBy1() = {
        value += 1
        value
      }
    }
    val myFirstClass = new FirstClass
    assertResult(expected) {
      myFirstClass.increaseValueBy1()
    }
  }

  it can "be called without parenthesis if it has no parameters" in {
    val expected = 5
    class FirstClass {
      private var value = 0
      def increaseValueBy5() = {
        value += 5
        value
      }
    }
    val myFirstClass = new FirstClass
    assertResult(expected) {
      // Call without parameters
      myFirstClass.increaseValueBy5
    }
  }

  it must "be called without parenthesis if it is declared without them" in {
    val expected = "My name is FirstClass"
    class FirstClass {
      // Method was declared without parenthesis
      def name = "My name is FirstClass"
    }
    val myFirstClass = new FirstClass
    assertResult(expected) {
      // Call without parameters
      myFirstClass.name
    }
  }

  "Fields" must "be created for all public variables (equivalent to Java getter/setter)" in {
    val expected = "John"
    class Person {
      // This is equivalent to Java getter/setter.
      // It is not named with getName and setName as in Java but serves the same purpose
      var name = ""
    }
    val person = new Person
    person.name = "John"
    assertResult(expected) {
      person.name
    }
  }

  it can "be redefined" in {
    val expected = "Barcelona"
    class Place {
      private var privateCity = ""
      // This is redefined getter
      def city = privateCity
      // This is redefined setter (using _)
      def city_=(newCity: String) {
        if (newCity != "Madrid") privateCity = newCity
      }
    }
    val place = new Place
    place.city = "Barcelona"
    place.city = "Madrid"
    assertResult(expected) {
      place.city
    }
  }

  it must "comply with Java getXXX/setXXX syntax if @BeanProperty is used" in {
    val expected = "Europe"
    class Place {
      @BeanProperty var continent = ""
    }
    val place = new Place
    place.setContinent("Europe")
    assertResult(expected) {
      place.getContinent
    }
  }

  "val" must "have only getter if it is public" in {
    val expected = "Spain"
    class Place {
      val country = "Spain"
    }
    val place = new Place
    // Following does not compile
    // place.country = "Germany"
    assertResult(expected) {
      place.country
    }
  }

  "Class" can "have primary constructor that is part of the class definition" in {
    class Place(val city: String, val country: String) { }
    val place = new Place("Barcelona", "Spain")
    assert(place != null)
  }

  "Primary constructor" must "have parameters turned into fields" in {
    val expected = "Barcelona"
    class Place(val city: String, val country: String) { }
    val place = new Place("Barcelona", "Spain")
    assertResult(expected) {
      place.city
    }
  }

  it must "execute all statements in the class definition" in {
    val expected = "Yes"
    class Place(val country: String) {
      private var sunny = "No"
      // This is statement and is executed by primary constructor
      if (country == "Spain") sunny = "Yes"
      def isAlwaysSunny = sunny
    }
    val place = new Place("Spain")
    assertResult(expected) {
      place.isAlwaysSunny
    }
  }

  it can "be without parameters" in {
    class ClassWithoutParams { }
    val classWithoutParams = new ClassWithoutParams
    assert(classWithoutParams != null)
  }

  it can "have parameters with default arguments, be private, etc (same rules as with fields)" in {
    val expected = "Europe"
    class Place(val country: String, val continent: String = "Europe") { }
    val place = new Place("Spain")
    assertResult(expected) {
      place.continent
    }
  }

  "Auxiliary constructor" should "be declared with this and must start with the call to the primary constructoor" in {
    val expected = "Unknown"
    class Place(val city: String, val country: String) {
      // this is auxiliary constructor
      def this(city: String) {
        // Must start with the call to the primary constructor
        this(city, "Unknown")
      }
    }
    val place = new Place("London")
    assertResult(expected) {
      place.country
    }
  }

  "Class" can "have multiple auxiliary constructors" in {
    val expected = "UK"
    class Place(val city: String, val country: String, val continent: String) {
      // this is the first auxiliary constructor
      def this(city: String) {
        this(city, "Unknown", "Unknown")
      }
      // this is the second auxiliary constructor
      def this(city: String, country: String) {
        this(city, country, "Unknown")
      }
    }
    val place = new Place("London", "UK")
    assertResult(expected) {
      place.country
    }
  }

}