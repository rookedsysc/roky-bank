package org.rookedsysc.accounts.accounts.service

import org.rookedsysc.accounts.accounts.dto.CustomerDto

interface IAccountService {
    fun createAccount(customerDto: CustomerDto)
}