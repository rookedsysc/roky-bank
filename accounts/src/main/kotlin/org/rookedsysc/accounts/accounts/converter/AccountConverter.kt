package org.rookedsysc.accounts.accounts.converter

import org.rookedsysc.accounts.accounts.dto.AccountDto
import org.rookedsysc.accounts.accounts.entity.Account
import org.springframework.stereotype.Service

class AccountConverter {
    companion object {
        fun toEntity(accountDto: AccountDto, customerId: Long): Account {
            return Account(
                    customerId = customerId,
                    accountNumber = accountDto.accountNumber,
                    accountType = accountDto.accountType,
                    branchAddress = accountDto.branchAddress
            )
        }

        fun toDto(account: Account): AccountDto {
            return AccountDto(
                    accountNumber = account.accountNumber,
                    accountType = account.accountType,
                    branchAddress = account.branchAddress
            )
        }
    }
}