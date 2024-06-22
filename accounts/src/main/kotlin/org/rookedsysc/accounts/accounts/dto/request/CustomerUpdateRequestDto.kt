package org.rookedsysc.accounts.accounts.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.rookedsysc.accounts.accounts.service.validation.NullOrEmailValid
import org.rookedsysc.accounts.accounts.service.validation.NullOrNameValid


data class CustomerUpdateRequestDto(
        @field:NullOrNameValid
        val name: String?,

        @field:NullOrEmailValid
        val email: String?,

        @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Password must contain at least one digit, one lowercase, one uppercase, and 8 characters.")
        @field:Size(min = 10, max = 10, message = "Mobile number must be 10 digits.")
        val mobileNumber: String,
)