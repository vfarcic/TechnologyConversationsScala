package com.wordpress.technologyconversations.learning.kata.solutions

import com.wordpress.technologyconversations.learning.specs.{BddSpec, UnitSpec}
import scala.collection.immutable.TreeMap
import scala.collection.mutable.ArrayBuffer

object RomanNumerals {

  val romanNumbers = TreeMap("I" -> 1, "V" -> 5, "X" -> 10, "L" -> 50, "C" -> 100, "D" -> 500, "M" -> 1000)
  val arabicNumbers = romanNumbers.map(_.swap)

  def romanToInt(number: String) = {
    var sum = 0
    val numbers = ArrayBuffer[Int]()
    for(digit <- number) {
      val arabicNumber = romanNumbers(digit.toString)
      val lastIndex = numbers.length - 1
      if (lastIndex >= 0 && arabicNumber > numbers.last) numbers(lastIndex) = numbers.last * -1
      numbers += arabicNumber
    }
    numbers.sum
  }

  def intToRoman(number: Int) = {
    // Convert number to a list in reverse order (from right-most digit to left-most digit)
    val numbers = number.toString.map(_.asDigit).toList.reverse
    var result = ""
    for (digit <- 1 to numbers.length) {
      // Get roman representation of each digit
      result = IntDigitToRoman(digit, numbers(digit - 1)) + result
    }
    result
  }

  private def IntDigitToRoman(digit: Int, number: Int): String = {
    var result = ""
    if (number > 0) {
      // For 400, digit is 3 and number is 4. Multiplier is 100.
      val multiplier = (scala.math.pow(10, digit) / 10).toInt
      for ((key, value) <- arabicNumbers) { // Iterate through all roman numbers
        val keyDigit = key / multiplier
        // Number is one of roman numbers - 1
        if (number == keyDigit - 1) {
          result = arabicNumbers(multiplier) + value
          return result
        // Number is equal or greater than one of roman numbers
        } else if ((keyDigit == 1 && number <= keyDigit + 2) || (keyDigit == 5 && number <= keyDigit + 3)) {
          result = value + (arabicNumbers(multiplier) * (number - keyDigit))
          return result
        }
      }
    }
    result
  }

}

// Convert arabic numbers into roman numbers and vice versa
// There is no need to go above 3000.
// http://content.codersdojo.org/code-kata-catalogue/roman-numerals/
// http://www.novaroma.org/via_romana/numbers.html
class RomanNumeralsTest extends UnitSpec {

  val x = 1

  "Roman I" should "be equivalent to 1" in {
    RomanNumerals.romanToInt("I") should be (1)
  }

  "Roman V" should "be equivalent to 5" in {
    RomanNumerals.romanToInt("V") should be (5)
  }

  "Roman X" should "be equivalent to 10" in {
    RomanNumerals.romanToInt("X") should be (10)
  }

  "Roman L" should "be equivalent to 50" in {
    RomanNumerals.romanToInt("L") should be (50)
  }

  "Roman C" should "be equivalent to 100" in {
    RomanNumerals.romanToInt("C") should be (100)
  }

  "Roman D" should "be equivalent to 500" in {
    RomanNumerals.romanToInt("D") should be (500)
  }

  "Roman M" should "be equivalent to 1000" in {
    RomanNumerals.romanToInt("M") should be (1000)
  }

  "Roman I" should "be used to subtract 1 from 5 and 10" in {
    RomanNumerals.romanToInt("IV") should be (4)
    RomanNumerals.romanToInt("IX") should be (9)
  }

  "Roman Is" should "be used to add 1 to ones" in {
    RomanNumerals.romanToInt("II") should be (2)
    RomanNumerals.romanToInt("III") should be (3)
    RomanNumerals.romanToInt("VI") should be (6)
    RomanNumerals.romanToInt("VII") should be (7)
    RomanNumerals.romanToInt("VIII") should be (8)
  }

  "Roman X" should "be used to subtract 10 from 50 and 100" in {
    RomanNumerals.romanToInt("XL") should be (40)
    RomanNumerals.romanToInt("XC") should be (90)
  }

  "Roman Xs" should "be used to add 10 to tens" in {
    RomanNumerals.romanToInt("XX") should be (20)
    RomanNumerals.romanToInt("XXX") should be (30)
    RomanNumerals.romanToInt("LX") should be (60)
    RomanNumerals.romanToInt("LXX") should be (70)
    RomanNumerals.romanToInt("LXXX") should be (80)
  }

  "Roman C" should "be used to subtract 100 from 500 and 1000" in {
    RomanNumerals.romanToInt("CD") should be (400)
    RomanNumerals.romanToInt("CM") should be (900)
  }

  "Roman Cs" should "be used to add 100 to hundreds" in {
    RomanNumerals.romanToInt("CC") should be (200)
    RomanNumerals.romanToInt("CCC") should be (300)
    RomanNumerals.romanToInt("DC") should be (600)
    RomanNumerals.romanToInt("DCC") should be (700)
    RomanNumerals.romanToInt("DCCC") should be (800)
  }

  "Roman Ms" should "be used to add 1000 to thousands" in {
    RomanNumerals.romanToInt("MM") should be (2000)
    RomanNumerals.romanToInt("MMM") should be (3000)
  }

  "Roman MCMXCVIII" should "be converted to 1998" in {
    RomanNumerals.romanToInt("MCMXCVIII") should be (1998)
  }

  "Roman MCMXCIX" should "be converted to 1999" in {
    RomanNumerals.romanToInt("MCMXCIX") should be (1999)
  }

  "Arabic 1" should "be equivalent to I" in {
    RomanNumerals.intToRoman(1) should be ("I")
  }

  "Arabic 5" should "be equivalent to V" in {
    RomanNumerals.intToRoman(5) should be ("V")
  }

  "Arabic 10" should "be equivalent to X" in {
    RomanNumerals.intToRoman(10) should be ("X")
  }

  "Arabic 50" should "be equivalent to L" in {
    RomanNumerals.intToRoman(50) should be ("L")
  }

  "Arabic 100" should "be equivalent to C" in {
    RomanNumerals.intToRoman(100) should be ("C")
  }

  "Arabic 500" should "be equivalent to D" in {
    RomanNumerals.intToRoman(500) should be ("D")
  }

  "Arabic 1000" should "be equivalent to M" in {
    RomanNumerals.intToRoman(1000) should be ("M")
  }

  "Arabic 4 and 9" should "be written by placing I to the left of V and X" in {
    RomanNumerals.intToRoman(4) should be ("IV")
    RomanNumerals.intToRoman(9) should be ("IX")
  }

  "Roman I" should "be used to add to I and V" in {
    RomanNumerals.intToRoman(2) should be ("II")
    RomanNumerals.intToRoman(3) should be ("III")
    RomanNumerals.intToRoman(6) should be ("VI")
    RomanNumerals.intToRoman(7) should be ("VII")
    RomanNumerals.intToRoman(8) should be ("VIII")
  }

  "Arabic 40 and 90" should "be written by placing X to the left or L and C" in {
    RomanNumerals.intToRoman(40) should be ("XL")
    RomanNumerals.intToRoman(90) should be ("XC")
  }

  "Arabic 10s" should "be written by placing X to the right of X and L" in {
    RomanNumerals.intToRoman(20) should be ("XX")
    RomanNumerals.intToRoman(30) should be ("XXX")
    RomanNumerals.intToRoman(60) should be ("LX")
    RomanNumerals.intToRoman(70) should be ("LXX")
    RomanNumerals.intToRoman(80) should be ("LXXX")
  }

  "Arabic 400 and 900" should "be written by placing C to the left of D and M" in {
    RomanNumerals.intToRoman(400) should be ("CD")
    RomanNumerals.intToRoman(900) should be ("CM")
  }

  "Arabic 100s" should "be written by placing C to the right of C and D" in {
    RomanNumerals.intToRoman(200) should be ("CC")
    RomanNumerals.intToRoman(300) should be ("CCC")
    RomanNumerals.intToRoman(600) should be ("DC")
    RomanNumerals.intToRoman(700) should be ("DCC")
    RomanNumerals.intToRoman(800) should be ("DCCC")
  }

  "Arabic 1000s" should "be written by placing M to the right of M" in {
    RomanNumerals.intToRoman(2000) should be ("MM")
    RomanNumerals.intToRoman(3000) should be ("MMM")
  }

  "Arabic 1998" should "be converted to MCMXCVIII" in {
    RomanNumerals.intToRoman(1998) should be ("MCMXCVIII")
  }

  "Arabic 1999" should "be converted to MCMXCIX" in {
    RomanNumerals.intToRoman(1999) should be ("MCMXCIX")
  }

}