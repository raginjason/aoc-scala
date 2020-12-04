package raginjason.aoc2020

import scala.collection.immutable.StringOps

object Day4 {

  // Slurp in file
  lazy val input: String = io.Source.fromInputStream(getClass.getResourceAsStream("day4.txt")).mkString.trim

  def main(args: Array[String]): Unit = {
    println(validPassports(splitPassports(input)).length)
    println(validPassportsPart2(splitPassports(input)).length)
  }

  def splitPassports(input: String): Seq[String] = {
    (input: StringOps).split("(?m)^\\s*$").map(_.replaceAll("\n", " ").trim)
  }

  def validPassports(input: Seq[String]): Seq[Passport] = {
    input.map(Passport).filter(_.hasRequiredFields)
  }

  def validPassportsPart2(input: Seq[String]): Seq[Passport] = {
    input.map(Passport).filter(_.isValid)
  }

  case class Passport(input: String) {
    def parts: Seq[String] = input.split(' ')

    def isValid: Boolean = {
      hasRequiredFields & birthYear.isValid & issueYear.isValid & expirationYear.isValid & height.isValid &
        eyeColor.isValid & hairColor.isValid & passportId.isValid
    }

    def hasRequiredFields: Boolean = {
      length == 8 | length == 7 & !hasCountryId
    }

    def length: Int = input.split(' ').length

    def hasCountryId: Boolean = input.split(' ').count(_.startsWith("cid:")) match {
      case x if x > 0 => true
      case _ => false
    }

    def birthYear: BirthYear = {
      BirthYear(input.split(' ').find(_.startsWith("byr:")))
    }

    def issueYear: IssueYear = {
      IssueYear(input.split(' ').find(_.startsWith("iyr:")))
    }

    def expirationYear: ExpirationYear = {
      ExpirationYear(input.split(' ').find(_.startsWith("eyr:")))
    }

    def height: Height = {
      Height(input.split(' ').find(_.startsWith("hgt:")))
    }

    def eyeColor: EyeColor = {
      EyeColor(input.split(' ').find(_.startsWith("ecl:")))
    }

    def hairColor: HairColor = {
      HairColor(input.split(' ').find(_.startsWith("hcl:")))
    }

    def passportId: PassportId = {
      PassportId(input.split(' ').find(_.startsWith("pid:")))
    }

  }

  case class BirthYear(s: Option[String]) {
    val year: Int = s.getOrElse("0").stripPrefix("byr:").toInt
    val isValid: Boolean = 1920 to 2002 contains year
  }

  case class IssueYear(s: Option[String]) {
    val year: Int = s.getOrElse("0").stripPrefix("iyr:").toInt
    val isValid: Boolean = 2010 to 2020 contains year
  }

  case class ExpirationYear(s: Option[String]) {
    val year: Int = s.getOrElse("0").stripPrefix("eyr:").toInt
    val isValid: Boolean = 2020 to 2030 contains year
  }

  case class Height(s: Option[String]) {
    val rawHeight: String = s.getOrElse("0in").stripPrefix("hgt:")
    val heightValue: Int = rawHeight.stripSuffix("in").stripSuffix("cm").toInt
    val heightType: String = rawHeight.substring(rawHeight.length - 2, rawHeight.length)

    def isValid: Boolean = (heightValue, heightType) match {
      case (heightValue, heightType) if heightType == "in" => 59 to 76 contains heightValue
      case (heightValue, heightType) if heightType == "cm" => 150 to 193 contains heightValue
      case _ => false
    }
  }

  case class EyeColor(s: Option[String]) {
    val eyeColor: String = s.getOrElse("INVALID").stripPrefix("ecl:")

    def isValid: Boolean = eyeColor match {
      case "amb" => true
      case "blu" => true
      case "brn" => true
      case "gry" => true
      case "grn" => true
      case "hzl" => true
      case "oth" => true
      case _ => false
    }
  }

  case class HairColor(s: Option[String]) {
    val hairColor: String = s.getOrElse("INVALID").stripPrefix("hcl:")

    def isValid: Boolean = hairColor.matches("^#[0-9a-f]{6}$")
  }

  case class PassportId(s: Option[String]) {
    val passportId: String = s.getOrElse("INVALID").stripPrefix("pid:")

    def isValid: Boolean = passportId.matches("^[0-9]{9}$")
  }

}
