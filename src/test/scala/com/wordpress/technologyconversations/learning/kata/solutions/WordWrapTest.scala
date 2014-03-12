package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{Matchers, FlatSpec}
import com.wordpress.technologyconversations.learning.kata.solutions.WordWrap._

/*
Source: http://codingdojo.org/cgi-bin/wiki.pl?KataWordWrap
You write a class called Wrapper, that has a single static function named wrap that takes two arguments, a string, and a column number.
The function returns the string, but with line breaks inserted at just the right places to make sure that no line is longer than the column number.

Like a word processor, break the line by replacing the last space in a line with a newline.

Solution can assume that no word is longer than the maximum number characters in a line.
*/
class WordWrapTest extends FlatSpec with Matchers {

  "WordWrap" should "return empty string is no text is specified" in {
    "".wordWrap(10) should be ("")
  }

  it should "work with a single word" in {
    "kata".wordWrap(4) should be ("kata")
  }

  it should "return wrapped text with small sample" in {
    val input = "This kata"
    val expected = "This\nkata"
    input.wordWrap(4) should be (expected)
  }

  it should "return wrapped text" in {
    val input = "This kata should be easy unless there are hidden, or not so hidden, obstacles. Let's start!"
    val expected = "This kata\nshould be\neasy unless\nthere are\nhidden, or\nnot so\nhidden,\nobstacles.\nLet's start!"
    input.wordWrap(12) should be (expected)
  }

  it should "return the same text if max length is the same as the length of the text" in {
    val input = "Lorem ipsum dolor sit amet."
    val expected = "Lorem ipsum dolor sit amet."
    input.wordWrap(input.length) should be (expected)
  }

}
