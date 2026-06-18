// =====================================================================
// Ejercicio 1: Modelar la jerarquía de entidades
// =====================================================================

// USO DE TRAIT PARA ENTITY TYPE
/* 
trait entType {
  def entityType: String
}
*/

/*trait para contar cantidad de instancias de una clase*/
trait Contador {
  private var total : Int = 0
  def incrementar() : Unit = total += 1
  def total_instancias() : Int = total
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

object PersonCount extends Contador
class Person(text: String) extends NamedEntity(text){
  override def entityType = "Person"
  PersonCount.incrementar()
}

object OrganizationCount extends Contador
class Organization(text: String) extends NamedEntity(text) {
  override def entityType = "Organization"
  OrganizationCount.incrementar()
}

object UniversityCount extends Contador
class University(text: String) extends Organization(text) {
  override def entityType = "University"
  UniversityCount.incrementar()
}

object PlaceCount extends Contador
class Place(text: String) extends NamedEntity(text) {
  override def entityType = "Place"
  PlaceCount.incrementar()
}

object TechnologyCount extends Contador
class Technology(text: String) extends NamedEntity(text) {
  override def entityType = "Technology"
  TechnologyCount.incrementar()
}

object ProgrammingLanguageCount extends Contador
class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType = "ProgrammingLanguage"
  ProgrammingLanguageCount.incrementar()
}
