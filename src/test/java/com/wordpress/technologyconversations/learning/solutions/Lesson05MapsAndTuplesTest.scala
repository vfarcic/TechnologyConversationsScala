package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec
import scala.collection.mutable
import scala.collection.immutable
import java.util.NoSuchElementException

class Lesson05MapsAndTuplesTest extends UnitSpec {

  "Map" must "be a collection of key value pairs" in {
    // When it's only Map, it's immutable
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages.size should be (3)
  }

  it should "be mutable if its content should change" in {
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages -= "Victor" // -= Removes an element
    ages.size should be (2)
  }

  it should "have parameter types if it starts blank" in {
    val myMap = new mutable.HashMap[String, Int] // HashMap
    myMap.size should be (0)
  }

  it should "define pairs using KEY -> VALUE or (KEY, VALUE)" in {
    val ages = Map("Victor" -> 39, ("Jordi", 35), "Sara" -> 3)
    ages.size should be (3)
  }

  it can "access values using key" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages("Victor") should be (39)
  }

  it should "throw NoSuchElementException when non existing key is accessed" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var michaelAge = 0
    try {
      michaelAge = ages("Michael")
    } catch {
      case _: NoSuchElementException => michaelAge = -1
    }
    michaelAge should be (-1)
  }

  it can "check whether some key exist with contains" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    val michaelAge = if (ages.contains("Michael")) ages("Michael") else 0
    michaelAge should be (0)
  }

  it can "use getOrElse to get value" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    val michaelAge = ages.getOrElse("Michael", 0)
    michaelAge should be (0)
  }

  it can "update value" in {
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages("Victor") = 31
    ages("Victor") should be (31)
  }

  it can "add value if key does not exist" in {
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages("Victor") = 31
    ages("Michael") = 99
    ages.size should be (4)
  }

  it can "add and update multiple associations with +=" in {
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages += ("Victor" -> 31, "Michael" -> 99)
    ages.size should be (4)
  }

  it can "remove key with -=" in {
    val ages = mutable.Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages -= "Jordi"
    ages.size should be (2)
  }

  it can "not be updated if it's immutable but a new one can be created" in {
    var ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    ages = ages + ("Michael" -> 99, "Eva" -> 41)
    ages = ages - "Michael"
    ages.size should be (4)
  }

  it can "be iterated using for loop" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    var totalAge = 0
    for ((key, value) <- ages) {
      totalAge += value
      names += key + " "
    }
    totalAge should be (77)
    names should be ("Victor Jordi Sara ")
  }

  it can "return keySet" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    for (key <- ages.keySet) names += key + " "
    names should be ("Victor Jordi Sara ")
  }

  it can "return values" in {
    val ages = Map("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var totalAge = 0
    for (age <- ages.values) totalAge += age
    totalAge should be (77)
  }

  it can "be sorted if it's immutable" in {
    val ages = immutable.SortedMap("Victor" -> 39, "Jordi" -> 35, "Sara" -> 3)
    var names = ""
    for (key <- ages.keySet) names += key + " "
    names should be ("Jordi Sara Victor ")
  }

  "Tuple" can "be created by enclosing individual values in parentheses" in {
    val person = ("John", "Rainy Street", "UK")
    person.toString() should be ("(John,Rainy Street,UK)")
  }

  it can "access its elements with the methods _1, _2, _3, ... (positions start with 1)" in {
    val person = ("John", "Rainy Street", "UK")
    person._2 should be ("Rainy Street")
  }

  it can "use pattern matching to get its components" in {
    val person = ("John", "Rainy Street", "UK")
    val (name, street, country) = person
    country should be ("UK")
  }

  it can "use _ for pattern matching when not all components are needed" in {
    val person = ("John", "Rainy Street", "UK")
    val (_, street, _) = person
    street should be ("Rainy Street")
  }

}