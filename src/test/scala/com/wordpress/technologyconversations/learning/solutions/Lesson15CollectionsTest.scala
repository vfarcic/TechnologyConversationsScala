package com.wordpress.technologyconversations.learning.solutions

import scala._
import org.scalatest.{Matchers, FlatSpec}

// TODO Publish
class Lesson15CollectionsTest extends FlatSpec with Matchers {

  "All collections" should "extend iterable trait" in {
    val array = Array(1, 2, 3)
    val it = array.iterator
    var sum = 0
    while(it.hasNext) {
      sum += it.next
    }
    sum should be (6)
  }

  it should "have a companion object with an apply method for constructing an instance" in {
    val map = Map(1 -> "1", 2 -> "2", 3 -> "3")
    map should have size 3
  }

  it should "support both mutable and immutable classes" in {
    val mutableMap = collection.mutable.Map(1 -> "1", 2 -> "2", 3 -> "3")
    val immutableMap = collection.immutable.Map(1 -> "1", 2 -> "2", 3 -> "3")
    mutableMap should be (immutableMap)
  }

  it should "have companion immutable object" in {
    val immutableMap = collection.immutable.Map(1 -> "1", 2 -> "2", 3 -> "3")
    // The same as when immutable.Map is used
    val objectMap = collection.Map(1 -> "1", 2 -> "2", 3 -> "3")
    immutableMap.getClass.getName should be (objectMap.getClass.getName)
  }

  "Immutable collections" can "create new collections out of old ones" in {
    val set = Set(1, 2, 3)
    val newSet = set + 4
    set should not contain 4
    newSet should contain (4)
  }

  "Vector" should "be immutable equivalent of ArrayBuffer (indexed sequence with fast random access)" in {
    val myVector = Vector(1, 2, 3)
    myVector should have size 3
  }

  "Range" should "be immutable integer sequence" in {
    val myRange = 2 to 5
    myRange should have size 4
    myRange should equal (Array(2, 3, 4, 5))
  }

  "List" should "be immutable and can be Nil or an object with a head element and tail that is again a list" in {
    val colors = List("Red", "Green")
    colors.head should be ("Red")
    colors.tail should equal (List("Green"))
    colors.tail.head should be ("Green")
    colors.tail.tail should be (Nil)
  }

  it can "use :: to make a new list" in {
    val list = List("Red", "Green")
    val newList = "Blue" :: list
    newList should equal (List("Blue", "Red", "Green"))
  }

  it can "be traversed using recursion" in {
    def colors(list: List[String]): String = {
      if (list == Nil) "" else list.head + " color " + colors(list.tail)
    }
    val colorsList = List("Red", "Green", "Blue")
    colors(colorsList) should be ("Red color Green color Blue color ")
  }

  "Linked list" should "be mutable" in {
    val list = collection.mutable.LinkedList("Red", "Green", "Blue")
    var iter = list
    while (iter != Nil) {
      iter.elem += " color"
      iter = iter.next
    }
    list(1) should be ("Green color")
  }

  "Set" should "be a collection of distinct values" in {
    // Adding 1 twice has no effect
    val numbers = Set(1, 2, 3, 1)
    numbers should have size 3
  }

  it should "not remember the order in which elements were inserted" in {
    val numbers = Set(1, 2, 3, 4, 5, 6)
    numbers.mkString should be ("516234")
  }

  "Linked Hash Set" should "remember the order in which elements were inserted" in {
    val numbers = collection.mutable.LinkedHashSet(1, 2, 3, 4, 5, 6)
    numbers.mkString should be ("123456")
  }

  "Sorted Set" should "have elements ordered" in {
    val numbers = collection.immutable.SortedSet(3, 1, 2)
    numbers.mkString should be ("123")
  }

  "Append (:+) and prepend (+:)" can "be used on a sequence" in {
    // Prepend 1 and append 6 to a range 2 to 5
    val seq = 1 +: (2 to 5) :+ 6
    seq.last should be (6)
    seq.head should be (1)
  }

  "Add (+) and remove (-)" can "be used on an unordered collection" in {
    val numbers = Set(1, 2, 3) + 4 - 2
    numbers should contain (4)
    numbers should not contain 2
  }

  "Bulk add (++) and remove (--)" can "be used" in {
    val numbers = Map(1 -> "1", 2 -> "2") ++ Map(3 -> "3", 4 -> "4") -- Array(4, 5)
    numbers should have size 3
  }

  "Prepend (::) and bulk prepend (:::)" can "be used on lists" in {
    val numbers = 1 :: List(2, 3) ::: List(4, 5)
    numbers.head should be (1)
    numbers.last should be (5)
  }

  "Mutations (+=), (++=), (-=), (--=)" can "be used on mutable collections" in {
    val numbers = collection.mutable.ArrayBuffer(1, 2) += 3 -= 1 ++= Array(5, 6, 7, 8) --= Array(6, 7)
    numbers should have size 4
  }

  "Map method" should "apply a function to a collection and yield a collection of results" in {
    val numbers = 1 to 3
    // map(_ * 10) multiplies all collection elements with 10
    val multipliedNumbers = numbers.map(_ * 10)
    multipliedNumbers.sum should be (60)
  }

  it should "be equivalent to yield" in {
    val numbers = 1 to 3
    val multipliedNumbers = numbers.map(_ * 10)
    // Same as previous
    val multipliedNumbersWithYield = for(number <- numbers) yield number * 10
    multipliedNumbersWithYield should equal (multipliedNumbers)
  }

  "Methods reduceLeft and reduceRight" should "apply operation to successive elements starting from left or right" in {
    val numbers = 1 to 4
    val sum = numbers.reduceLeft(_ * _)
    sum should be (((1 * 2) * 3) * 4)
  }

  "Methods foldLeft and foldRight" should "start with an initial element" in {
    val numbers = 1 to 4
    val sum = numbers.foldRight(-123)(_ * _)
    sum should be ((((-123 * 4) * 3) * 2) * 1)
  }

  "Methods scanLeft and scanRight" should "combine folding and mapping" in {
    val numbers = 1 to 3
    val newNumbers = numbers.scanLeft(-10)(_ * _)
    newNumbers should equal (List(-10, -10 * 1, (-10 * 1) * 2, ((-10 * 1) * 2) * 3))
  }

  "Method zip" should "combine two collections into a list of pairs" in {
    val names = List("Victor", "Jordi", "Sarah")
    val ages = List(39, 35, 3)
    val people = names zip ages
    people should equal (List(("Victor", 39), ("Jordi", 35), ("Sarah", 3)))
  }

  it can "apply a function to each pair" in {
    val names = List("Victor", "Jordi", "Sarah")
    val ages = List(39, 35, 3)
    val people = (names zip ages).map(person => person._1 + ", age " + person._2)
    people should equal (List("Victor, age 39", "Jordi, age 35", "Sarah, age 3"))
  }

  "Stream" should "be immutable list in which tail is computed lazily" in {
    def numbersFrom(number: BigInt): Stream[BigInt] = number #:: numbersFrom(number + 1)
    val numbers = numbersFrom(23)
    numbers.head should be (23)
    numbers.tail.tail.tail.head should be (26)
  }

  it can "call take followed by force to force evaluation of all values" in {
    def numbersFrom(number: BigInt): Stream[BigInt] = number #:: numbersFrom(number + 1)
    val numbers = numbersFrom(23)
    numbers.take(3).force.toString should be ("Stream(23, 24, 25)")
  }

}
