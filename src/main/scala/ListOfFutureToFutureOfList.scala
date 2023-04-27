import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object ListOfFutureToFutureOfList extends App {
  implicit val ec = ExecutionContext.global
  private val listOfFuture = List(Future(21), Future(10), Future(11))
  private val list = for {
    future <- listOfFuture
    res = Await.result(future, 1.seconds)
  } yield res
  println(list)
  private val futureOfList = Future {
    list
  }
  futureOfList.onComplete {
    case Success(value) => value.map(num => num)
    case Failure(exception) => exception.getMessage
  }
}
