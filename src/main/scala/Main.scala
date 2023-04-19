import JsonToScalaConverterTest.reads
import play.api.libs.json.Json

object Main extends App {
  val json =
    """
        {"name": "Manish Mishra",
      |"id": 1,
      |"address": "Gr.Noida"
        }
      """.stripMargin
  private val parsedJsValue = Json.parse(json)
  private val parsed = Json.fromJson[Person](parsedJsValue)
  println(parsed)
}
