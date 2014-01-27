package com.wordpress.technologyconversations.learning.kata.solutions

object WordWrap {

  implicit class StringImprovements(s: String) {

    def wordWrap(maxLength: Int): String = {
      s.split(" ").foldLeft(Array(""))( (out, in) => {
        if ((out.last + " " + in).length > maxLength) out :+ in
        else out.updated(out.size - 1, out.last + " " + in)
      }).mkString("\n").trim
    }

  }

}
