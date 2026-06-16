import Dictionary.loadAll
/*
* Estos test pueden fallar solo porque el resultado en consola es diferente
* por espacios vacíos. El formato actual fué una desición de diseño en base 
* a la consigna dada.
* 
* Leer bien el error en consola!
*/
class Ejercicio4_formatNERResult extends munit.FunSuite {
  test("formatNERResult works correctly with standard dictionaries") {

    val text = "Scala 3 released at EPFL by Martin Odersky"
    val dictionary = loadAll()
    val entities = Analyzer.detectEntities(text, dictionary)

    val expected = "Post: \"Scala 3 released at EPFL by Martin Odersky\"\n" +
      "Entidades detectadas:\n" +
      "  [ProgrammingLanguage] Scala\n" +
      "  [University] EPFL\n" +
      "  [Person] Martin Odersky\n"

    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  test("formatNERResult works correctly with empty dictionaries") {
    val text = "Scala 3 released at EPFL by Martin Odersky"
    val dictionary = Nil
    val entities = Analyzer.detectEntities(text, dictionary)

    val expected = "Post: \"Scala 3 released at EPFL by Martin Odersky\"\n" +
      "  (sin entidades detectadas)\n"

    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  test("formatNERResult works correctly with an empty post title") {
    val text = ""
    val dictionary = loadAll()
    val entities = Analyzer.detectEntities(text, dictionary)

    val expected = "Post: \"\"\n" +
      "  (sin entidades detectadas)\n"

    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  test("formatNERResult preserves detection order, not type or dictionary order") {
    val text = "Martin Odersky created Scala at EPFL"
    val dictionary = loadAll()
    val entities = Analyzer.detectEntities(text, dictionary)
    val expected = "Post: \"Martin Odersky created Scala at EPFL\"\n" +
      "Entidades detectadas:\n" +
      "  [Person] Martin Odersky\n" +
      "  [ProgrammingLanguage] Scala\n" +
      "  [University] EPFL\n"
    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  test("formatNERResult handles multiple entities of the same type") {
    val text = "Alan Turing and Ada Lovelace are pioneers"
    val dictionary = loadAll()
    val entities = Analyzer.detectEntities(text, dictionary)
    val expected = "Post: \"Alan Turing and Ada Lovelace are pioneers\"\n" +
      "Entidades detectadas:\n" +
      "  [Person] Alan Turing\n" +
      "  [Person] Ada Lovelace\n"
    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  //Test útil para ejercicio 3
  test("formatNERResult does not escape embedded double quotes") {
    val text = "Scala is \"the best\" language"
    val dictionary = loadAll()
    val entities = Analyzer.detectEntities(text, dictionary)
    val expected = "Post: \"Scala is \"the best\" language\"\n" +
      "Entidades detectadas:\n" +
      "  [ProgrammingLanguage] Scala\n"
    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }

  test("formatNERResult handles accented characters correctly") {
    val text = "Martin Odersky estudió en Córdoba"
    val cba = new Place("Cordoba")
    val dictProvisional = Dictionary.loadAll()
    val dictionary = dictProvisional :+ cba
    val entities = Analyzer.detectEntities(text, dictionary)
    val expected = "Post: \"Martin Odersky estudió en Córdoba\"\n" +
      "Entidades detectadas:\n" +
      "  [Person] Martin Odersky\n"
    val obtained = Formatters.formatNERResult(text, entities)
    assertEquals(obtained, expected)
  }
}
