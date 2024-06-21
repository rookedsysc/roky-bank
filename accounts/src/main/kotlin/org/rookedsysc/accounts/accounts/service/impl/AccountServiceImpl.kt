package org.rookedsysc.accounts.accounts.service.impl

import jakarta.transaction.Transactional
import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.converter.AccountConverter
import org.rookedsysc.accounts.accounts.converter.CustomerConverter
import org.rookedsysc.accounts.accounts.dto.request.AccountUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerCreateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.CustomerResponseDto
import org.rookedsysc.accounts.accounts.entity.Account
import org.rookedsysc.accounts.accounts.entity.Customer
import org.rookedsysc.accounts.accounts.exception.CustomerAlreadyExistException
import org.rookedsysc.accounts.accounts.exception.ResourceNotFoundException
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
    override fun createAccount(customerDto: CustomerCreateRequestDto) {
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
        val customer: Customer = customerRepository.findByMobileNumber(mobileNumber) ?: let {
            throw ResourceNotFoundException(resourceName = "customer", fieldName = "mobileNumber", fieldValue = mobileNumber)
        }
        val account: Account = accountRepository.findByCustomerId(customer.customerId!!) ?: let {
            throw ResourceNotFoundException(resourceName = "account", fieldName = "customerId", fieldValue = customer.customerId.toString())
        }
        return CustomerConverter.toResponse(customer, AccountConverter.toDto(account))
    }

    override fun updateCustomer(request: CustomerUpdateRequestDto): Boolean {
        var response = false
        if (request.mobileNumber != null) {
            val customer: Customer = customerRepository.findByMobileNumber(request.mobileNumber) ?: let {
                throw ResourceNotFoundException(resourceName = "customer", fieldName = "mobileNumber", fieldValue = request.mobileNumber)
            }
            val updatedCustomer: Customer = CustomerConverter.update(customer, request)
            customerRepository.save(updatedCustomer)
            response = true
        }

        return response
    }

    override fun updateAccount(request: AccountUpdateRequestDto): Boolean {
        var response = false
        if (request.accountNumber != null) {
            val account: Account = accountRepository.findByAccountNumber(request.accountNumber) ?: let {
                throw ResourceNotFoundException(resourceName = "account", fieldName = "accountNumber", fieldValue = request.accountNumber.toString())
            }
            val updatedAccount: Account = AccountConverter.update(account, request)
            accountRepository.save(updatedAccount)
            response = true
        }

        return response
    }

    override fun deleteAccount(mobileNumber: String): Boolean {
        val customer: Customer = findByMobileNumOrThrow(mobileNumber)
        accountRepository.deleteByCustomerId(customer.customerId!!)
        customerRepository.delete(customer)
        return true
    }

    private fun findByMobileNumOrThrow(mobileNumber: String): Customer {
        val customer: Customer = customerRepository.findByMobileNumber(mobileNumber) ?: let {
            throw ResourceNotFoundException(resourceName = "customer", fieldName = "mobileNumber", fieldValue = mobileNumber)
        }
        return customer
    }
}