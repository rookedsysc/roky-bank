package org.rookedsysc.accounts.accounts.service

import org.rookedsysc.accounts.accounts.dto.CustomerRequestDto
import org.rookedsysc.accounts.accounts.dto.CustomerResponseDto

interface IAccountService {
    fun createAccount(customerDto: CustomerRequestDto)

    fun accountDetailByMobileNumber(mobileNumber: String) : CustomerResponseDto
}