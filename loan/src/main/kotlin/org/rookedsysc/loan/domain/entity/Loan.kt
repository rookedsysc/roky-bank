package org.rookedsysc.loan.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "loan")
class Loan(
        @Id
        @Column(name = "loan_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var loanId: Long? = null,

        @Column(name = "mobile_number")
        var mobileNumber: String,

        @Column(name = "loan_number")
        val loanNumber: String,

        @Column(name = "loan_type")
        val loanType: String,

        @Column(name = "total_loan")
        val totalLoan: Int,

        @Column(name = "amount_paid")
        val amountPaid: Int,

        @Column(name = "outstanding_amount")
        var outstandingAmount: Int
) : BaseEntity()