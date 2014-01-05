package com.wordpress.technologyconversations.learning.solutions

import com.wordpress.technologyconversations.learning.specs.UnitSpec

class Lesson11RegEx extends UnitSpec {

  "RegEx" can "be constructed with the r method of the string class" in {
    val pattern = "[0-9]+".r
    val firstMatch = pattern.findFirstIn("Rainy Street 32").mkString
    firstMatch should be ("32")
  }

  it can "be used with raw string syntax (\"\"\"...\"\"\")" in {
    val pattern = """\s+Street\s+""".r
    val firstMatch = pattern.findFirstIn("Rainy Street 32").mkString
    firstMatch should be (" Street ")
  }

  it can "be used with findAllIn to get an iterator" in {
    val pattern = "[0-9]+".r
    val matches = pattern.findAllIn("Rainy Street 32, floor 5").toArray
    matches(1) should be ("5")
  }

  it can "be used with findFirstIn to get the first match as an Option" in { // Options will be explained later
    val pattern = "[0-9]+".r
    val firstMatch = pattern.findFirstIn("Rainy Street 32, floor 5").mkString
    firstMatch should be ("32")
  }

  it can "be used with findPrefixOf to check whether the beginning of the string matches" in { // Options will be explained later
    val pattern = """[0-9]+""".r
    val matchPrefix = pattern.findPrefixOf("08904, Rainy Street 32, floor 5").mkString
    matchPrefix should be ("08904")
  }

  it can "be used to replace the first match" in {
    val pattern = """[0-9]+""".r
    val replaced = pattern.replaceFirstIn("08904, Rainy Street 32, floor 5", "UNKNOWN")
    replaced should be ("UNKNOWN, Rainy Street 32, floor 5")
  }

  it can "be used to replace all matches" in {
    val pattern = """[0-9]+""".r
    val replaced = pattern.replaceAllIn("08904, Rainy Street 32, floor 5", "UNKNOWN")
    replaced should be ("UNKNOWN, Rainy Street UNKNOWN, floor UNKNOWN")
  }

  "RegEx group" should "be created with parentheses" in {
    val pattern = "([a-z|A-Z]+)".r
    // TODO Finish
  }

}
