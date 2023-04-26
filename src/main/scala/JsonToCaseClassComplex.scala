import com.knoldus.{CEO, Role}
import play.api.libs.json._

case class Employee(name: String, middleName: Option[String], id: Int, phoneNumber: List[Long], address: String, achievements: Awards, role: Role)

object JsonToCaseClassComplex extends App {
  implicit val ceo: Reads[CEO] = Json.reads[CEO]
  implicit val role: Reads[Role] = Json.reads[Role]
  implicit val award: Reads[Awards] = Json.reads[Awards]
  implicit val employee: Reads[Employee] = Json.reads[Employee]
  private val employeeDetail: JsValue = Json.parse {
    """{
      |"name" : "Manish Mishra",
      |"id" : 1849,
      |"phoneNumber" : [9897878987,8767676567],
      |"address" : "Noida",
      |"achievements" :{"awardOne":" Give Little Unexpected Extra","awardTwo":" Go 1%"},
      |"role":"Role"
      |}""".stripMargin
  }
  private val jsonToCaseClass: JsResult[Employee] = Json.fromJson[Employee](employeeDetail)
  val result = jsonToCaseClass match {
    case JsSuccess(value, path) =>
      s"""Name: ${value.name}
         |MiddleName :${value.middleName}
         |Id :${value.id}\nPhone Number :${value.phoneNumber}
         |Address :${value.address}
         |Achievements\t1 :${value.achievements.awardOne}\t2: ${value.achievements.awardTwo}
         |Role : ${value.role.toString}
         |""".stripMargin
    case JsError(errors) => s"Error : ${JsError.toJson(errors).toString}"
  }
  println(result)
}
