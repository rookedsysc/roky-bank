package org.rookedsysc.accounts.accounts.repository

import org.rookedsysc.accounts.accounts.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByMobileNumber(mobileNumber: String): Customer?
}