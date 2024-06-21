package org.rookedsysc.accounts.accounts.dto.response

data class AccountResponseDto(
        val accountNumber: Long,
        val accountType: String,
        val branchAddress: String,
)