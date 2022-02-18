package exercises.part2oop

import java.lang
import java.lang.Integer

object ExceptionsMine extends App {
  val x : String = null

  // println(x.length) --> NullPointerException

  // throwing and caching exceptions
  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extends form Throwable class
  // Exception and Error are the major Throwable subtypes


  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("no int for you")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will be executed no mather what
    // does not influence the return type of this expression
    println("finally")
  }

  println(potentialFail)

  // 3. fow to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  // throw exception

  /*
  1. Crash your program with an OutOfMemoryError
  2. Crash with SOError
  3. PocketCalculator
    - add(x,y
    - substract(x,y)
   */
   class OverflowException extends Exception
   class UnderflowException extends Exception
   class MathCalculationException extends Exception

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if (x + y > Int.MaxValue) throw new OverflowException
      else return x + y
    }

    def subtract(x: Int, y: Int): Int = {
      if (x - y < Int.MinValue) throw new UnderflowException
      else x - y
    }

    def multiply(x: Int, y: Int): Int =
      if (x * y > Int.MaxValue) throw new OverflowException
      else x * y

    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException
      else x % y
  }
}
