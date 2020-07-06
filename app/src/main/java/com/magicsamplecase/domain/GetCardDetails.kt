package com.magicsamplecase.domain

import com.magicsamplecase.domain.boundaries.CardsRepositoryBoundary
import com.magicsamplecase.domain.models.CardDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCardDetails @Inject constructor(private val boundary: CardsRepositoryBoundary) {
    fun getSource(id: String): Single<CardDetails> = boundary.getCardDetails(id)
}