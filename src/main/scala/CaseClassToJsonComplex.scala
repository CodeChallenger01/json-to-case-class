import com.knoldus.{CEO, Role}
import play.api.libs.json.{Json, OWrites}

case class People(name: String, middleName: Option[String], id: Int, phoneNumber: List[Long], address: String, achievements: Awards, role: Role)

object People {
  implicit val role: OWrites[Role] = Json.writes[Role]
  implicit val write: OWrites[People] = Json.writes[People]
}

case class Awards(awardOne: String, awardTwo: String)

object Awards {
  implicit val award: OWrites[Awards] = Json.writes[Awards]
}

object CaseClassToJsonComplex extends App {

  implicit val ceo: OWrites[CEO] = Json.writes[CEO]
  private val awards = Awards("Give Little Unexpected Extra", "Go 1%")
  private val employee = People("Manish Mishra", None, 1849, List(9897878987L, 8767676567L), "Noida", awards, CEO("Head"))
  private val parseToJson = Json.toJson(employee)
  println(parseToJson)
}
