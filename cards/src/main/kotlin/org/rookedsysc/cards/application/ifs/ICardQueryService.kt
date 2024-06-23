package org.rookedsysc.cards.application.ifs

import org.rookedsysc.cards.infrastructure.dto.request.CardUpdateRequest

interface ICardQueryService{
    fun create(mobileNumber: String)
    fun update(request: CardUpdateRequest) : Boolean
    fun delete(mobileNumber: String) : Boolean
}