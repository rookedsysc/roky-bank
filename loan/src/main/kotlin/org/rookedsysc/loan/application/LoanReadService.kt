package org.rookedsysc.loan.application

import org.rookedsysc.loan.application.converter.LoanConverter
import org.rookedsysc.loan.application.ifs.ILoanReadService
import org.rookedsysc.loan.common.exception.ResourceNotFoundException
import org.rookedsysc.loan.domain.entity.Loan
import org.rookedsysc.loan.infrastructure.dto.LoanResponse
import org.rookedsysc.loan.infrastructure.persistence.LoanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoanReadService(private val loanRepository: LoanRepository) : ILoanReadService {
    override fun getLoanDetails(mobileNumber: String): LoanResponse {
        val loan: Loan = loanRepository.findByMobileNumber(mobileNumber)
                .let {
                    if (it == null) {
                        throw ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                    }
                    it
                }
        return LoanConverter.convertToResponse(loan)
    }
}