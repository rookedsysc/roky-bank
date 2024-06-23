package org.rookedsysc.cards.application.ifs

import org.rookedsysc.cards.infrastructure.dto.response.CardResponse

interface ICardReadService {
    fun cardDetailByMobileNumber(mobileNumber: String): CardResponse
}