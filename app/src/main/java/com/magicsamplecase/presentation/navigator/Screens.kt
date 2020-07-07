package com.magicsamplecase.presentation.navigator

import android.os.Parcelable
import com.magicsamplecase.presentation.scenes.card_details.CardDetailsFragment
import com.magicsamplecase.presentation.scenes.cards.CardListFragment
import kotlinx.android.parcel.Parcelize
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen(), Parcelable

@Parcelize
class CardListScreen : Screen() {
    override fun getFragment() = CardListFragment.getInstance()
}

@Parcelize
class CardDetailsScreen(val id: String) : Screen() {
    override fun getFragment() = CardDetailsFragment.getInstance(id)
}