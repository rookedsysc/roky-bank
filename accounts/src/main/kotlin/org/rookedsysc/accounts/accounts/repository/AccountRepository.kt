package org.rookedsysc.accounts.accounts.repository

import org.rookedsysc.accounts.accounts.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByCustomerId(customerId: Long): Account?
    fun findByAccountNumber(accountNumber: Long): Account?
}