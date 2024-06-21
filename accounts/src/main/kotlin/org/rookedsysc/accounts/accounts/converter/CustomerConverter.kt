package org.rookedsysc.accounts.accounts.converter

import org.rookedsysc.accounts.accounts.dto.CustomerDto
import org.rookedsysc.accounts.accounts.entity.Customer

class CustomerConverter {
    companion object {
        fun toEntity(customerDto: CustomerDto): Customer {
            return Customer(
                    name = customerDto.name,
                    email = customerDto.email,
                    mobileNumber = customerDto.mobileNumber
            )
        }

        fun toDto(customer: Customer): CustomerDto {
            return CustomerDto(
                    name = customer.name,
                    email = customer.email,
                    mobileNumber = customer.mobileNumber
            )
        }
    }
}