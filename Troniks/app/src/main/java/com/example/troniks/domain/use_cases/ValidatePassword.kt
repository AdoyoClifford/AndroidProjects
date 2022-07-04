package com.example.troniks.domain.use_cases

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password Needs to contain at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any{it.isDigit()}
                && password.any{it.isLetter()}
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password Needs to contain at least one letter and a digit"
            )
        }
        return ValidationResult(
            successful = true,
        )
    }
}