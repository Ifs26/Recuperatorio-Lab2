import Formatters.formatEntityStats

class Ejercicio5_countByType extends munit.FunSuite {

  test("countByType groups entities and counts correctly by type") {
    val entities = List(
      new Person("Alan Turing"),
      new ProgrammingLanguage("Scala"),
      new Person("Ada Lovelace"),
      new University("MIT")
    )

    val resultado = Analyzer.countByType(entities)

    val esperado = Map(
      "Person" -> 2,
      "ProgrammingLanguage" -> 1,
      "University" -> 1
    )

    assertEquals(resultado, esperado)
  }

  test("countByType returns empty map for empty list") {
    assertEquals(Analyzer.countByType(List.empty), Map.empty[String, Int])
  }
}

class Ejercicio5_formatEntityStats extends munit.FunSuite {
  test("formatEntityStats works correctly") {
    val expected = "=== Estadísticas de entidades ===\n" +
      "Person: 5\n" +
      "ProgrammingLanguage: 3\n" +
      "Organization: 2\n" +
      "University: 2\n"

    val entities = List(
      new Person("Ada Lovelace"),
      new Person("Charles Babbage"),
      new Person("Alan Turing"),
      new Person("Grace Hopper"),
      new Person("John von Neumann"),
      new ProgrammingLanguage("Scala"),
      new ProgrammingLanguage("C"),
      new ProgrammingLanguage("Python"),
      new University("UNC"),
      new University("MIT"),
      new Organization("IBM"),
      new Organization("Alphabet")
    )
    val counts = Analyzer.countByType(entities)

    val obtained = formatEntityStats(counts)
    assertEquals(obtained, expected)

  }

  test("formatEntityStats with empty map returns only header") {
    val expected = "=== Estadísticas de entidades ===\n"
    val obtained = formatEntityStats(Map.empty[String, Int])
    assertEquals(obtained, expected)
  }
}
