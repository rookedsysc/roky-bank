package org.rookedsysc.accounts.accounts.dto

data class CustomerResponseDto(
        val name: String,
        val email: String,
        val mobileNumber: String,
        val account: AccountDto,
)
