package com.example.troniks.domain.use_case

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
                successful = false,
                errorMessage = "Email is not valid"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}