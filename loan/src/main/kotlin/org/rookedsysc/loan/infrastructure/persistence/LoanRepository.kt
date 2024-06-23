package org.rookedsysc.loan.infrastructure.persistence

import org.rookedsysc.loan.domain.entity.Loan
import org.springframework.data.jpa.repository.JpaRepository

interface LoanRepository : JpaRepository<Loan, Long> {
    fun findByMobileNumber(mobileNumber: String): Loan?
}