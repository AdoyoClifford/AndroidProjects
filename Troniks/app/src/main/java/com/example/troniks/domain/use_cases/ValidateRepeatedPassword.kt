package com.example.troniks.domain.use_cases

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return  ValidationResult(
                successful = false,
                errorMessage = "Passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}