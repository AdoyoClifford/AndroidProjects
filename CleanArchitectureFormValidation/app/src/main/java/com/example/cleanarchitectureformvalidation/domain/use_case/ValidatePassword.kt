package com.example.cleanarchitectureformvalidation.domain.use_case

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to contain at least 8 characters"
            )
        }

        val containsLettersAndDigits = password.any{it.isDigit()}
                && password.any{it.isLetter()}
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password needs to contain at least one letter and digits"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}