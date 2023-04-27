import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object FutureFailMethod extends App {
  implicit val ec = ExecutionContext.global

  private def divisionOfNumber(numberOne: Int, numberTwo: Int): Future[Int] = {
    Future.failed(new ArithmeticException("Divide by zero"))
  }

  private val division = divisionOfNumber(2, 0)
  division.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }
}
