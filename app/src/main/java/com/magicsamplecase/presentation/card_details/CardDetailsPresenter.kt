package com.magicsamplecase.presentation.card_details

import com.magicsamplecase.BasePresenter
import com.magicsamplecase.domain.GetCardDetails
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class CardDetailsPresenter @Inject constructor( private val id: String,
                                                private val useCase: GetCardDetails,
                                                private val view: CardDetailsView) : BasePresenter() {


    init {
        useCase.getSource(id).subscribe(
            // Handle Success
            {
                view.initView(CardDetailsVM.map(it))
            },

            // Handle Error
            {

            }
        ).addTo(disposeBag)
    }
}