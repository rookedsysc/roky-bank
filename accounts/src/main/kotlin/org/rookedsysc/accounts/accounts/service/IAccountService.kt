package org.rookedsysc.accounts.accounts.service

import org.rookedsysc.accounts.accounts.dto.request.AccountUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerCreateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.CustomerResponseDto

interface IAccountService {
    fun createAccount(customerDto: CustomerCreateRequestDto)

    fun accountDetailByMobileNumber(mobileNumber: String): CustomerResponseDto

    fun updateCustomer(request: CustomerUpdateRequestDto): Boolean

    fun updateAccount(request: AccountUpdateRequestDto): Boolean
}