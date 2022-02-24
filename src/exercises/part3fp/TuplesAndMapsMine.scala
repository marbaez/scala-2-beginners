package exercises.part3fp

import scala.:+
import scala.annotation.tailrec

object TuplesAndMapsMine extends App {

  // tuples = finite ordered "lists"
  val aTuple = (2, "hello, Scala")  // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)  // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 9000)).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  /*
  1. What would happen if I had two original entris "Jim" -> 555 and "JIM" -> 900
  2. Overly simplified social network based on maps
    Person = string
    - add a person to the network
    - remove a person
    - friend (mutual)
    - unfriend (mutual)

    - number of friends of a person
    - person with most friends
    - how many people have no friends
    - if there is a social connection between two people (direct or not)

   */

  // 1.
  println(newPhonebook)
  println(phonebook.map(p => p._1.toLowerCase -> p._2)) // last value overrides first one

  case class SocialNetwork(network: Map[String, List[String]]) {
    def add(person: String): SocialNetwork = {
      val newPerson = person -> List[String]()
      SocialNetwork(network + newPerson)
    }

    def remove(person: String): SocialNetwork =
      if (network.contains(person)) {
        SocialNetwork {
          network.filter(_._1 != person)
        }
      } else this

    def friend(p1: String, p2: String): SocialNetwork =
      if (network.contains(p1) && network.contains(p2)) {
        val friendsP1 = network(p1)
        val friendsP2 = network(p2)

        SocialNetwork(network + (p1 -> (friendsP1 :+ p2)) + (p2 -> (friendsP2 :+ p1)))
      } else this

    def unfriend(p1: String, p2: String): SocialNetwork =
      if (network.contains(p1) && network.contains(p2)) {
        val friendsP1 = network(p1)
        val friendsP2 = network(p2)

        SocialNetwork(network + (p1 -> (friendsP1.filter(_ != p2))) + (p2 -> (friendsP2.filter(_ != p1))))
      } else this

    def numFriends(person: String): Int = if (network.contains(person)) {
      network(person).size
    } else 0

    def personWithMostFriends: Int = network.maxBy(_._2.size)._2.size

    def personsWithNoFriends:Int = network.count(_._2.isEmpty)

    def areConnected(p1: String, p2:String): Boolean = {
      @tailrec
      def go(l: List[String], connected: Set[String], visited: Set[String]): Set[String] =
        l match {
          case Nil => connected
          case x :: xs if visited.contains(x) => go(xs, connected, visited)
          case x :: xs => {go(xs ++ network(x).filter(!visited.contains(_)), connected ++ network(x).toSet , visited + x)}
        }

      val connects = go(network(p1), Set(p1), Set(p1))
      connects.contains(p2)
    }
  }

  val social = SocialNetwork( Map(
    "p1" -> List("p2", "p3"),
    "p2" -> List("p1", "p4", "p5"),
    "p3" -> List("p1", "p5", "p6", "p7"),
    "p4" -> List("p2"),
    "p5" -> List("p2", "p3"),
    "p6" -> List("p3"),
    "p7" -> List("p3"),
    "p10" -> List()
  ))

  println(social.areConnected("p1", "p10"))



}
