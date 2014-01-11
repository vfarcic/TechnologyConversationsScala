package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec
import scala.collection.mutable.ArrayBuffer

class Lesson04ArraysTest extends UnitSpec {

   "Array" should "be fixed length" in {
     val expected = 10
     val numbers = new Array(10)
     assertResult(expected) {
       numbers.length
     }
   }

   it should "initialize values with 0 when the type is Int" in {
     val expected = 0
     val numbers = new Array[Int](10)
     assertResult(expected) {
       numbers(5) // Get element with index 5
     }
   }

   it should "initialize values with null when the type is String" in {
     val expected = null
     val strings = new Array[String](10)
     assertResult(expected) {
       strings(4)
     }
   }

   it can "have values supplied when created" in {
     val expected = 16
     val numbers = Array(4, 16, 3)
     assertResult(expected) {
       numbers(1)
     }
   }

   it should "have the size determined by the number of elements" in {
     val expected = 3
     val numbers = Array("blue", "yellow", "green")
     assertResult(expected) {
       numbers.length
     }
   }

   it should "have elements that can be accessed with () (similar as [] in Java)" in {
     val expected = "green"
     val numbers = Array("blue", "yellow", "green")
     assertResult(expected) {
       numbers(2)
     }
   }

   it can "be converted to an ArrayBuffer" in {
     val expected = 3
     val array = Array(33, 18, 21)
     val buffer = array.toBuffer // ArrayBuffer explanation comes next
     assertResult(expected) {
       buffer.length
     }
   }

   "ArrayBuffer" can "be initialized without elements" in {
     val expected = 0
     val buffer = ArrayBuffer[Int]()
     assertResult(expected) {
       buffer.length
     }
   }

   it can "add one element" in {
     val expected = 42
     val buffer = ArrayBuffer[Int]()
     assertResult(0) {
       buffer.length
     }
     buffer += 35
     buffer += 42
     assertResult(expected) {
       buffer(1)
     }
   }

   it can "add multiple elements" in {
     val expected = 5
     val buffer = ArrayBuffer(3, 7)
     buffer += (35, 42, 3)
     assertResult(expected) {
       buffer.length
     }
   }

   it can "append collection" in {
     val expected = 4
     val buffer = ArrayBuffer(3)
     buffer ++= Array(35, 42, 3)
     assertResult(expected) {
       buffer.length
     }
   }

   it can "remove elements from the start with trimStart" in {
     val expected = 42
     val buffer = ArrayBuffer(35, 42, 3)
     buffer.trimStart(1)
     assertResult(expected) {
       buffer(0)
     }
   }

   it can "remove elements from the end with trimEnd" in {
     val expected = 1
     val buffer = ArrayBuffer(35, 42, 3)
     buffer.trimEnd(2)
     assertResult(expected) {
       buffer.length
     }
   }

   it can "specify elements at initialization" in {
     val expected = 3
     val buffer = ArrayBuffer(33, 18, 21)
     assertResult(expected) {
       buffer.length
     }
   }

   it can "insert an element at specified location" in {
     val expected = 31
     val buffer = ArrayBuffer(33, 18, 21)
     buffer.insert(2, 31) // First parameter specifies index where to insert element
     assertResult(expected) {
       buffer(2)
     }
   }

   it can "insert multiple elements at specified location" in {
     val expected = 63
     val buffer = ArrayBuffer(33, 18, 21)
     buffer.insert(2, 61, 62, 63)
     assertResult(expected) {
       buffer(4)
     }
   }

   it can "remove an element at specified location" in {
     val expected = 21
     val buffer = ArrayBuffer(33, 18, 21)
     buffer.remove(1)
     assertResult(expected) {
       buffer(1)
     }
   }

   it can "remove multiple elements starting from specified location" in {
     val expected = 11
     val buffer = ArrayBuffer(33, 18, 21, 11)
     buffer.remove(1, 2) // Second parameter tells how many elements to remove
     assertResult(expected) {
       buffer(1)
     }
   }

   it can "be converted to an Array" in {
     val expected = 4
     val buffer = ArrayBuffer(33, 18, 21, 11)
     val array = buffer.toArray
     assertResult(expected) {
       array.length
     }
   }

   "Array and ArrayBuffer" can "be traversed using for loop" in {
     val expected = 6
     val array = Array(1, 2, 3)
     var sum = 0
     for (index <- 0 until array.length) {
       sum += array(index)
     }
     assertResult(expected) {
       sum
     }
   }

   it can "traverse every second element" in {
     val expected = "135"
     val array = Array(1, 2, 3, 4, 5)
     var result = ""
     for (index <- 0 until (array.length, 2)) {
       result += array(index)
     }
     assertResult(expected) {
       result
     }
   }

   it can "traverse elements starting from the end" in {
     val expected = "54321"
     val array = Array(1, 2, 3, 4, 5)
     var result = ""
     for (index <- (0 until array.length).reverse) {
       result += array(index)
     }
     assertResult(expected) {
       result
     }
   }

   it can "traverse all elements without using index" in {
     val expected = "12345"
     val array = Array(1, 2, 3, 4, 5)
     var result = ""
     for (element <- array) {
       result += element
     }
     assertResult(expected) {
       result
     }
   }

   it can "be transformed into a new array" in {
     val expected = 30
     val array = Array(1, 2, 3, 4, 5)
     val result = for (element <- array) yield 10 * element
     assertResult(expected) {
       result(2)
     }
   }

   it can "traverse all elements using guard" in {
     val expected = 50
     val array = Array(1, 2, 3, 4, 5)
     val result = for (element <- array if element > 2) yield {
       10 * element
     }
     assertResult(expected) {
       result(2)
     }
   }

   it can "use algorithms like sum" in {
     val expected = 15
     val array = Array(1, 2, 3, 4, 5)
     assertResult(expected) {
       array.sum
     }
   }

   it can "use algorithms like min" in {
     val expected = 5
     val array = Array(15, 27, 5, 66)
     assertResult(expected) {
       array.min
     }
   }

   it can "use algorithms like sorted" in {
     val expected = 15
     val array = ArrayBuffer(15, 27, 5, 66)
     val sorted = array.sorted
     assertResult(expected) {
       sorted(1)
     }
   }

   it can "use algorithms like sortWith" in {
     val expected = 27
     val array = ArrayBuffer(15, 27, 5, 66)
     val sorted = array.sortWith(_ > _) // < means ascending and > descending order
     assertResult(expected) {
       sorted(1)
     }
   }

   it can "use algorithms like sorted with strings" in {
     val expected = "green"
     val array = ArrayBuffer("red", "blue", "green")
     val sorted = array.sorted
     assertResult(expected) {
       sorted(1)
     }
   }

   it can "display contents as a string" in {
     val expected = "1527566"
     val array = ArrayBuffer(15, 27, 5, 66)
     assertResult(expected) {
       array.mkString
     }
   }

   it can "display contents as a string with separator" in {
     val expected = "15 and 27 and 5 and 66"
     val array = ArrayBuffer(15, 27, 5, 66)
     assertResult(expected) {
       array.mkString(" and ") // Parameter is a separator
     }
   }

   it can "display contents as a string with separator, prefix and suffix" in {
     val expected = "<15 and 27 and 5 and 66>"
     val array = ArrayBuffer(15, 27, 5, 66)
     assertResult(expected) {
       array.mkString("<", " and ", ">")
     }
   }

  it can "build a new collection by applying a function to all elements" in {
    val numbersAsStrings = Array("1", "2", "3")
    // Converts any element (_) to Int
    val numbers = numbersAsStrings.map(_.toInt)
    numbers.sum should be (6)
  }

   // TODO Write tests for multidimensional arrays

 }