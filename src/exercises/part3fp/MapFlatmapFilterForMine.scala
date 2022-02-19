package exercises.part3fp

import exercises.{Cons, Empty}

object MapFlatmapFilterForMine extends App {
  val list = List(1,2,3)
  println(list)

  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)

  println(list.flatMap(toPair))

  // Exercise: print all the combinations between two lists
  val numbers = List(1,2,3,4)
  val letters = List("a","b","c","d")
  val colors = List("black", "white")
  
  println({
    numbers.flatMap(x => letters.map(y => x+y))
  })

  // foreach
  list.foreach(println)

  // for ccmoprehensions
  val forComprehension = for {
    n <- numbers if n % 2 == 0
    l <- letters
    c <- colors
  } yield "" + n + l + "-" + c
  println(forComprehension)

  /*
  Exercises:
  1. MyList supports for comprehensions? Yes
  2. A small collection of at most one element - Maybe[+T]
      - map, flatMap, filter
   */

  // 1. MyList supports for comprehensions
  val myList = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val forCompMyList = for {
    n <- myList
  } yield n * 2
  println(forCompMyList)


  // 2. Small collection Maybe[+T]
  sealed trait Maybe[+T] {
    def map[B](f: T => B): Maybe[B]
    def flatMap[B](f: T => Maybe[B]): Maybe[B]
    def filter(f: T => Boolean): Maybe[T]
  }

  case object None extends Maybe[Nothing] {
    override def map[B](f: Nothing => B): Maybe[Nothing] = None
    override def flatMap[B](f: Nothing => Maybe[B]): Maybe[Nothing] = None
    override def filter(f: Nothing => Boolean): Maybe[Nothing] = None
  }

  case class Some[+T](elem: T) extends Maybe[T] {
    override def map[B](f: T => B): Some[B] = Some(f(elem))
    override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(elem)
    override def filter(f: T => Boolean): Maybe[T] = if (f(elem)) this else None
  }

}
