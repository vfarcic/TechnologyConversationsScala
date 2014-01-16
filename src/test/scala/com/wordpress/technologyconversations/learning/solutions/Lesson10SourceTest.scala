package com.wordpress.technologyconversations.learning.solutions

import scala.io.Source
import org.scalatest.{Matchers, FlatSpec}

// TODO Publish
class Lesson10SourceTest extends FlatSpec with Matchers {

  "scala.io.Source" can "be used to read all lines from a file (or other sources)" in {
    val source = Source.fromFile("test_data/Lesson10FilesLines.txt")
    val iterator = source.getLines()
    var lineNumber = 1
    for (line <- iterator) {
      line should be ("This is line number " + lineNumber)
      lineNumber += 1
    }
  }

  it can "be used to convert file content to a string" in {
    val source = Source.fromFile("test_data/Lesson10Files.txt")
    source.mkString should be ("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
  }

}
