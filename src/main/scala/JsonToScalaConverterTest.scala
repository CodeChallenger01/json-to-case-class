import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class Person(name: String, id: Int, address: String)

object JsonToScalaConverterTest {
  implicit val reads: Reads[Person] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "id").read[Int] and
      (JsPath \ "address").read[String]
    )(Person.apply _)
}

