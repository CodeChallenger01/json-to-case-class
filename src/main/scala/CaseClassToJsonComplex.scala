import play.api.libs.json.{Json, OWrites}

case class Employee(name: String, middleName: Option[String], id: Int, phoneNumber: List[Long], address: String, achievements: Awards)

object Employee {
  implicit val write: OWrites[Employee] = Json.writes[Employee]
}

case class Awards(awardOne: String, awardTwo: String)

object Awards {
  implicit val award: OWrites[Awards] = Json.writes[Awards]
}

object CaseClassToJsonComplex extends App {
  private val awards = Awards("Give Little Unexpected Extra", "Go 1%")
  private val employee = Employee("Manish Mishra",None, 1849, List(9897878987L, 8767676567L), "Noida", awards)
  private val parseToJson = Json.toJson(employee)
  println(parseToJson)
}
