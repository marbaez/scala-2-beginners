package exercises.part3fp

object AnonymousFunctionsMine extends App {
  // anonymous function (Lambda)
  val doubler: Int => Int = x => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parameters
  val justDoSomething: () => Int = () => 3

  // be careful
  println(justDoSomething)  //function itsels
  println(justDoSomething()) // function call

  // curly braces with lambda
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactyc sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
  1. MyList: Replace all FunctionX calls with lambdas
  2. Rewrite the "special" adder as an anonymous function
   */

  def specialAdder: Int => Int => Int = (x) => (y) => x + y
  print(specialAdder(3)(4))

 }
