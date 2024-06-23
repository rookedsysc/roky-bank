package org.rookedsysc.loan.application

import org.rookedsysc.loan.application.converter.LoanConverter
import org.rookedsysc.loan.application.ifs.ILoanQueryService
import org.rookedsysc.loan.common.constants.LoanConstants
import org.rookedsysc.loan.common.exception.LoanAlreadyExistsException
import org.rookedsysc.loan.common.exception.ResourceNotFoundException
import org.rookedsysc.loan.domain.entity.Loan
import org.rookedsysc.loan.infrastructure.dto.LoanUpdateRequest
import org.rookedsysc.loan.infrastructure.persistence.LoanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class LoanQueryService(private val loanRepository: LoanRepository) : ILoanQueryService {
    override fun create(mobileNumber: String) {
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

    override fun update(request: LoanUpdateRequest) {
        loanRepository.findByMobileNumber(request.mobileNumber!!)
                .let {
                    if (it == null) {
                        throw ResourceNotFoundException("Loan", "mobileNumber", request.mobileNumber)
                    }
                    loanRepository.save(LoanConverter.updateLoan(request, it))
                }
    }

    override fun delete(mobileNumber: String): Boolean {
        loanRepository.findByMobileNumber(mobileNumber)
                .let {
                    if (it == null) {
                        throw ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                    }
                }
        loanRepository.deleteByMobileNumber(mobileNumber)
        return true
    }
}