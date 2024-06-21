package org.rookedsysc.accounts.accounts.service.impl

import jakarta.transaction.Transactional
import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.converter.AccountConverter
import org.rookedsysc.accounts.accounts.converter.CustomerConverter
import org.rookedsysc.accounts.accounts.dto.CustomerRequestDto
import org.rookedsysc.accounts.accounts.dto.CustomerResponseDto
import org.rookedsysc.accounts.accounts.entity.Account
import org.rookedsysc.accounts.accounts.entity.Customer
import org.rookedsysc.accounts.accounts.exception.CustomerAlreadyExistException
import org.rookedsysc.accounts.accounts.exception.ResourceNotFound
import org.rookedsysc.accounts.accounts.repository.AccountRepository
import org.rookedsysc.accounts.accounts.repository.CustomerRepository
import org.rookedsysc.accounts.accounts.service.IAccountService
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class AccountServiceImpl(
        private val accountRepository: AccountRepository,
        private val customerRepository: CustomerRepository
) : IAccountService {
    override fun createAccount(customerDto: CustomerRequestDto) {
        customerRepository.findByMobileNumber(customerDto.mobileNumber)
                .let {
                    if (it != null) {
                        throw CustomerAlreadyExistException("Customer with mobile number ${customerDto.mobileNumber} already exists")
                    }
                }
        val customer: Customer = CustomerConverter.toEntity(customerDto)
        customer.createdBy = "Anonymous"
        val savedCustomer: Customer = customerRepository.save(customer)
        accountRepository.save(createAccount(savedCustomer.customerId!!))
    }

    private fun createAccount(customerId: Long): Account {
        val randomAccNumber = 1000000000L + Random().nextInt(900000000)

        val newAccount: Account = Account(
                customerId = customerId,
                accountNumber = randomAccNumber,
                accountType = AccountConstants.SAVINGS,
                branchAddress = AccountConstants.ADDRESS
        )
        newAccount.createdBy = "Anonymous"

        return newAccount
    }

    override fun accountDetailByMobileNumber(mobileNumber: String): CustomerResponseDto {
        val customer: Customer = customerRepository.findByMobileNumber(mobileNumber)?: let {
            throw ResourceNotFound(resourceName = "customer", fieldName = "mobileNumber", fieldValue = mobileNumber)
        }
        val account: Account = accountRepository.findByCustomerId(customer.customerId!!)?: let {
            throw ResourceNotFound(resourceName = "account", fieldName = "customerId", fieldValue = customer.customerId.toString())
        }
        return CustomerConverter.toResponse(customer, AccountConverter.toDto(account))
    }
}