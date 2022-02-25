package exercises.part3fp

import scala.util.Random

object OptionsMine extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  // to deal with unsafe APIs

  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) // WRONG
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE IT

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  print(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions
  val config: Map[String, String] = Map(
    "host" -> "localhost",
    "port" -> "9004"
  )

  class Connection {
   def connect = "Connected"
    override def toString: String = connect
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val connection = for {
    host <- config.get("host")
    port <- config.get("port")
    conn <- Connection(host, port)
  } yield conn

  println(connection)
}
