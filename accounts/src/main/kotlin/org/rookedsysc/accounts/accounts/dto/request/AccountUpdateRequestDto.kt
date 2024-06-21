package org.rookedsysc.accounts.accounts.dto.request

data class AccountUpdateRequestDto(
        val accountNumber: Long?,
        val accountType: String?,
        val branchAddress: String?,
)
