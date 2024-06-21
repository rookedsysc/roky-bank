package org.rookedsysc.accounts.accounts.converter

import org.rookedsysc.accounts.accounts.dto.response.AccountResponseDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerCreateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.CustomerResponseDto
import org.rookedsysc.accounts.accounts.entity.Customer

class CustomerConverter {
    companion object {
        fun toEntity(customerDto: CustomerCreateRequestDto): Customer {
            return Customer(
                    name = customerDto.name,
                    email = customerDto.email,
                    mobileNumber = customerDto.mobileNumber
            )
        }

        fun update(customer: Customer, customerDto: CustomerUpdateRequestDto): Customer {
            customer.name = customerDto.name ?: customer.name
            customer.email = customerDto.email ?: customer.email
            customer.mobileNumber = customerDto.mobileNumber ?: customer.mobileNumber
            return customer
        }

        fun toResponse(customer: Customer, accountDto: AccountResponseDto): CustomerResponseDto {
            return CustomerResponseDto(
                    name = customer.name,
                    email = customer.email,
                    mobileNumber = customer.mobileNumber,
                    account = accountDto,
            )
        }
    }
}