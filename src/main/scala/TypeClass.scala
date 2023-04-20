trait Shape[A] {
  def area(a: A): Int
}

case class Square(side: Int)

object Square {
  implicit val shape = new Shape[Square] {
    override def area(square: Square): Int = square.side * square.side
  }
}

case class Rectangle(length: Int, breadth: Int)

object Rectangle {
  implicit val shape = new Shape[Rectangle] {
    override def area(rectangle: Rectangle): Int = rectangle.length * rectangle.breadth
  }
}

object CalcArea {
  def calcArea[T](t: T)(implicit S: Shape[T]): Int = {
    S.area(t)
  }
}

object MainTest extends App {

  private val squareOne = Square(10)
  val result = CalcArea.calcArea[Square](squareOne)
  println(result)

  private val square = Square(21)
  private val squareArea = implicitly[Shape[Square]].area(square)
  println(squareArea)

  private val rectangle = Rectangle(12, 10)
  private val rectangleArea = implicitly[Shape[Rectangle]].area(rectangle)
  println(rectangleArea)
}
