package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{Matchers, FlatSpec}

/*
Source: http://codingdojo.org/cgi-bin/wiki.pl?KataWordWrap
You write a class called Wrapper, that has a single static function named wrap that takes two arguments, a string, and a column number.
The function returns the string, but with line breaks inserted at just the right places to make sure that no line is longer than the column number.
You try to break lines at word boundaries.

Like a word processor, break the line by replacing the last space in a line with a newline.
*/
class WordWrapTest extends FlatSpec with Matchers {

  "WordWrap#wrap" should "return wrapped text with small sample" in {
    val input = "This kata"
    val expected = "This\nkata"
    WordWrap.wrap(input, 4) should be (expected)
  }

  it should "return wrapped text" in {
    val input = "This kata should be easy unless there are hidden, or not so hidden, obstacles. Let's start!"
    val expected = "This kata\nshould be\neasy unless\nthere are\nhidden, or\nnot so\nhidden,\nobstacles.\nLet's start!"
    WordWrap.wrap(input, 12) should be (expected)
  }

}
