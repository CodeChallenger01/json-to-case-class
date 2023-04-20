
import play.api.libs.json.{Json, OWrites}

case class Person(name: String, id: Int, address: String)

object Person {
  implicit val residentWrites: OWrites[Person] = Json.writes[Person]
}

object Main extends App {
  private val person = Person(name = "Manish Mishra", id = 21, address = "Noida")
  private val personJson = Json.toJson(person)
  println(personJson)
}
