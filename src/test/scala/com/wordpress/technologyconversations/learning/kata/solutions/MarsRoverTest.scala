package com.wordpress.technologyconversations.learning.kata.solutions

import org.scalatest.{Matchers, FlatSpec}

/*
Source: http://dallashackclub.com/rover

Develop an api that moves a rover around on a grid.
* You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
* The rover receives a character array of commands.
* Implement commands that move the rover forward/backward (f,b).
* Implement commands that turn the rover left/right (l,r).
* Implement wrapping from one edge of the grid to another. (planets are spheres after all)
* Implement obstacle detection before each move to a new square.
If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.
*/
class MarsRoverTest extends FlatSpec with Matchers {

  "Planet" can "define its size" in {
    val mars = Planet(5, 6)
    mars.sizeX should be (5)
    mars.sizeY should be (6)
  }

  "Rover" should "accept starting point (X,Y) of a rover and the direction (N,S,E,W) it is facing" in {
    val rover = Rover(12, 42, 'E')
    rover.x should be (12)
    rover.y should be (42)
    rover.direction should be ('E')
  }

  it should "be able to move forward (f)" in {
    val rover = Rover(12, 42, 'E')
    rover.sendCommand(Array('f'))
    rover.x should be (13)
    rover.y should be (42)
  }

  it should "be able to move backward (b)" in {
    val rover = Rover(12, 42, 'E')
    rover.sendCommand(Array('b'))
    rover.x should be (11)
    rover.y should be (42)
  }

  it should "be able to turn left (l)" in {
    val rover = Rover(12, 42, 'E')
    rover.sendCommand(Array('l'))
    rover.direction should be ('N')
  }

  it should "be able to receive a character array of commands" in {
    val rover = Rover(12, 42, 'E')
    rover.sendCommand(Array('f', 'l', 'f'))
    rover.x should be (13)
    rover.y should be (43)
    rover.direction should be ('N')
  }

  it should "wrap from one edge of the grid to another" in {
    val rover = Rover(1, 1, 'E', Planet(3, 3))
    rover.sendCommand(Array('f', 'f', 'f'))
    rover.x should be (1)
    rover.sendCommand(Array('r', 'f', 'f', 'f'))
    rover.y should be (1)
  }

}
