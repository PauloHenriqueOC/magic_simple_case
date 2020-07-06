package com.magicsamplecase.domain.boundaries

import com.magicsamplecase.domain.models.CardDetails
import com.magicsamplecase.domain.models.Cards
import io.reactivex.rxjava3.core.Single

interface CardsRepositoryBoundary {
    fun getCardList(): Single<Cards>
    fun getCardDetails(id: String): Single<CardDetails>
}