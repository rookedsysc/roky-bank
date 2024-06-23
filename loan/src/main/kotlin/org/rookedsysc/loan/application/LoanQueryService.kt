package org.rookedsysc.loan.application

import org.rookedsysc.loan.common.constants.LoanConstants
import org.rookedsysc.loan.common.exception.LoanAlreadyExistsException
import org.rookedsysc.loan.domain.entity.Loan
import org.rookedsysc.loan.infrastructure.persistence.LoanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class LoanQueryService(private val loanRepository: LoanRepository) {
    fun create(mobileNumber: String) {
        loanRepository.findByMobileNumber(mobileNumber)
                .let {
                    if (it != null) {
                        throw LoanAlreadyExistsException("Loan already registered with given mobileNumber $mobileNumber")
                    }
                }
        loanRepository.save(createNewLoan(mobileNumber))
    }

    private fun createNewLoan(mobileNumber: String): Loan {
        val randomLoanNumber = 100000000000L + Random().nextInt(900000000)
        val newLoan: Loan = Loan(
                loanNumber = randomLoanNumber.toString(),
                mobileNumber = mobileNumber,
                loanType = LoanConstants.HOME_LOAN,
                totalLoan = LoanConstants.NEW_LOAN_LIMIT,
                amountPaid = 0,
                outstandingAmount = LoanConstants.NEW_LOAN_LIMIT
        )
        return newLoan
    }
}