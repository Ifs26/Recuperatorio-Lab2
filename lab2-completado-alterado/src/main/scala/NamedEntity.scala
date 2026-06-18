// =====================================================================
// Ejercicio 1: Modelar la jerarquía de entidades
// =====================================================================

/*trait para crear instancias*/
trait Factory {
  def create(name : String): NamedEntity
}

object personFactory extends Factory{
  def create(text : String) = new Person(text)
}

object organizationFactory extends Factory{
  def create(text : String) = new Organization(text)
}

object universityFactory extends Factory{
  def create(text : String) = new University(text)
}

object placeFactory extends Factory{
  def create(text : String) = new Place(text)
}

object technologyFactory extends Factory{
  def create(text : String) = new Technology(text)
}

object programmingLanguageFactory extends Factory{
  def create(text : String) = new ProgrammingLanguage(text)
}

/**
 * Clase base abstracta para todas las entidades nombradas.
 *
 * Una entidad nombrada es una expresión del texto que refiere a un objeto
 * del mundo real (persona, lugar, organización, tecnología, etc.).
 *
 * @param text el texto tal como aparece en el corpus
 */
abstract class NamedEntity(val text: String) { //extends entType 
                          //"val" indica que es un parametro público

  /**
   * Retorna el tipo de la entidad como String.
   * Ejemplo: "Person", "University", "ProgrammingLanguage"
   *
   * TODO (Ejercicio 1): Implementar en cada subclase concreta.
   */
  def entityType: String

  /**
   * Retorna una línea de descripción de la entidad para el informe.
   *
   * Al usar entityType aquí, este método funciona correctamente para cualquier
   * subclase sin necesidad de redefinirlo. Esto es polimorfismo.
   */
  def describe: String = s"[$entityType] $text"

  def hierarchy : List[String] = List(entityType)
}

// =====================================================================
// TODO (Ejercicio 1): Completar la jerarquía de entidades
//
// Implementar las clases faltantes.
//
// Jerarquía esperada:
//
//   NamedEntity
//   ├── Person
//   ├── Organization
//   │   └── University
//   ├── Place
//   └── Technology
//       └── ProgrammingLanguage

class Person(text: String) extends NamedEntity(text){
  override def entityType = "Person"
}

class Organization(text: String) extends NamedEntity(text) {
  override def entityType = "Organization"
}

class University(text: String) extends Organization(text) {
  override def entityType = "University"
  
  override def hierarchy: List[String] = List(entityType,super.entityType)
}

class Place(text: String) extends NamedEntity(text) {
  override def entityType = "Place"
}

class Technology(text: String) extends NamedEntity(text) {
  override def entityType = "Technology"
}

class ProgrammingLanguage(text: String)
extends Technology(text) {

  override def entityType = "ProgrammingLanguage"
  override def hierarchy = List(entityType,super.entityType)
    
}
