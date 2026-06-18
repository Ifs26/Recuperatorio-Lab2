import scala.util.matching.Regex
import java.util.jar.Attributes.Name
/**
 * Clase base abstracta para todas las entidades nombradas.
 *
 * Una entidad nombrada es una expresión del texto que refiere a un objeto
 * del mundo real (persona, lugar, organización, tecnología, etc.).
 *
 * @param text el texto tal como aparece en el corpus
 */
abstract class NamedEntity(val text: String) {

  /**
   * Retorna el tipo de la entidad como String.
   */
  def entityType: String

  /**
   * Retorna una línea de descripción de la entidad para el informe.
   */
  def describe: String = s"[$entityType] $text"

  def matches(text: String): Boolean = {
    //Tengo que comparar cada elemento del string con el texto de la entidad actual
    val escaped = Regex.quote(this.text.toLowerCase())
    val pattern = s"(?<!\\w)$escaped(?!\\w)"
    pattern.r.findFirstIn(text.toLowerCase()).isDefined
  }

  def isRelevant: Boolean = true

}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"

  override def matches(text: String) : Boolean = {
    val escaped = Regex.quote(this.text)
    val pattern = s"(?<!\\w)$escaped(?!\\w)"

    pattern
    .r
    .findFirstIn(text)
    .isDefined
  }

}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"

  override def isRelevant: Boolean = false
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"

  override def isRelevant: Boolean = true
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"

  override def isRelevant: Boolean = false
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"
  
  override def matches(text: String) : Boolean = {
    val escaped = Regex.quote(this.text)
    val pattern = s"(?<!\\w)$escaped(?!\\w)"

    pattern
    .r
    .findFirstIn(text)
    .isDefined
  }

  override def isRelevant: Boolean = false
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"

  override def isRelevant: Boolean = true
}

abstract class Event(text: String) extends NamedEntity(text){

}

class Conference(text: String) extends Event(text) {
  override def entityType : String = "Conference"
}