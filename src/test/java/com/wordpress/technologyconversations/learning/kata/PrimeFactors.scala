package com.wordpress.technologyconversations.learning.kata

import com.wordpress.technologyconversations.learning.specs.UnitSpec
import scala.collection.mutable.ArrayBuffer

object PrimeFactors {

   def result(number: Int) = {
   }

 }

// Compute the prime factors of a given natural number.
// http://butunclebob.com/ArticleS.UncleBob.ThePrimeFactorsKata
class PrimeFactorsTest extends UnitSpec {

  "Prime Factors" must "be Array() for 1" in {
    PrimeFactors.result(1) should equal (Array[Int]())
  }

  "Prime Factors" must "be Array(2) for 2" in {
    PrimeFactors.result(2) should equal (Array(2))
  }

  "Prime Factors" must "be Array(3) for 3" in {
    PrimeFactors.result(3) should equal (Array(3))
  }

  "Prime Factors" must "be Array(2, 2) for 4" in {
    PrimeFactors.result(4) should equal (Array(2, 2))
  }

  "Prime Factors" must "be Array(5) for 5" in {
    PrimeFactors.result(5) should equal (Array(5))
  }

  "Prime Factors" must "be Array(2, 3) for 6" in {
    PrimeFactors.result(6) should equal (Array(2, 3))
  }

  "Prime Factors" must "be Array(7) for 7" in {
    PrimeFactors.result(7) should equal (Array(7))
  }

  "Prime Factors" must "be Array(2, 2, 2) for 8" in {
    PrimeFactors.result(8) should equal (Array(2, 2, 2))
  }

  "Prime Factors" must "be Array(3, 3) for 9" in {
    PrimeFactors.result(9) should equal (Array(3, 3))
  }

  "Prime Factors" must "be Array(3, 23) for 69" in {
    PrimeFactors.result(69) should equal (Array(3, 23))
  }

  "Prime Factors" must "be Array(2, 3, 29) for 174" in {
    PrimeFactors.result(174) should equal (Array(2, 3, 29))
  }

}