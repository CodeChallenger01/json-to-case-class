trait Volume[T] {
  def volume(a: T): Int
}

case class Cuboid(length: Int, breadth: Int, height: Int)

object Cuboid {
  implicit val volume = new Volume[Cuboid] {
    override def volume(cuboid: Cuboid): Int = cuboid.length * cuboid.breadth * cuboid.height
  }
}

object CalcVolume {
  def calcVolume[T](t: T)(implicit S: Volume[T]): Int = {
    S.volume(t)
  }
}

object MainVolume extends App {
  private val cuboidVolume = Cuboid(10, 21, 10)
  val result = CalcVolume.calcVolume(cuboidVolume)
  println(result)

}
