package com.magicsamplecase.presentation.cards

import com.magicsamplecase.BasePresenter
import com.magicsamplecase.domain.GetCards
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class CardListPresenter @Inject constructor( private val useCase: GetCards,
                                             private val view: CardListView
) : BasePresenter() {
    init {
        useCase.getSource().subscribe(
            // Handle Success
            { list -> view.updateCardList(
                CardsViewModel.mapToView(list)
            )},

            // Handle Error
            { error -> println("ERROR_LOG: $error") }
        ).addTo(disposeBag)
    }
}