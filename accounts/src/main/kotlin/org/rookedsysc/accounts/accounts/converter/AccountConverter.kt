package org.rookedsysc.accounts.accounts.converter

import org.rookedsysc.accounts.accounts.dto.request.AccountUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.AccountResponseDto
import org.rookedsysc.accounts.accounts.entity.Account

class AccountConverter {
    companion object {
        fun toEntity(accountDto: AccountResponseDto, customerId: Long): Account {
            return Account(
                    customerId = customerId,
                    accountNumber = accountDto.accountNumber,
                    accountType = accountDto.accountType,
                    branchAddress = accountDto.branchAddress
            )
        }

        fun update(account: Account, accountDto: AccountUpdateRequestDto): Account {
            account.accountNumber = accountDto.accountNumber?: account.accountNumber
            account.accountType = accountDto.accountType?: account.accountType
            account.branchAddress = accountDto.branchAddress?: account.branchAddress
            return account
        }

        fun toDto(account: Account): AccountResponseDto {
            return AccountResponseDto(
                    accountNumber = account.accountNumber,
                    accountType = account.accountType,
                    branchAddress = account.branchAddress
            )
        }
    }
}