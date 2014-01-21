package com.wordpress.technologyconversations.learning.kata.solutions

object WordWrap {

  def wrap(text: String, maxLength: Int): String = {
    text.split(" ").foldLeft(Array(""))( (out, in) => {
      if ((out.last + " " + in).trim.length > maxLength) out :+ (" " + in).trim
      else out.updated(out.size - 1, (out.last + " " + in).trim)
    }).mkString("\n")
  }
}
