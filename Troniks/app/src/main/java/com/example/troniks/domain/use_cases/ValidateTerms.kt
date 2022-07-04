package com.example.troniks.domain.use_cases

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms and conditions"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}