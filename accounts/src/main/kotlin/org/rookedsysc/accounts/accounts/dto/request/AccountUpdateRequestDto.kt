package org.rookedsysc.accounts.accounts.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class AccountUpdateRequestDto(
        @field:NotEmpty(message = "AccountNumber can not be a null or empty")
        @field:Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
        val accountNumber: Long?,

        @field:NotEmpty(message = "AccountType can not be a null or empty")
        val accountType: String?,

        @NotEmpty(message = "BranchAddress can not be a null or empty")
        val branchAddress: String?,
)
