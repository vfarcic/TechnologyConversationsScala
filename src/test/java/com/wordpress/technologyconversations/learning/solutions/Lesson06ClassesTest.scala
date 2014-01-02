package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec
import scala.collection.mutable
import scala.collection.immutable
import java.util.NoSuchElementException
import scala.beans.BeanProperty

class Lesson06ClassesTest extends UnitSpec {

  "Everything" can "be nested" in {
    val expected = 42
    class MyClass {
      class MyNestedClass { // This class is nested within the MyClass
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
      myFirstClass.increaseValueBy5 // Called without parameters
    }
  }

  it must "be called without parenthesis if it is declared without them" in {
    val expected = "My name is FirstClass"
    class FirstClass {
      def name = "My name is FirstClass" // Method was declared without parenthesis
    }
    val myFirstClass = new FirstClass
    assertResult(expected) {
      myFirstClass.name // Called without parameters
    }
  }

  "Fields" must "be created for all public variables (equivalent to Java getter/setter)" in {
    val expected = "John"
    class Person {
      var name = "" // This is equivalent to Java getter/setter. It is not named with getName and setName as in Java but serves the same purpose
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
      def city = privateCity // This is redefined getter
      def city_=(newCity: String) { // This is redefined setter (using _)
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

  "val" must "have getter only if it is public" in {
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

  "Class" must "have primary constructor that is part of class definition" in {
    class Place(val city: String, val country: String) {
    }
    val place = new Place("Barcelona", "Spain")
    assert(place != null)
  }

  "Primary constructor" must "have parameters turned into fields" in {
    val expected = "Barcelona"
    class Place(val city: String, val country: String) {
    }
    val place = new Place("Barcelona", "Spain")
    assertResult(expected) {
      place.city
    }
  }

  it must "execute all statements in the class definition" in {
    val expected = "Yes"
    class Place(val country: String) {
      private var sunny = "No"
      if (country == "Spain") sunny = "Yes" // This is statement and is executed by primary constructor
      def isAlwaysSunny = sunny
    }
    val place = new Place("Spain")
    assertResult(expected) {
      place.isAlwaysSunny
    }
  }

  it can "be without parameters" in {
    class ClassWithoutParams {
    }
    val classWithoutParams = new ClassWithoutParams
    assert(classWithoutParams != null)
  }

  it can "have parameters with default arguments, be private, etc (same rules as with fields)" in {
    val expected = "Europe"
    class Place(val country: String, val continent: String = "Europe") {
    }
    val place = new Place("Spain")
    assertResult(expected) {
      place.continent
    }
  }

  "Auxiliary constructor" should "be declared with this and must start with the call to the primary constructoor" in {
    val expected = "Unknown"
    class Place(val city: String, val country: String) {
      def this(city: String) {// this is auxiliary constructor
        this(city, "Unknown") // Must start with the call to the primary constructor
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
      def this(city: String) {// this is first auxiliary constructor
        this(city, "Unknown", "Unknown")
      }
      def this(city: String, country: String) {// this is second auxiliary constructor
        this(city, country, "Unknown")
      }
    }
    val place = new Place("London", "UK")
    assertResult(expected) {
      place.country
    }
  }

}