package com.magicsamplecase.presentation.scenes.cards

import com.magicsamplecase.BasePresenterView

interface CardListView : BasePresenterView {
    fun updateCardList(data: CardsViewModel)
}