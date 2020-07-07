package com.magicsamplecase.presentation.scenes.card_details

import com.magicsamplecase.domain.models.CardDetails

class CardDetailsVM (
    val name: String,
    val type: String
) {
    companion object {
        fun map(model: CardDetails) = CardDetailsVM(
            model.name,
            model.type
        )
    }
}