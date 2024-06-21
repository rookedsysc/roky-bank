package org.rookedsysc.accounts.accounts.controller

import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.dto.CustomerRequestDto
import org.rookedsysc.accounts.accounts.dto.CustomerResponseDto
import org.rookedsysc.accounts.accounts.dto.ResponseDto
import org.rookedsysc.accounts.accounts.service.IAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountService: IAccountService) {

    @PostMapping
    fun createAccount(@RequestBody customerDto: CustomerRequestDto): ResponseEntity<ResponseDto> {
        accountService.createAccount(customerDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201))
    }

    @GetMapping
    fun accountDetailByMobileNumber(@RequestParam mobileNumber: String): ResponseEntity<CustomerResponseDto> {
        val customerDto = accountService.accountDetailByMobileNumber(mobileNumber)
        return ResponseEntity.ok(customerDto)
    }
}