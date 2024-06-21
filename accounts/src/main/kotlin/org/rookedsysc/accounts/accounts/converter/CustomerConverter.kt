package org.rookedsysc.accounts.accounts.converter

import org.rookedsysc.accounts.accounts.dto.AccountDto
import org.rookedsysc.accounts.accounts.dto.CustomerRequestDto
import org.rookedsysc.accounts.accounts.dto.CustomerResponseDto
import org.rookedsysc.accounts.accounts.entity.Customer

class CustomerConverter {
    companion object {
        fun toEntity(customerDto: CustomerRequestDto): Customer {
            return Customer(
                    name = customerDto.name,
                    email = customerDto.email,
                    mobileNumber = customerDto.mobileNumber
            )
        }

        fun toResponse(customer: Customer, accountDto: AccountDto): CustomerResponseDto {
            return CustomerResponseDto(
                    name = customer.name,
                    email = customer.email,
                    mobileNumber = customer.mobileNumber,
                    account = accountDto,
            )
        }
    }
}