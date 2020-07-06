package com.magicsamplecase.data.repository

import com.magicsamplecase.data.remote.CardsRemoteDS
import com.magicsamplecase.data.remote.models.mapToDomain
import com.magicsamplecase.domain.models.Cards
import com.magicsamplecase.domain.boundaries.CardsRepositoryBoundary
import com.magicsamplecase.domain.models.CardDetails
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class CardsRepository @Inject constructor(private val cardsRemoteDS: CardsRemoteDS) : CardsRepositoryBoundary {

    override fun getCardList(): Single<Cards> =
        cardsRemoteDS.getCards()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.mapToDomain() }

    override fun getCardDetails(id: String): Single<CardDetails> =
        cardsRemoteDS.getCardDetails(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.mapToDomain() }
}