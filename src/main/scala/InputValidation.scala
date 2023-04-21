import scala.io.StdIn.readLine

trait Validator[A] {
  def validate(input: A): Either[Throwable, String]
}

case class StringValidation(userInput: String, maxLength: Int)

object StringValidation {
  implicit val string = new Validator[StringValidation] {
    override def validate(input: StringValidation): Either[Throwable, String] = {
      val content = input.userInput
      content match {
        case value if value.isEmpty || content.length > input.maxLength => Left(new RuntimeException("Either String is Empty or Length Exceeds"))
        case value if value.contains("@") || value.contains("$") || value.contains("#") => Left(new Exception("Containing Special Character"))
        case value => Right(s"Valid String : $value")
      }
    }
  }
}

case class NumberValidation(userInput: Int, minRange: Int, maxRange: Int)

object NumberValidation {
  implicit val number = new Validator[NumberValidation] {
    override def validate(input: NumberValidation): Either[Throwable, String] = {
      val content = input.userInput

      content match {
        case value if value.isNaN || content < input.minRange || content > input.maxRange => Left(new RuntimeException("Numeric value is not within range"))
        case value => Right(s"Valid Number: $value")
      }
    }
  }
}

object MainInput extends App {
  /* Checking For String Input */
  private val inputOne = readLine("Enter the String :")
  private val string: StringValidation = StringValidation(inputOne, 10)
  private val isStringValidated = implicitly[Validator[StringValidation]].validate(string)
  private val resultOfString = isStringValidated match {
    case Left(value) => s"$value"
    case Right(value) => s"$value"
  }
  println(resultOfString)

  /* Checking For Numeric Input */
  private val inputTwo = readLine("\nEnter the Number :").toInt
  private val number: NumberValidation = NumberValidation(inputTwo, 10, 1000)
  private val isNumberValidated = implicitly[Validator[NumberValidation]].validate(number)
  private val resultOfNumber = isNumberValidated match {
    case Left(value) => s"$value"
    case Right(value) => s"$value"
  }
  println(resultOfNumber)

}
