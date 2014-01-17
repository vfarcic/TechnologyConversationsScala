package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{GivenWhenThen, FeatureSpec, Matchers, FlatSpec}

object Permutations {
  def apply(text: String) = text.toCharArray.permutations.toArray.map(_.mkString)
}

class PermutationsTest extends FeatureSpec with GivenWhenThen with Matchers {

  scenario("Program returns all permutations of a given string") {

    When("permutation of a string ABC is performed")
    val perm = Permutations("ABC")

    Then("the result has 6 permutations")
    perm should have size 6

    Then("the result contains permutation ABC")
    perm should contain ("ABC")

    Then("the result contains permutation ACB")
    perm should contain ("ACB")

    Then("the result contains permutation BAC")
    perm should contain ("BAC")

    Then("the result contains permutation BCA")
    perm should contain ("BCA")

    Then("the result contains permutation CAB")
    perm should contain ("CAB")

    Then("the result contains permutation CBA")
    perm should contain ("CBA")

  }

}
