package com.example.troniks.domain.use_cases

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty"
            )
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = true,
                errorMessage = "Email is not Valid"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}