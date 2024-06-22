package org.rookedsysc.accounts.accounts.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


data class CustomerUpdateRequestDto(
        @field:NotBlank(message = "Name cannot be blank.")
        @field:Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters.")
        val name: String?,
        
        @field:NotBlank(message = "Email cannot be blank.")
        @field:Email(message = "Email addr should be a valid value.")
        val email: String?,

        @Pattern(regexp = "(^$|[0-9]{10})", message = "Password must contain at least one digit, one lowercase, one uppercase, and 8 characters.")
        @field:Size(min = 10, max = 10, message = "Mobile number must be 10 digits.")
        val mobileNumber: String?,
)