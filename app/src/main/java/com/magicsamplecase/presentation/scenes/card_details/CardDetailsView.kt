package com.magicsamplecase.presentation.scenes.card_details

import com.magicsamplecase.BasePresenterView

interface CardDetailsView : BasePresenterView{
    fun initView(cardDetailsVM: CardDetailsVM)
}