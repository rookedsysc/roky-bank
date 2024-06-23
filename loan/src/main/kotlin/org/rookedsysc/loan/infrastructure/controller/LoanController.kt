package org.rookedsysc.loan.infrastructure.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.Pattern
import org.rookedsysc.cards.domain.dto.ResponseDto
import org.rookedsysc.loan.application.LoanQueryService
import org.rookedsysc.loan.common.constants.LoanConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(
        name = "CRUD REST APIs for Loans in RokyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping("/api/loans")
class LoanController(private val loanQueryService: LoanQueryService) {

    @PostMapping
    fun create(@RequestParam
               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
               mobileNumber: String
    ): ResponseEntity<ResponseDto> {
        loanQueryService.create(mobileNumber)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseDto(
                                LoanConstants.STATUS_201,
                                LoanConstants.MESSAGE_201
                        )
                )
    }
}