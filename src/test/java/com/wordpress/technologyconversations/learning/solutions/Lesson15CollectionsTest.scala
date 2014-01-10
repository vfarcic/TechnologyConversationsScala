package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson15CollectionsTest extends UnitSpec {

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
    set should not contain (4)
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

}
