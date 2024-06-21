package org.rookedsysc.accounts.accounts.dto.response

data class CustomerResponseDto(
        val name: String,
        val email: String,
        val mobileNumber: String,
        val account: AccountResponseDto,
)
