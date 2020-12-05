package com.adventofcode.day4

import com.adventofcode.IsDayProgram
import java.io.File

@Suppress("unused")
class Program : IsDayProgram<Pair<Int, Int>> {
    override fun run(dayNumber: Int): Pair<Int, Int> {
        val passports = File(javaClass.getResource("/day4/input").toURI()).readText()
        val part1 = passports.countValidPassports(passportValidatorBasedOnRequiredFields)
        val part2 = passports.countValidPassports(passportValidatorBasedOnFieldValidators)
        return part1 to part2
    }
}

typealias Passport = Map<String, String>
typealias PassportValidator = (Passport) -> Boolean

fun String.toPassports(): List<Passport> = split("\n\n").map { rawPassport ->
    "(\\w{3}):(.+?)(?=[\\s\n]|$)".toRegex().findAll(rawPassport)
            .map { it.groupValues[1] to it.groupValues[2] }
            .toMap()
}

fun String.countValidPassports(validator: PassportValidator): Int = toPassports().count(validator)

// region Part1

val passportValidatorBasedOnRequiredFields: PassportValidator = { passport ->
    val requiredFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    passport.keys.containsAll(requiredFields)
}

// endregion

// region Part2

typealias PassportFieldValidator = (String) -> Boolean

val passportValidatorBasedOnFieldValidators: PassportValidator = { passport ->
    val validators = mapOf(
            "byr" to birthYearValidator,
            "iyr" to issueYearValidator,
            "eyr" to expirationYearValidator,
            "hgt" to heightValidator,
            "hcl" to hairColorValidator,
            "ecl" to eyeColorValidator,
            "pid" to passportIdValidator
    )
    validators.all { (fieldKey, validator) -> passport[fieldKey]?.let(validator) ?: false }
}

val birthYearValidator: PassportFieldValidator = { birthYear ->
    birthYear.matches("\\d{4}".toRegex()) && birthYear.toInt() in 1920..2002
}

val issueYearValidator: PassportFieldValidator = { issueYear ->
    issueYear.matches("\\d{4}".toRegex()) && issueYear.toInt() in 2010..2020
}

val expirationYearValidator: PassportFieldValidator = { expirationYear ->
    expirationYear.matches("\\d{4}".toRegex()) && expirationYear.toInt() in 2020..2030
}

val heightValidator: PassportFieldValidator = { height ->
    when {
        height.matches("\\d+cm".toRegex()) -> height.dropLast(2).toInt() in 150..193
        height.matches("\\d+in".toRegex()) -> height.dropLast(2).toInt() in 59..76
        else -> false
    }
}

val hairColorValidator: PassportFieldValidator = { hairColor ->
    hairColor.matches("#[0-9a-f]{6}".toRegex())
}

val eyeColorValidator: PassportFieldValidator = { eyeColor ->
    eyeColor.matches("amb|blu|brn|gry|grn|hzl|oth".toRegex())
}

val passportIdValidator: PassportFieldValidator = { passportId ->
    passportId.matches("\\d{9}".toRegex())
}

// endregion