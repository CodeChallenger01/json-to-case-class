import com.knoldus.Employee
import play.api.libs.json.{JsError, JsPath, JsResult, JsSuccess, JsValue, Json}
import com.knoldus.Conversion.residentReads


object JsonToCaseClass extends App {
  private val jsonString: JsValue = Json.parse(
    """{
      "name" : "Manish Mishra",
      "age" : 21,
      "id" : 1849,
      "address" : "Noida"
    }"""
  )
  private val check: JsResult[Employee] = Json.fromJson[Employee](jsonString)
  check match {
    case JsSuccess(r: Employee, path: JsPath) => println("Name: " + r.name + "\nAge: " + r.age + "\nID: " + r.id + "\nAddress :" + r.address )
    case e@JsError(_) => println("Errors: " + JsError.toJson(e).toString())
  }
}

