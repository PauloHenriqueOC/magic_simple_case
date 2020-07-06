package com.magicsamplecase.domain

import com.magicsamplecase.domain.models.Cards
import com.magicsamplecase.domain.boundaries.CardsRepositoryBoundary
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCards @Inject constructor(private val boundary: CardsRepositoryBoundary) {
    fun getSource(): Single<Cards> = boundary.getCardList()
}