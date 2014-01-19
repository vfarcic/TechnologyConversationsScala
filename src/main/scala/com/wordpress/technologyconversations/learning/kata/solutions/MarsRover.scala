package com.wordpress.technologyconversations.learning.kata.solutions

class Rover(var x: Int, var y: Int, var direction: Char, val planet: Planet) {

  def sendCommand(commands: Array[Char]) {
    for(command <- commands) {
      command match {
        case 'f' => move(1)
        case 'b' => move(-1)
        case 'l' => direction = position.turnLeft
        case 'r' => direction = position.turnRight
      }
    }
  }

  private def position = direction match {
    case 'N' => DirectionOptions(0, 1, 'W', 'E')
    case 'S' => DirectionOptions(0, -1, 'E', 'W')
    case 'E' => DirectionOptions(1, 0, 'N', 'S')
    case 'W' => DirectionOptions(-1, 0, 'S', 'E')
  }

  private def move(stepDirection: Int) {
    x = (x + position.stepX * stepDirection) % planet.sizeX
    if (x <= 0) x = planet.sizeX - x
    y = (y + position.stepY * stepDirection) % planet.sizeY
    if (y <= 0) y = planet.sizeY - y
  }

  private case class DirectionOptions(stepX: Int, stepY: Int, turnLeft: Char, turnRight: Char)

}
object Rover {
  def apply(x: Int, y: Int, location: Char, planet: Planet = Planet()) = new Rover(x, y, location, planet)
}

case class Planet(sizeX: Int = 100, sizeY: Int = 100)
