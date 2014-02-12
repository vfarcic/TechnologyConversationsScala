package com.technologyconversations.learning.kata.solutions

import org.scalatest.{Matchers, FlatSpec}

class BankOcrTest extends FlatSpec with Matchers {

//  "numbersToPipesLine" should "return a line of pipes for specified digits" in {
//    BankOcr.pipesLine("123456789", 0) should be ("    _  _     _  _  _  _  _ ")
//  }
//
//  "numbersToPipes" should "return correct pipes for digits 123456789" in {
//    val expected =
//      "    _  _     _  _  _  _  _ \n" +
//      "  | _| _||_||_ |_   ||_||_|\n" +
//      "  ||_  _|  | _||_|  ||_| _|\n" +
//      "                           "
//    BankOcr.pipes("123456789") should be (expected)
//  }
//
//  "numbersToPipes" should "return correct pipes for digits 000000000" in {
//    val expected =
//      " _  _  _  _  _  _  _  _  _ \n" +
//      "| || || || || || || || || |\n" +
//      "|_||_||_||_||_||_||_||_||_|\n" +
//      "                           "
//    BankOcr.pipes("000000000") should be (expected)
//  }

  "getDigitPipes" should "return pipes sequence for the specified digit" in {
    val expected = Seq(
      " _ ",
      "| |",
      "|_|",
      "   "
    )
    BankOcr.getDigitPipes(0) should equal(expected)
  }

  "getDigit" should "return digit sequence" in {
    val actual = Seq(
      " _ ",
      "|_|",
      " _|",
      "   "
    )
    BankOcr.getDigit(actual) should be("9")
  }

  "getPipesSequence" should "return list with 9 elements" in {
    val actual =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_||_|\n" +
      "  ||_  _|  | _||_|  ||_| _|\n" +
      "                           "
    BankOcr.getPipesSequence(actual) should have size(9)
  }

  "getPipesSequence" should "return list with correct digits" in {
    val actual =
        "    _  _     _  _  _  _  _ \n" +
        "  | _| _||_||_ |_   ||_||_|\n" +
        "  ||_  _|  | _||_|  ||_| _|\n" +
        "                           "
    val digits = BankOcr.getPipesSequence(actual)
    for(digit <- 1 to 9) {
      digits(digit - 1) should be(digit.toString)
    }
  }

}
