package com.wordpress.technologyconversations.learning

import com.wordpress.technologyconversations.learning.specs.UnitSpec
import scala.collection.mutable
import scala.collection.immutable
import java.util.NoSuchElementException

class Lesson05MapsTest extends UnitSpec {

  "Map" must "be a collection of key value pairs" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3) // When it's only Map, it's immutable
    assertResult(expected) {
      ages.size
    }
  }

  it must "be mutable it its content should change" in {
    val expected = ()
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages -= "Victor" // -= Removes an element
    assertResult(expected) {
      ages.size
    }
  }

  it must "have parameter types if it starts blank" in {
    val expected = ()
    val myMap = new mutable.HashMap[String, Int] // HashMap
    assertResult(expected) {
      myMap.size
    }
  }

  it can "define pair using KEY -> VALUE or (KEY, VALUE)" in {
    val expected = ()
    val ages = Map("Victor" -> 39, ("Jordi", 35), "Sara" -> 3)
    assertResult(expected) {
      ages.size
    }
  }

  it can "values can be accessed using key" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    assertResult(expected) {
      ages("Victor")
    }
  }

  it must "throw NoSuchElementException when non existing key is accessed" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var michaelAge = 0
    try {
      michaelAge = ages("Michael")
    } catch {
      case _: NoSuchElementException => michaelAge = -1
    }
    assertResult(expected) {
      michaelAge
    }
  }

  it can "check whether some key exist with contains" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    val michaelAge = if (ages.contains("Michael")) ages("Michael") else 0
    assertResult(expected) {
      michaelAge
    }
  }

  it can "use getOrElse to get value" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    val michaelAge = ages.getOrElse("Michael", 0)
    assertResult(expected) {
      michaelAge
    }
  }

  it can "update value" in {
    val expected = ()
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages("Victor") = 31
    assertResult(expected) {
      ages("Victor")
    }
  }

  it can "add value if key does not exist" in {
    val expected = ()
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages("Victor") = 31
    ages("Michael") = 99
    assertResult(expected) {
      ages.size
    }
  }

  it can "add and update multiple associations with +=" in {
    val expected = ()
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages += ("Victor" -> 31, "Michael" -> 99)
    assertResult(expected) {
      ages.size
    }
  }

  it can "remove key with -=" in {
    val expected = ()
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages -= "Jordi"
    assertResult(expected) {
      ages.size
    }
  }

  it can "not be updated if it's immutable but a new one can be created" in {
    val expected = ()
    var ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages = ages + ("Michael" -> 99, "Eva" -> 41)
    ages = ages - "Michael"
      assertResult(expected) {
      ages.size
    }
  }

  it can "be iterated using for loop" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    var totalAge = 0
    for ((key, value) <- ages) {
      totalAge += value
      names += key + " "
    }
    assertResult(expected) {
      totalAge
    }
    assertResult("Victor Jordi Sara ") {
      names
    }
  }

  it can "return keySet" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    for (key <- ages.keySet) names += key + " "
    assertResult(expected) {
      names
    }
  }

  it can "return values" in {
    val expected = ()
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var totalAge = 0
    for (age <- ages.values) totalAge += age
    assertResult(expected) {
      totalAge
    }
  }

  it can "be sorted (only immutable)" in {
    val expected = ()
    val ages = immutable.SortedMap("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    for (key <- ages.keySet) names += key + " "
    assertResult(expected) {
      names
    }
  }

}