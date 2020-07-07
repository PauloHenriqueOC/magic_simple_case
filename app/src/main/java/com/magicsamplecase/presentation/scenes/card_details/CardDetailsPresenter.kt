package com.magicsamplecase.presentation.scenes.card_details

import android.util.Log
import com.magicsamplecase.BasePresenter
import com.magicsamplecase.domain.GetCardDetails
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class CardDetailsPresenter @Inject constructor( private val id: String,
                                                private val useCase: GetCardDetails,
                                                private val view: CardDetailsView) : BasePresenter()
{


    override fun bindView() {
        view.displayLoading(true)
        useCase.getSource(id)
            .doOnDispose { Log.d("DEBUG_PRESENTER", "getSource Dispose") }
            .subscribe(
            // Handle Success
            {
                Log.d("DEBUG_PRESENTER", "getSource Success")
                view.initView(CardDetailsVM.map(it))
                view.displayLoading(false)
            },

            // Handle Error
            { view.displayLoading(true) }

        ).addTo(disposeBag)
    }
}