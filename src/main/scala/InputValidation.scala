import com.knoldus.{Error, NumberTooLong, StringContainNumber, StringContainSpecialCharacter, StringTooLong}

import scala.io.StdIn.readLine

trait Validator[A] {
  def validate(input: A): Either[Error, String]
}

case class StringValidation(userInput: String, maxLength: Int)

object StringValidation {
  implicit val string = new Validator[StringValidation] {
    override def validate(input: StringValidation): Either[Error, String] = {
      val content = input.userInput
      content match {
        case value if value.isEmpty || content.length > input.maxLength => Left(StringTooLong("String is Too Long"))
        case value if value.contains("@") || value.contains("$") || value.contains("#") => Left(StringContainSpecialCharacter("String Containing Special Character"))
        case value => Right(s"Validated String : $value")
      }
    }
  }
}

case class NumberValidation(userInput: Int, minRange: Int, maxRange: Int)

object NumberValidation {
  implicit val number = new Validator[NumberValidation] {
    override def validate(input: NumberValidation): Either[Error, String] = {
      val content = input.userInput
      content match {
        case value if value.isNaN || content < input.minRange || content > input.maxRange => Left(NumberTooLong(" Number is out of Range "))
        case value => Right(s"Valid Number: $value")
      }
    }
  }
}

object InputValidation {
  def inputValidate[T](input: T)(implicit S: Validator[T]): Either[Error, String] = {
    S.validate(input)
  }
}

object MainInput extends App {
  /* Checking For String Input */
  private val inputOne = readLine("Enter the String :")
  private val string: StringValidation = StringValidation(inputOne, 20)
  private val isStringValidated = InputValidation.inputValidate(string)
  private val resultOfString = isStringValidated match {
    case Right(value) => s"$value"
    case Left(value) => value match {
      case StringContainSpecialCharacter(message) => s"$message"
      case StringTooLong(message) => s"$message"
      case StringContainNumber(message) => s"$message"
    }
  }
  println(resultOfString)

  /* Checking For Numeric Input */
  private val inputTwo = readLine("\nEnter the Number :").toInt
  private val number: NumberValidation = NumberValidation(inputTwo, 10, 1000)
  private val isNumberValidated = InputValidation.inputValidate(number)
  private val resultOfNumber = isNumberValidated match {
    case Right(value) => s"$value"
    case Left(value) => value match {
      case NumberTooLong(message) => s"$message"
    }
  }
  println(resultOfNumber)

}