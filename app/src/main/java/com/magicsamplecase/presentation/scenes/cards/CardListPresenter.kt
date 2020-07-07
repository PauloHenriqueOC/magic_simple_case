package com.magicsamplecase.presentation.scenes.cards

import com.magicsamplecase.BasePresenter
import com.magicsamplecase.domain.GetCards
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class CardListPresenter @Inject constructor(
    private val useCase: GetCards,
    private val view: CardListView
) : BasePresenter() {

    override fun bindView() {
        view.displayLoading(true)
        useCase.getSource()
            .subscribe(
                // Handle Success
                { list ->
                    view.updateCardList( CardsViewModel.mapToView(list) )
                    view.displayLoading(false)
                },

                // Handle Error
                { error ->
                    println("ERROR_LOG: $error")
                    view.displayLoading(false)
                }
            )
            .addTo(disposeBag)
    }
}