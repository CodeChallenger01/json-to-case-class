import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue, Json, Reads}
case class Employee(name: String, middleName: Option[String], id: Int, phoneNumber: List[Long], address: String, achievements: Awards)
object JsonToCaseClassComplex extends App {
  implicit val award: Reads[Awards] = Json.reads[Awards]
  implicit val employee: Reads[Employee] = Json.reads[Employee]
  private val employeeDetail: JsValue = Json.parse {
    """{
      |"name" : "Manish Mishra",
      |"id" : 1849,
      |"phoneNumber" : [9897878987,8767676567],
      |"address" : "Noida",
      |"achievements" :{"awardOne":" Give Little Unexpected Extra","awardTwo":" Go 1%"}
      |}""".stripMargin
  }
  private val jsonToCaseClass: JsResult[Employee] = Json.fromJson[Employee](employeeDetail)
  val result = jsonToCaseClass match {
    case JsSuccess(value, path) => s"Name: ${value.name}\nMiddleName :${value.middleName}\nId :${value.id}\nPhone Number :${value.phoneNumber}\nAddress :${value.address}\nAchievements\t1 :${value.achievements.awardOne}\t2: ${value.achievements.awardTwo}"
    case JsError(errors) => s"Error : ${JsError.toJson(errors).toString}"
  }
  println(result)
}
