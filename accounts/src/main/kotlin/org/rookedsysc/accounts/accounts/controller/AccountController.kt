package org.rookedsysc.accounts.accounts.controller

import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.dto.request.AccountUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerCreateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.CustomerResponseDto
import org.rookedsysc.accounts.accounts.dto.response.ResponseDto
import org.rookedsysc.accounts.accounts.service.IAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AccountController(private val accountService: IAccountService) {

    @PostMapping("/accounts")
    fun createAccount(@RequestBody customerDto: CustomerCreateRequestDto): ResponseEntity<ResponseDto> {
        accountService.createAccount(customerDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201))
    }

    @GetMapping("/accounts")
    fun accountDetailByMobileNumber(@RequestParam mobileNumber: String): ResponseEntity<CustomerResponseDto> {
        val customerDto = accountService.accountDetailByMobileNumber(mobileNumber)
        return ResponseEntity.ok(customerDto)
    }

    @PatchMapping("/accounts")
    fun accountUpdate(@RequestBody updateRequestDto: AccountUpdateRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.updateAccount(updateRequestDto))
    }

    @PatchMapping("/customers")
    fun customerUpdate(@RequestBody updateRequestDto: CustomerUpdateRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.updateCustomer(updateRequestDto))
    }

    @DeleteMapping("/accounts")
    fun deleteAccount(@RequestParam mobileNumber: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.deleteAccount(mobileNumber))
    }
}