package org.rookedsysc.accounts.accounts.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class CustomerAlreadyExistException(msg: String) : RuntimeException(msg)