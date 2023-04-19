import JsonToScalaConverterTest.reads
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.Json

class JsonScalaConverterTest extends AnyFlatSpec with Matchers {

  "it " should "return the Output" in {
    val json =
      """
          {"name": "Manish Mishra",
        |"id": 1,
        |"address": "Gr.Noida"
          }
        """.stripMargin
    val parsedJsValue = Json.parse(json)
    val actualOutput = Json.fromJson[Person](parsedJsValue).toString
    val expectedOutput = "JsSuccess(Person(Manish Mishra,1,Gr.Noida),)"
    assert(actualOutput == expectedOutput)
  }

  "it " should "match with other detail " in {
    val json =
      """
          {"name": "Rohit Kumar",
        |"id": 32,
        |"address": "Noida"
          }
        """.stripMargin
    val parsedJsValue = Json.parse(json)
    val actualOutput = Json.fromJson[Person](parsedJsValue).toString
    val expectedOutput = "JsSuccess(Person(Rohit Kumar,32,Noida),)"
    assert(actualOutput == expectedOutput)
  }
}
