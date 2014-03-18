package com.technologyconversations.learning.kata.solutions

import org.scalatest.{FlatSpec, Matchers}
import ReversePolishNotation._

/*
In reverse Polish notation the operators follow their operands;
for instance, to add 3 and 4, one would write "3 4 +" rather than "3 + 4".
If there are multiple operations, the operator is given immediately after its second operand;
so the expression written "3 - 4 + 5" in conventional notation would be written "3 4 - 5 +" in RPN: 4 is first subtracted from 3, then 5 added to it.
An advantage of RPN is that it obviates the need for parentheses that are required by infix.
While "3 - 4 * 5" can also be written "3 - (4 * 5)", that means something quite different from "(3 - 4) * 5".
In postfix, the former could be written "3 4 5 * -", which unambiguously means "3 (4 5 *) -" which reduces to "3 20 -";
the latter could be written "3 4 - 5 *" (or 5 3 4 - *, if keeping similar formatting), which unambiguously means "(3 4 -) 5 *".

Taken from http://en.wikipedia.org/wiki/Reverse_Polish_notation.
 */
class ReversePolishNotationTest extends FlatSpec with Matchers {

  "calculateFunction" should "return stack with 2 elements popped and one element pushed" in {
    calcSign(List[Double](1, 2, 3, 4, 5), _ / _) should have size 4
  }

  it should "be use function parameter to calculate value that is pushed" in {
    calcSign(List[Double](1, 2, 3, 15, 3), _ / _).last should equal(5)
  }

  "calculate" should "be able to calculate single digit numbers" in {
    "1 2 +".calc should equal(3)
  }

  it should "be able to calculate multi digit numbers" in {
    "12 3 /".calc should equal(4)
  }

  it should "be able to handle negative numbers" in {
    "-12 3 /".calc should equal(-4)
  }

  it should "be able to handle decimal numbers" in {
    "-12.9 3 /".calc should equal(-4.3)
  }

  it should "be able to calculate more complex notations" in {
    "1 2 + 4 * 5 + 3 -".calc should equal(14)
  }

}
