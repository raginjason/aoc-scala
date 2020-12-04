package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableFor2
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day4._
import raginjason.aoc2020.Day4Test._

class Day4Test extends Suites(
  new Part1Test, new Part2Test
)

object Day4Test {

  class Part1Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val samplePassports: String =
      """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        |byr:1937 iyr:2017 cid:147 hgt:183cm
        |
        |iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        |hcl:#cfa07d byr:1929
        |
        |hcl:#ae17e1 iyr:2013
        |eyr:2024
        |ecl:brn pid:760753108 byr:1931
        |hgt:179cm
        |
        |hcl:#cfa07d eyr:2025 pid:166559648
        |iyr:2011 ecl:brn hgt:59in""".stripMargin

    test(s"splitPassports() against sample") {
      assert(splitPassports(samplePassports).length == 4)
    }

    val expectedPassports: TableFor2[String, Boolean] =
      Table(
        ("input", "expectedValid"),
        ("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm", true),
        ("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929", false),
        ("hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm", true),
        ("hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in", false),
      )

    forAll(expectedPassports) { (input, expectedValid) =>
      test(s"Passport($input).hasRequiredFields") {
        val p = Passport(input)
        assert(p.hasRequiredFields == expectedValid)
      }
    }

    test(s"validPassports(splitPassports()) sample") {
      val p = validPassports(splitPassports(samplePassports))
      assert(p.length == 2)
    }

    test(s"validPassports(splitPassports()) input") {
      val p = validPassports(splitPassports(input))
      assert(p.length == 206)
    }
  }

  class Part2Test extends AnyFunSuite with ScalaCheckPropertyChecks {

    val sampleBirthYears: TableFor2[String, Boolean] =
      Table(
        ("input", "expected"),
        ("byr:1919", false),
        ("byr:1920", true),
        ("byr:1921", true),
        ("byr:2001", true),
        ("byr:2002", true),
        ("byr:2003", false),
      )

    forAll(sampleBirthYears) { (input, expected) =>
      test(s"BirthYear($input).isValid") {
        assert(BirthYear(Option(input)).isValid == expected)
      }
    }

    val sampleIssueYears: TableFor2[String, Boolean] =
      Table(
        ("input", "expected"),
        ("iyr:2009", false),
        ("iyr:2010", true),
        ("iyr:2011", true),
        ("iyr:2019", true),
        ("iyr:2020", true),
        ("iyr:2021", false),
      )

    forAll(sampleIssueYears) { (input, expected) =>
      test(s"IssueYear($input).isValid") {
        assert(IssueYear(Option(input)).isValid == expected)
      }
    }

    val sampleExpirationYears: TableFor2[String, Boolean] =
      Table(
        ("input", "expected"),
        ("eyr:2019", false),
        ("eyr:2020", true),
        ("eyr:2021", true),
        ("eyr:2029", true),
        ("eyr:2030", true),
        ("eyr:2031", false),
      )

    forAll(sampleExpirationYears) { (input, expected) =>
      test(s"ExpirationYear($input).isValid") {
        assert(ExpirationYear(Option(input)).isValid == expected)
      }
    }

    val sampleHeight: TableFor2[String, Boolean] =
      Table(
        ("input", "expected"),
        ("hgt:149cm", false),
        ("hgt:150cm", true),
        ("hgt:151cm", true),
        ("hgt:192cm", true),
        ("hgt:193cm", true),
        ("hgt:194cm", false),
        ("hgt:58in", false),
        ("hgt:59in", true),
        ("hgt:60in", true),
        ("hgt:75in", true),
        ("hgt:76in", true),
        ("hgt:77in", false),
      )

    forAll(sampleHeight) { (input, expected) =>
      test(s"Height($input).isValid") {
        assert(Height(Option(input)).isValid == expected)
      }
    }

    test(s"validPassportsPart2(splitPassports()) input") {
      val p = validPassportsPart2(splitPassports(input))
      assert(p.length == 123)
    }

  }

}
