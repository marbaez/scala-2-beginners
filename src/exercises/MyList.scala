package exercises

import lectures.part2oop.Generics.MyList

abstract class MyList[+A] {
  /*
  head -> first element of the list
  tail -> remainder of the list
  isEmpty -> if the list is empty
  add(int) -> new list which this element is added
  toString -> a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](e: B): MyList[B]
  def printElements: String
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  override def toString: String = s"[${printElements}]"

  // HOFs
  def foreach(a:A => Unit): Unit
  def sort(f: (A,A) => Int): MyList[A]
  def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](c: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)
  def printElements: String = ""
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // HOFs
  override def foreach(a: Nothing => Unit): Unit = ()
  override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](l: MyList[B], f: (Nothing, B) => C): MyList[C] = Empty

  override def fold[B](c: B)(operator: (B, Nothing) => B): B = c
}
case class Cons[+A](val h: A,val t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](e: B): MyList[B] = Cons(e, this)
  override def printElements: String = if (t.isEmpty) s"$h" else s"$h, ${t.printElements}"
  def ++[B >: A](list: MyList[B]): MyList[B] = Cons[B](head, tail ++ list)

  def map[B](transformer: A => B): MyList[B] =
    new Cons[B](transformer(this.head), this.tail.map(transformer))
  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(this.head)) Cons[A](this.head, this.tail.filter(predicate))
    else this.tail.filter(predicate)
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  // HOFs
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons[A](x, Empty)
      else if (f(x, sortedList.head) <= 0) new Cons[A](x, sortedList)
      else new Cons[A](sortedList.head, insert(x, sortedList.tail))
    }
    def sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C] =
    if (!isEmpty && !l.isEmpty) new Cons[C](f(head, l.head), tail.zipWith(l.tail, f))
    else Empty

  override def fold[B](c: B)(operator: (B, A) => B): B =
    operator(tail.fold(c)(operator), head)
}

/*
  Exercises Extending MyList
  1. Generic Trait MyPredicate[-T] test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] transform A -> B
  3. MyList:
    - map(transformer) => MyList
    - filter(predicate)
    - flatMap(transformer from A to MyList[B]) => MyList[B]

    [1,2,3].map(n*2
 */

object ListTest extends App {
  val listIntegers: MyList[Int] = Cons(99, Cons(55, Cons(77, Cons(66, Cons(77, Empty)))))
  val listStrings: MyList[String] = Cons[String]("Hello", Cons[String]("Scala", Empty))
  println(listIntegers.map((a: Int) => a * 2))
  println(listIntegers.filter((t: Int) => t > 2))
  print(listIntegers.flatMap((a: Int) => Cons[Int](a, Cons[Int](a * 2, Empty))))
  listIntegers.foreach(println)

  println(listIntegers.sort(_ - _))
}
