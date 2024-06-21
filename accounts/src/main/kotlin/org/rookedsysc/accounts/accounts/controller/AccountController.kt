package org.rookedsysc.accounts.accounts.controller

import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.dto.CustomerDto
import org.rookedsysc.accounts.accounts.dto.ResponseDto
import org.rookedsysc.accounts.accounts.service.IAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AccountController(private val accountService: IAccountService) {

    @PostMapping("/accounts")
    fun createAccount(@RequestBody customerDto: CustomerDto): ResponseEntity<ResponseDto> {
        accountService.createAccount(customerDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201))
    }
}