package com.example.troniks.domain.use_cases

import android.util.Patterns
import java.util.regex.Pattern

private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty"
            )
        }
        if (Pattern.matches(EMAIL_VALIDATION_REGEX,email)) {
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