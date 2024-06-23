package org.rookedsysc.cards.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(resourceName: String, fieldName: String, fieldValue: String) : RuntimeException("$resourceName not found with the given input data $fieldName : $fieldValue")