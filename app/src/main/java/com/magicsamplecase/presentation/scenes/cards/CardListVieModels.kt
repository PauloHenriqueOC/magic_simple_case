package com.magicsamplecase.presentation.scenes.cards
import com.magicsamplecase.domain.models.Cards

class CardsViewModel(var cards: List<CardViewModel>) {
    companion object CardsViewModel {
        fun mapToView(list: Cards) =
            CardsViewModel(
                cards = list.cards.map {
                    CardViewModel(
                        it.cardName,
                        it.id
                    )
                }
            )
    }
}

class CardViewModel (
    var cardName: String,
    var id: String
)