package org.rookedsysc.accounts.accounts.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponseDto(
        val apiPath: String,
        val errorCode: HttpStatus,
        val errorMsg: String,
        val errorTime: LocalDateTime
)