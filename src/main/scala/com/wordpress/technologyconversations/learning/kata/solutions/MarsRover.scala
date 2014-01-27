package com.wordpress.technologyconversations.learning.kata.solutions

class Rover(val coordinates: Coordinates, var direction: Char, val planet: Planet) {

  val compass = Seq('N', 'E', 'S', 'W')

  def sendCommands(commands: String): String = {
    def sendCommands(commands: List[Char], performed: String): String = {
      commands.head match {
        case 'f' => if(!move(1)) return performed
        case 'b' => if(!move(-1)) return performed
        case 'l' => turn(-1)
        case 'r' => turn(1)
      }
      if (commands.tail == Nil) performed + commands.head.toString
      else sendCommands(commands.tail, performed + commands.head.toString)
    }
    val performed = sendCommands(commands.toList, "")
    (if (performed == commands) "OK" else "NOK") + ": " + performed
  }

  private def move(stepDirection: Int): Boolean = {
    val newCoordinates = coordinates.step(stepDirection, direction, planet.coordinates)
    if (planet.hasObstacle(newCoordinates)) false
    else { coordinates.update(newCoordinates); true }
  }

  private def turn(turnDirection: Int) {
    direction = compass((4 + compass.indexOf(direction) + turnDirection) % 4)
  }

}
object Rover {
  def apply(initialX: Int, initialY: Int, direction: Char, planet: Planet = Planet()) = {
    new Rover(Coordinates(initialX, initialY), direction, planet)
  }
}

class Planet(val coordinates: Coordinates = Coordinates(100, 100), obstacles: List[Coordinates] = List()) {
  def hasObstacle(location: Coordinates): Boolean = {
    for (coordinates <- obstacles) {
      if (coordinates.x == location.x && coordinates.y == location.y) {
        return true
      }
    }
    false
  }
}
object Planet {
  def apply(maxX: Int = 100, maxY: Int = 100, obstacles: List[Coordinates] = List()) = {
    new Planet(Coordinates(maxX, maxY), obstacles)
  }
}

class Coordinates(val x: Point, val y: Point) {
  def step(stepDirection: Int, direction: Char, planetCoordinates: Coordinates): Coordinates = {
    var stepX, stepY = 0
    direction match {
      case 'N' => stepY = 1
      case 'S' => stepY = -1
      case 'E' => stepX = 1
      case 'W' => stepX = -1
    }
    Coordinates(
      x.step(stepDirection, stepX, planetCoordinates.x.location),
      y.step(stepDirection, stepY, planetCoordinates.y.location))
  }
  def update(coordinates: Coordinates) {
    x.location = coordinates.x.location
    y.location = coordinates.y.location
  }
}
object Coordinates {
  def apply(x: Int, y: Int) = new Coordinates(new Point(x), new Point(y))
}

class Point(var location: Int) {
  def step(direction: Int, step: Int, max: Int): Int = (max + location + step * direction) % max
  override def equals(that: Any): Boolean = this.hashCode() == that.hashCode()
  override def hashCode = location.hashCode
}