package com.magicsamplecase.presentation.scenes.cards

import com.magicsamplecase.dagger.*
import dagger.Component
import dagger.Module
import dagger.Provides

@ViewScope
@Component(dependencies = [PresentationComponent::class], modules = [CardListModule::class])
interface CardListComponent {
    fun inject(fragment: CardListFragment)
}

@Module
class CardListModule(private val view: CardListView) {
    @Provides
    fun providesPresenter() = view
}