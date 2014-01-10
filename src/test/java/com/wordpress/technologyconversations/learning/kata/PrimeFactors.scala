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

  "Prime Factors" must "be List() for 1" in {
    PrimeFactors.result(1) should equal (List[Int]())
  }

  "Prime Factors" must "be List(2) for 2" in {
    PrimeFactors.result(2) should equal (List(2))
  }

  "Prime Factors" must "be List(3) for 3" in {
    PrimeFactors.result(3) should equal (List(3))
  }

  "Prime Factors" must "be List(2, 2) for 4" in {
    PrimeFactors.result(4) should equal (List(2, 2))
  }

  "Prime Factors" must "be List(5) for 5" in {
    PrimeFactors.result(5) should equal (List(5))
  }

  "Prime Factors" must "be List(2, 3) for 6" in {
    PrimeFactors.result(6) should equal (List(2, 3))
  }

  "Prime Factors" must "be List(7) for 7" in {
    PrimeFactors.result(7) should equal (List(7))
  }

  "Prime Factors" must "be List(2, 2, 2) for 8" in {
    PrimeFactors.result(8) should equal (List(2, 2, 2))
  }

  "Prime Factors" must "be List(3, 3) for 9" in {
    PrimeFactors.result(9) should equal (List(3, 3))
  }

  "Prime Factors" must "be List(3, 23) for 69" in {
    PrimeFactors.result(69) should equal (List(3, 23))
  }

  "Prime Factors" must "be List(2, 3, 29) for 174" in {
    PrimeFactors.result(174) should equal (List(2, 3, 29))
  }

}
