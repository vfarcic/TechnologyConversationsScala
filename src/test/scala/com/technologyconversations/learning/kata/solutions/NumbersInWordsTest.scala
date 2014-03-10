package com.technologyconversations.learning.kata.solutions

import org.scalatest.{GivenWhenThen, FeatureSpec, Matchers, FlatSpec}

/*
It occurs now and then in real life that people want to write about money, especially about a certain amount of money.
If it comes to cheques or contracts for example some nations have laws that state that you should write out the amount in words additionally to the amount in numbers to avoid fraud and mistakes.
So if you want to transfer 745 $ to someone via cheque you have to fill out to fields:

745.00 (amount in numbers)

seven hundred forty five (amount in words)

Assumptions:
* Cheques do not accept more than 7 digits so 9.999.999 (nine million nine hundred ninety nine thousand nine hundred ninety nine) would be the upper limit.
* Cents are not used.

Solution should be able to convert in both directions (numbers to words and words to numbers).

 */
class NumbersInWordsSpec extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Numbers can be converted to words") {

    Given("amount in numbers is 1234567.00")
    val amountNumbers = 1234567

    When("amount in numbers is converted to amount in words")
    val amountWords = NumbersInWords().numbersToWords(amountNumbers)

    Then("amount in words is one million two hundred thirty four thousand five hundred sixty seven")
    amountWords should equal("one million two hundred thirty four thousand five hundred sixty seven")

  }

  scenario("Words can be converted to numbers") {

    Given("amount in words is one million two hundred thirty four thousand five hundred sixty seven")
    val amountWords = "one million two hundred thirty four thousand five hundred sixty seven"

    When("amount in numbers is converted to amount in words")
    val amountNumbers = NumbersInWords().wordsToNumbers(amountWords)

    Then("amount in words is 1234567")
    amountNumbers should equal(1234567)

  }

}

class NumbersInWordsUnitTest extends FlatSpec with Matchers {

  "numbersMap" should "have values from 1 to 9" in {
    NumbersInWords().numbersMap(1) should equal("one")
    NumbersInWords().numbersMap(9) should equal("nine")
  }

  it should "have values from 10 to 19" in {
    NumbersInWords().numbersMap(10) should equal("ten")
    NumbersInWords().numbersMap(19) should equal("nineteen")
  }

  it should "have values 20, 30, 40,.., 90 " in {
    NumbersInWords().numbersMap(20) should equal("twenty")
    NumbersInWords().numbersMap(90) should equal("ninety")
  }

  it should "have values 100, 1.000 and 1.000.000 " in {
    NumbersInWords().numbersMap(100) should equal("hundred")
    NumbersInWords().numbersMap(1000) should equal("thousand")
    NumbersInWords().numbersMap(1000000) should equal("million")
  }

  "numbersToWords" should "return empty string if amount is 0" in {
    NumbersInWords().numbersToWords(0) should equal("")
  }

  it should "return empty string if amount is negative" in {
    NumbersInWords().numbersToWords(-99) should equal("")
  }

  it should "return one million if amount is 1.000.000" in {
    NumbersInWords().numbersToWords(1000000) should equal("one million")
  }

  it should "return three million if amount is 3.000.000" in {
    NumbersInWords().numbersToWords(3000000) should equal("three million")
  }

  it should "return four thousand if amount is 4.000" in {
    NumbersInWords().numbersToWords(4000) should equal("four thousand")
  }

  it should "return one thousand if amount is 1.000" in {
    NumbersInWords().numbersToWords(1000) should equal("one thousand")
  }

  it should "return five hundred if amount is 500" in {
    NumbersInWords().numbersToWords(500) should equal("five hundred")
  }

  it should "return one hundred if amount is 100" in {
    NumbersInWords().numbersToWords(100) should equal("one hundred")
  }

  it should "return sixty if amount is 60" in {
    NumbersInWords().numbersToWords(60) should equal("sixty")
  }

  it should "return twenty if amount is 20" in {
    NumbersInWords().numbersToWords(20) should equal("twenty")
  }

  it should "return seventeen if amount is 17" in {
    NumbersInWords().numbersToWords(17) should equal("seventeen")
  }

  it should "return eight if amount is 8" in {
    NumbersInWords().numbersToWords(8) should equal("eight")
  }

  it should "return seven hundred forty five if amount is 745" in {
    NumbersInWords().numbersToWords(745) should equal("seven hundred forty five")
  }

  it should "return one million two hundred thirty four thousand five hundred sixty seven if amount is 1.234.567" in {
    NumbersInWords().numbersToWords(1234567) should equal("one million two hundred thirty four thousand five hundred sixty seven")
  }

  it should "return nine million nine hundred ninety nine thousand nine hundred ninety nine if amount is 9.999.999" in {
    NumbersInWords().numbersToWords(9999999) should equal("nine million nine hundred ninety nine thousand nine hundred ninety nine")
  }

  "wordsMap" should "have values from one to nine" in {
    NumbersInWords().wordsMap("one") should equal(1)
    NumbersInWords().wordsMap("nine") should equal(9)
  }

  it should "have values from 10 to 19" in {
    NumbersInWords().wordsMap("ten") should equal(10)
    NumbersInWords().wordsMap("nineteen") should equal(19)
  }

  it should "have values 20, 30, 40,.., 90 " in {
    NumbersInWords().wordsMap("twenty") should equal(20)
    NumbersInWords().wordsMap("ninety") should equal(90)
  }

  it should "have values 100, 1.000 and 1.000.000 " in {
    NumbersInWords().wordsMap("hundred") should equal(100)
    NumbersInWords().wordsMap("thousand") should equal(1000)
    NumbersInWords().wordsMap("million") should equal(1000000)
  }

  "wordsToNumbers" should "return 0 if amount is empty string" in {
    NumbersInWords().wordsToNumbers("") should equal(0)
  }

  it should "return 0 if amount is incorrect" in {
    NumbersInWords().wordsToNumbers("this is not correct") should equal(0)
  }

  it should "return 1.000.000 if amount is one million" in {
    NumbersInWords().wordsToNumbers("one million") should equal(1000000)
  }

  it should "return 3.000.000 if amount is three million" in {
    NumbersInWords().wordsToNumbers("three million") should equal(3000000)
  }

  it should "return 4.000 if amount is four thousand" in {
    NumbersInWords().wordsToNumbers("four thousand") should equal(4000)
  }

  it should "return 1.000 if amount is one thousand" in {
    NumbersInWords().wordsToNumbers("one thousand") should equal(1000)
  }

  it should "return 500 if amount is five hundred" in {
    NumbersInWords().wordsToNumbers("five hundred") should equal(500)
  }

  it should "return 100 if amount is one hundred" in {
    NumbersInWords().wordsToNumbers("one hundred") should equal(100)
  }

  it should "return 60 if amount is sixty" in {
    NumbersInWords().wordsToNumbers("sixty") should equal(60)
  }

  it should "return 20 if amount is twenty" in {
    NumbersInWords().wordsToNumbers("twenty") should equal(20)
  }

  it should "return 17 if amount is seventeen" in {
    NumbersInWords().wordsToNumbers("seventeen") should equal(17)
  }

  it should "return 8 if amount is eight" in {
    NumbersInWords().wordsToNumbers("eight") should equal(8)
  }

  it should "return 745 if amount is seven hundred forty five" in {
    NumbersInWords().wordsToNumbers("seven hundred forty five") should equal(745)
  }

  it should "return 1.234.567 if amount is one million two hundred thirty four thousand five hundred sixty seven" in {
    NumbersInWords().wordsToNumbers("one million two hundred thirty four thousand five hundred sixty seven") should equal(1234567)
  }

  it should "return 9.999.999 if amount is nine million nine hundred ninety nine thousand nine hundred ninety nine" in {
    NumbersInWords().wordsToNumbers("nine million nine hundred ninety nine thousand nine hundred ninety nine") should equal(9999999)
  }

}
