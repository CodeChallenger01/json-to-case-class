import play.api.libs.json.Json

object Main extends App {
  implicit val residentWrites = Json.writes[Person]
  private val person = Person(name = "Manish Mishra", id = 21, address = "Noida")
  private val personJson = Json.toJson(person)
  println(personJson)
}
