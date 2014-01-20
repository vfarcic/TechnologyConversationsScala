package com.wordpress.technologyconversations.learning.kata.solutions

object WordWrap {

  def wrap(text: String, maxLength: Int): String = {
    text.split(" ").foldLeft(Array[String]())(
      (out, in) => {
        if (out.size == 0 || (out.last + " " + in).length > maxLength) out :+ (" " + in).trim
        else out.updated(out.size - 1, out.last + " " + in)
      }
    ).mkString("\n")
  }

}
