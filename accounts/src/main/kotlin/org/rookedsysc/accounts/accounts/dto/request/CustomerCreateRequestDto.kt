package org.rookedsysc.accounts.accounts.dto.request

data class CustomerCreateRequestDto(
        val name: String,
        val email: String,
        val mobileNumber: String,
)