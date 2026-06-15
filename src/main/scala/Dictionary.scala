// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================
import FileIO.readLines

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {

    val lineas = readLines(filePath)

    lineas.flatMap(word => 
      entityType match {
        case "Person"                           => Some(new Person(word))
        case "Organization"                     => Some(new Organization(word))
        //Cada University object ya hereda ser Organizaciones aunque no estén en el archivo correspondiente
        case "University"                       => Some(new University(word)) 
        case "Place"                            => Some(new Place(word))
        //case "Technology"                     => Some(new Technology(word)) //no hay txt para tecnologias
        case "ProgrammingLanguage"              => Some(new ProgrammingLanguage(word))
        case _                                  => None //descarte gracias a flatmap luego
      }
    )

  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   */
  def loadAll(): List[NamedEntity] = {

    val entityPath = List(("data/people.txt","Person"),
                          ("data/universities.txt","University"),
                          ("data/languages.txt","ProgrammingLanguage"),
                          ("data/organizations.txt","Organization"),
                          ("data/places.txt","Place"))

    val allDict = entityPath.flatMap{ case (path,tipo) =>
      loadFromFile(path,tipo)
    }
    allDict.distinctBy(_.text)
    /** (DDD) Uso de distinctBy:
    *
    * Elimina entidades cuyo texto es idéntico, conservando la primera 
    * ocurrencia. Útil cuando un mismo nombre aparece en múltiples 
    * archivos (ej: "MIT" en universities.txt y organizations.txt).
    * La prioridad la define el orden de entityPath. Si quiero tener repetidos
    * por cuestiones semanticas debería quitarlo.
    **/
  }
}
