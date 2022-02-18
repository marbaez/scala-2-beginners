package exercises.part2oop

object CaseClassesMine extends App {
  case class Person(name: String, age: Int)
  // 1. class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)

  // 3. equals, hashcode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy methods
  val jim3 = jim.copy(age = 45)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are Serializable
  // useful in Akka

  // 7. have extractor patterns, they can be used in pattern matched

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  Expand MyList - use case classes and case objects
   */
}
