package raginjason.aoc2020

import org.scalatest.Suites
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableFor2
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import raginjason.aoc2020.Day4._
import raginjason.aoc2020.Day4Test._

class Day4Test extends Suites(
  new Part1Test
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
      test(s"Passport($input).isValid") {
        val p = Passport(input)
        assert(p.isValid == expectedValid)
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

}
