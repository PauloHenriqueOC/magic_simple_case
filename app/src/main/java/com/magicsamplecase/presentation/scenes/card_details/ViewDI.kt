package com.magicsamplecase.presentation.scenes.card_details

import com.magicsamplecase.dagger.PresentationComponent
import com.magicsamplecase.dagger.ViewScope
import dagger.Component
import dagger.Module
import dagger.Provides

@ViewScope
@Component(dependencies = [PresentationComponent::class], modules = [CardDetailsModule::class])
interface CardDetailsComponent {
    fun inject(fragment: CardDetailsFragment)
}

@Module
class CardDetailsModule(private val view: CardDetailsView, private val id: String) {
    @Provides
    fun providesPresenter() = view

    @Provides
    fun providesId() = id
}