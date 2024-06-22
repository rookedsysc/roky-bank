package org.rookedsysc.accounts.accounts.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.rookedsysc.accounts.accounts.constants.AccountConstants
import org.rookedsysc.accounts.accounts.dto.request.AccountUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerCreateRequestDto
import org.rookedsysc.accounts.accounts.dto.request.CustomerUpdateRequestDto
import org.rookedsysc.accounts.accounts.dto.response.CustomerResponseDto
import org.rookedsysc.accounts.accounts.dto.response.ErrorResponseDto
import org.rookedsysc.accounts.accounts.dto.response.ResponseDto
import org.rookedsysc.accounts.accounts.service.IAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(
        name = "CRUD REST APIs for Accounts in RokyBank",
        description = "CRUD REST APIs in RokyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@Validated
@RestController
@RequestMapping("/api")
class AccountController(private val accountService: IAccountService) {

    @Operation(
            summary = "계정 생성",
    )
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "201",
                        description = "HTTP Status CREATED"),
                ApiResponse(responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = [Content(
                                schema = Schema(implementation = ErrorResponseDto::class)
                        )])
            ]
    )
    @PostMapping("/accounts")
    fun createAccount(@Valid @RequestBody customerDto: CustomerCreateRequestDto): ResponseEntity<ResponseDto> {
        accountService.createAccount(customerDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201))
    }

    @Operation(
            summary = "계정 조회",
            description = "가입시 사용한 휴대폰 번호로 계정 조회"
    )
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200",
                        description = "HTTP Status OK"),
                ApiResponse(responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = [Content(
                                schema = Schema(implementation = ErrorResponseDto::class)
                        )]
                ),
            ]
    )

    @GetMapping("/accounts")
    fun accountDetailByMobileNumber(@RequestParam mobileNumber: String): ResponseEntity<CustomerResponseDto> {
        val customerDto = accountService.accountDetailByMobileNumber(mobileNumber)
        return ResponseEntity.ok(customerDto)
    }

    @Operation(
            summary = "계좌 정보 수정",
            description = "계좌 번호로 정보 수정"
    )
    @ApiResponses(
            value = [
                ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = [Content(
                                schema = Schema(implementation = ErrorResponseDto::class)
                        )]
                )
            ]
    )
    @PatchMapping("/accounts")
    fun accountUpdate(@Valid @RequestBody updateRequestDto: AccountUpdateRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.updateAccount(updateRequestDto))
    }

    @Operation(
            summary = "고객 정보 수정",
            description = "계좌 번호로 정보 수정"
    )
    @PatchMapping("/customers")
    fun customerUpdate(@Valid @RequestBody updateRequestDto: CustomerUpdateRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.updateCustomer(updateRequestDto))
    }

    @Operation(
            summary = "계좌 및 고객 정보 삭제",
            description = "가입시 사용한 휴대폰 번호로 계좌 및 고객 정보 삭"
    )
    @DeleteMapping("/accounts")
    fun deleteAccount(@RequestParam mobileNumber: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok(accountService.deleteAccount(mobileNumber))
    }
}