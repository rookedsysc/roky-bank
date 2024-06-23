package org.rookedsysc.cards.common.handler

import org.rookedsysc.cards.domain.dto.ErrorResponseDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        val validationErrors = ex.bindingResult
                .allErrors
                .filterIsInstance<FieldError>()
                .associate { it.field to it.defaultMessage }

        return ResponseEntity(validationErrors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleGlobalException(e: Exception, webRequest: WebRequest): ResponseEntity<ErrorResponseDto> {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR

        val response = ErrorResponseDto(
                apiPath = webRequest.getDescription(false)
                        .toString(),
                errorCode = httpStatus,
                errorMsg = "Unknown error occurred. Please contact support",
                errorTime = LocalDateTime.now()
        )
        return ResponseEntity(response, httpStatus)
    }


}