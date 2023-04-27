import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}

object EitherOfStringsToFutureOfString extends App {
  implicit val ex = ExecutionContext.global

  private def converter(result: Either[String, String]): Future[String] = {
    Future {
      result match {
        case Left(value) => value
        case Right(value) => value
      }
    }
  }

  val result: Future[String] = converter(Left("Hello Manish"))
  println(result)

  /* Other Approach*/
  implicit def otherWayToConvert(result: Either[String, String]): Future[String] = {
    Future {
      result match {
        case Left(value) => throw new ArithmeticException("Error")
        case Right(value) => value
      }
    }
  }

  private val futureToString: Future[String] = Left("This is implicit way to convert")
  Await.ready(futureToString, 1.seconds)
  println(futureToString)

}
