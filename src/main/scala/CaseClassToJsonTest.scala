import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{Reads, __}

case class Person(name: String, id: Int, address: String)

object JsonToScalaConverterTest {
  implicit val reads: Reads[Person] = (
    (__ \ "name").read[String] and
      (__ \ "id").read[Int] and
      (__ \ "address").read[String]
    )(Person)
}
