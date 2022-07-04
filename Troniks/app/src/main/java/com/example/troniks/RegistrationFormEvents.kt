package com.example.troniks

sealed class RegistrationFormEvents {
    data class EmailChanged(val email: String): RegistrationFormEvents()
    data class PasswordChanged(val password: String): RegistrationFormEvents()
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegistrationFormEvents()
    data class AcceptedTerms(val isAccepted: Boolean): RegistrationFormEvents()

    object Submit: RegistrationFormEvents()
}
