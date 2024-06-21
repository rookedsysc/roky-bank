package org.rookedsysc.accounts.accounts.exception

import org.rookedsysc.accounts.accounts.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [CustomerAlreadyExistException::class])
    fun handleCustomerAlreadyExistException(e: CustomerAlreadyExistException, webRequest: WebRequest): ResponseEntity<ErrorResponseDto> {
        val response = ErrorResponseDto(
                apiPath = webRequest.getDescription(false)
                        .toString(),
                errorCode = HttpStatus.BAD_REQUEST,
                errorMsg = e.message.toString(),
                errorTime = LocalDateTime.now()
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}