import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object FutureTest extends App {
  implicit val ec: scala.concurrent.ExecutionContext = ExecutionContext.global
  val number: Future[Int] = Future {
    21
  }
  private val secondNumber: Future[Int] = Future(20)
  val result = number.onComplete {
    case Success(num) =>
      secondNumber.onComplete {
        case Success(num2) => println("Sum of two future :" + (num + num2))
        case Failure(exception) => println(exception)
      }
    case Failure(exception) => println(exception)
  }
  number.onComplete {
    case Success(value) => println("Single Future :" + value)
    case Failure(exception) => println(exception)
  }
  private val forResult = for {
    num <- number
    res = num * 2
  } yield res

  println("For Comprehension :" + Await.ready(forResult, 1.seconds))
  number.foreach(num => println("By Using For Each :" + num))

  //  private val newThreadPool = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())
  /* This is program of future that running parallel*/
  private val numberOne = Future(21)
  private val numberTwo = Future(19)
  private val parallelFuture = for {
    num <- numberOne
    num2 <- numberTwo
  } yield num + num2
  parallelFuture.onComplete {
    case Success(value) => println("Parallel Collection :" + value)
    case Failure(exception) => println(exception)
  }

  /* THis program is used to run the Future sequentially*/
  private val sequentialFuture = for {
    num <- Future(100)
    numTwo <- Future(200)
  } yield num + numTwo
  sequentialFuture.onComplete {
    case Success(value) => println("Sequentially :" + value)
    case Failure(exception) => println(exception)
  }
}
