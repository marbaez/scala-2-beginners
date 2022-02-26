package exercises.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailureMine extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)
  println(Try(aFailure).orElse(Try("hola")))
}
