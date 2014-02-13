package com.technologyconversations.learning.kata.solutions

import org.scalatest.{GivenWhenThen, FeatureSpec, Matchers, FlatSpec}

// http://codingdojo.org/cgi-bin/wiki.pl?KataBankOCR
class BankOcrScenarioTest extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Parse pipes representing 000000000 into actual numbers") {

    val digits = "000000000"

    Given("Pipes are representing " + digits)
    val pipes =
      " _  _  _  _  _  _  _  _  _ \n" +
      "| || || || || || || || || |\n" +
      "|_||_||_||_||_||_||_||_||_|\n" +
      "                           "

    When("pipes are parsed")
    val result = BankOcr(pipes)

    Then("result is " + digits)
    result should be(digits)

  }

  scenario("Parse pipes representing 123456789 into actual numbers") {

    val digits = "123456789"

    Given(s"Pipes are representing $digits")
    val pipes =
        "    _  _     _  _  _  _  _ \n" +
        "  | _| _||_||_ |_   ||_||_|\n" +
        "  ||_  _|  | _||_|  ||_| _|\n" +
        "                           "

    When("pipes are parsed")
    val result = BankOcr(pipes)

    Then("result is " + digits)
    result should be(digits)

  }

  scenario("Incorrect checksum is represented with ERR") {

    val digits = "123456780"

    Given(s"Pipes are representing $digits")
    val pipes =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_|| |\n" +
      "  ||_  _|  | _||_|  ||_||_|\n" +
      "                           "

    When("pipes are parsed")
    val result = BankOcr(pipes)

    Then("result is $digits ERR")
    result should be(s"$digits ERR")

  }

  scenario("Incorrect pipe digits are replaced with the question mark (?) and represented with ILL") {

    val digits = "1234?678?"

    Given(s"Pipes are representing $digits")
    val pipes =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_| _ |_   ||_||_|\n" +
      "  ||_  _|  | _||_|  ||_| _ \n" +
      "                           "

    When("pipes are parsed")
    val result = BankOcr(pipes)

    Then(s"result is $digits ILL")
    result should be(s"$digits ILL")

  }

//  scenario("If the output is ERR or ILL and there is only one variation with a valid checksum, that number should be used") {
//
//    val digits = "711111111"
//
//    Given(s"Pipes are representing 111111111")
//    val pipes =
//      "                           \n" +
//      "  |  |  |  |  |  |  |  |  |\n" +
//      "  |  |  |  |  |  |  |  |  |\n" +
//      "                           "
//
//    When("pipes are parsed")
//    val result = BankOcr(pipes)
//
//    Then(s"result is $digits")
//    result should be(digits)
//
//  }

}

class BankOcrUnitTest extends FlatSpec with Matchers {

  "digitPipes" should "return pipes sequence for the specified digit" in {
    val expected = Seq(
      " _ ",
      "| |",
      "|_|",
      "   "
    )
    new BankOcr().digitPipes(0) should equal(expected)
  }

  "digit" should "return numeric representation of pipes" in {
    val actual = Seq(
      " _ ",
      "|_|",
      " _|",
      "   "
    )
    new BankOcr().digit(actual) should be("9")
  }

  it should "return question mark if pipes cannot be transformed" in {
    val actual = Seq(
      " _ ",
      "|_|",
      " _ ",
      "   "
    )
    new BankOcr().digit(actual) should be ("?")
  }

  "validChecksum" should "return true if checksum is correct ((9*d9+8*d8+7*d7 +..+1*d1) mod 11 == 0)" in {
    new BankOcr().validChecksum("123456789") should be (right = true)
  }

  it should "return true if checksum is incorrect ((9*d9+8*d8+7*d7 +..+1*d1) mod 11 == 0)" in {
    new BankOcr().validChecksum("123456780") should be (right = false)
  }

  "status" should "return ERR if checksum is incorrect" in {
    new BankOcr().status("123456780") should be ("ERR")
  }

  it should "return ILL if some digits are unknown" in {
    new BankOcr().status("1234567?0") should be ("ILL")
  }

  "parse" should "return 9 digits" in {
    val actual =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_||_|\n" +
      "  ||_  _|  | _||_|  ||_| _|\n" +
      "                           "
    new BankOcr().parse(actual) should have size 9
  }

  it should "return list with correct digits" in {
    val actual =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_||_|\n" +
      "  ||_  _|  | _||_|  ||_| _|\n" +
      "                           "
    new BankOcr().parse(actual) should be("123456789")
  }

  it should "return digits ending with ERR when checksum is incorrect" in {
    val actual =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_|| |\n" +
      "  ||_  _|  | _||_|  ||_||_|\n" +
      "                           "
    new BankOcr().parse(actual) should be("123456780 ERR")
  }

  "findVariations" should "find all possible digit variations" in {
    val actual =
      "   \n" +
      "  |\n" +
      "  |\n" +
      "   "
    new BankOcr().findVariations(actual) should have size 2
  }

  it should "return all possible digit variations" in {
    val actual =
      "   \n" +
      "  |\n" +
      "  |\n" +
      "   "
    new BankOcr().findVariations(actual) should equal(Set("1", "7"))
  }

}
